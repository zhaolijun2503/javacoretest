package org.javacore.thread.CountDownLatch;

public class CountDownLatch {
	
	public static void main(String[] args) throws InterruptedException {
		
		java.util.concurrent.CountDownLatch  cd=new java.util.concurrent.CountDownLatch(5);
		long start =System.currentTimeMillis();
		for (int i = 0; i <10; i++) {
			new Thread(() -> {
				try {
                    System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
					Thread.sleep(1000);
                    System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					cd.countDown();
				}
			}).start();
		
		}
        System.out.println("等待子线程执行完毕...");
		cd.await();
		System.out.println("子线程已经执行完毕");
        System.out.println("继续执行主线程");
		System.out.println(cd.getCount());
		System.out.println(System.currentTimeMillis() - start);
	}

}
