package org.jee.memoryAnalysis;


/*
 * 
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

}
