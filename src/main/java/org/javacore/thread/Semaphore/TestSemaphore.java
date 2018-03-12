package org.javacore.thread.Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
 * void  acquire()   从信号量获取一个许可，如果无可用许可前 将一直阻塞等待，
 * void acquire(int permits)  获取指定数目的许可，如果无可用许可前  也将会一直阻塞等待
 * boolean tryAcquire()   从信号量尝试获取一个许可，如果无可用许可，直接返回false，不会阻塞
 * boolean tryAcquire(int permits)   尝试获取指定数目的许可，如果无可用许可直接返回false，
 * boolean tryAcquire(int permits, long timeout, TimeUnit unit)   在指定的时间内尝试从信号量中获取许可，如果在指定的时间内获取成功，返回true，否则返回false
 * void release()  释放一个许可，别忘了在finally中使用，注意：多次调用该方法，会使信号量的许可数增加，达到动态扩展的效果，如：初始permits 为1， 调用了两次release，最大许可会改变为2
 * int availablePermits() 获取当前信号量可用的许可
 */
public class TestSemaphore {
	
	public static void main(String[] args) {
		
	    // 允许2个线程同时访问  
        final Semaphore semaphore = new Semaphore(2);  
        ExecutorService executorService = Executors.newCachedThreadPool();  
        for (int i = 0; i < 10; i++) {  
            final int index = i;   
            executorService.execute(new Runnable() {  
                public void run() {  
                    try {  
                        semaphore.acquire();  
                        // 这里可能是业务代码  
                        System.out.println("线程:" + Thread.currentThread().getName() + "获得许可:" + index);  
                        TimeUnit.SECONDS.sleep(1);  
                        semaphore.release();  
                        System.out.println("允许TASK个数：" + semaphore.availablePermits());    
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            });  
        }  
        executorService.shutdown();  
	}

}
