package org.javacore.thread.CyclicBarrier;

public class CyclicBarrier {
	
	public static void main(String[] args) {
		java.util.concurrent.CyclicBarrier  cb=new java.util.concurrent.CyclicBarrier(5, () -> {
			System.out.println("cb finish");
		});
		
		long  start= System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				boolean holdLock = Thread.holdsLock(Thread.currentThread());
	            System.out.println("线程是否持有锁"+Thread.currentThread().getName()+holdLock);
	            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
				try {
					Thread.sleep(1000);
	                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
					cb.await();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            System.out.println("所有线程写入完毕，继续处理其他任务...");
				System.out.println(String.format("耗时:%sms", System.currentTimeMillis()- start));
			}).start();
		}
	}

}
