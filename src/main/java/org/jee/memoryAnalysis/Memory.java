package org.jee.memoryAnalysis;


/*
 *  http://www.importnew.com/12901.html
 *  jstack <PID>
	jstack -F <PID> # 有时候线程挂起的时候要加上-F参数才能把信息dump处理
	设置JVM参数，-XX:+HeapDumpOnOutOfMemoryError，在内存溢出的时候就会生成Heap dump文件 
	#提取进程内存信息，用于分析OOM导致原因
	jmap -dump:format=b,file=HeapDump.bin <pid>
	#输出堆信息
	jmap -heap <PID> 
	jmap -histo:live <pid> |less      打印每个class的实例数目,内存占用,类全名信息. VM的内部类名字开头会加上前缀”*”. 如果live子参数加上后,只统计活的对象数量
	jmap -dump:live,format=b,file=heap.bin <pid>
	jmap -dump:live,format=b,file=heap.hprof <pid>
	使用hprof二进制形式,输出jvm的heap内容到文件=. live子选项是可选的，假如指定live选项,那么只输出活的对象到文件
	
	JAVA进程内存 = JVM进程内存+heap内存+ 永久代内存+ 本地方法栈内存+线程栈内存 +堆外内存 +socket 缓冲区内存
	linux内存和JAVA堆中的关系
	RES = JAVA正在存活的内存对象大小 + 未回收的对象大小  + 其它
	VIART= JAVA中申请的内存大小，即 -Xmx  -Xms + 其它
	其它 = 永久代内存+ 本地方法栈内存+线程栈内存 +堆外内存 +socket 缓冲区内存 +JVM进程内存
	
	分析占用内存最高的线程堆栈；top -H -p <pid>     printf '%xn' 18783    jstack <pid> | grep -a 3d30
	
	JAVA_MEM_OPTS=" -server -Xmx3g -Xms2g -Xmn256m -XX:PermSize=128m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=80 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/dump_mobile
	
	GC_LOG="-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:/tmp/gc.log"
 */
public class Memory {
	/*
	 * 通过以下步骤可以很容易产生内存泄露（程序代码不能访问到某些对象，但是它们仍然保存在内存中）:
		    应用程序创建一个长时间运行的线程（或者使用线程池，会更快地发生内存泄露）。
		    线程通过某个类加载器（可以自定义）加载一个类。
		    该类分配了大块内存（比如new byte[1000000]），在某个静态变量存储一个强引用，然后在ThreadLocal中存储它自身的引用。分配额外的内存new byte[1000000]是可选的(类实例泄露已经足够了)，但是这样会使内存泄露更快。
		    线程清理自定义的类或者加载该类的类加载器。
		    重复以上步骤。
		    由于没有了对类和类加载器的引用，ThreadLocal中的存储就不能被访问到。ThreadLocal持有该对象的引用，它也就持有了这个类及其类加载器的引用，类加载器持有它所加载的类的所有引用，这样GC无法回收ThreadLocal中存储的内存。在很多JVM的实现中Java类和类加载器直接分配到permgen区域不执行GC，这样导致了更严重的内存泄露。
		   这种泄露模式的变种之一就是如果你经常重新部署以任何形式使用了ThreadLocal的应用程序、应用容器（比如Tomcat）会很容易发生内存泄露（由于应用容器使用了如前所述的线程，每次重新部署应用时将使用新的类加载器
		   
		   除了被遗忘的监听器，静态引用，hashmap中key错误/被修改或者线程阻塞不能结束生命周期等典型内存泄露场景，下面介绍一些不太明显的Java发生内存泄露的情况，主要是线程相关的。

	     Runtime.addShutdownHook后没有移除，即使使用了removeShutdownHook，由于ThreadGroup类对于未启动线程的bug，它可能不被回收，导致ThreadGroup发生内存泄露。
	              创建但未启动线程，与上面的情形相同
	              创建继承了ContextClassLoader和AccessControlContext的线程，ThreadGroup和InheritedThreadLocal的使用，所有这些引用都是潜在的泄露，以及所有被类加载器加载的类和所有静态引用等等。这对ThreadFactory接口作为重要组成元素整个j.u.c.Executor框架(java.util.concurrent)的影响非常明显，很多开发人员没有注意到它潜在的危险。而且很多库都会按照请求启动线程。
	     ThreadLocal缓存，很多情况下不是好的做法。有很多基于ThreadLocal的简单缓存的实现，但是如果线程在它的期望生命周期外继续运行ContextClassLoader将发生泄露。除非真正必要不要使用ThreadLocal缓存。
		    当ThreadGroup自身没有线程但是仍然有子线程组时调用ThreadGroup.destroy()。发生内存泄露将导致该线程组不能从它的父线程组移除，不能枚举子线程组。
		    使用WeakHashMap，value直接(间接)引用key，这是个很难发现的情形。这也适用于继承Weak/SoftReference的类可能持有对被保护对象的强引用。
		    使用http(s)协议的java.net.URL下载资源。KeepAliveCache在系统ThreadGroup创建新线程，导致当前线程的上下文类加载器内存泄露。没有存活线程时线程在第一次请求时创建，所以很有可能发生泄露。(在Java7中已经修正了，创建线程的代码合理地移除了上下文类加载器。)
		    使用InflaterInputStream在构造函数(比如PNGImageDecoder)中传递new java.util.zip.Inflater()，不调用inflater的end()。仅仅是new的话非常安全，但如果自己创建该类作为构造函数参数时调用流的close()不能关闭inflater，可能发生内存泄露。这并不是真正的内存泄露因为它会被finalizer释放。但这消耗了很多native内存，导致linux的oom_killer杀掉进程。所以这给我们的教训是：尽可能早地释放native资源。
	    java.util.zip.Deflater也一样，它的情况更加严重。好的地方可能是很少用到Deflater。如果自己创建了Deflater或者Inflater记住必须调用end()
	 */

}
