package org.javacore.concurrent;

import java.util.concurrent.locks.LockSupport;


/*  https://blog.csdn.net/secsf/article/details/78560013
 *  LockSupport的park(),unPark()与Thread的wait(),notify()区别
    1.面向的主体不一样。LockSuport主要是针对Thread进进行阻塞处理,可以指定阻塞队列的目标对象,每次可以指定具体的线程唤醒。Object.wait()是以对象为纬度,阻塞当前的线程和唤醒单个(随机)或者所有线程。
    2.实现机制不同。虽然LockSuport可以指定monitor的object对象,但和object.wait(),两者的阻塞队列并不交叉
 */
public class LockSupportTest {
	
	public static void main(String[] args) throws Exception {
		TestLockSupport lockSupport = new TestLockSupport();
		lockSupport.start();
//		TestThread thread = new TestThread();
//		thread.start();
		
	}
}
	class TestLockSupport extends Thread {
		public void run() {
			System.out.println( "TestLockSupport.run()" );
			LockSupport.park(  );
		}
	}
	class TestThread extends Thread {
		public void run() {
			System.out.println( "TestThread.run()" );
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	
	}
