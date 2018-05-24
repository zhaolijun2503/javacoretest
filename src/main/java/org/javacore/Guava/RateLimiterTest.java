package org.javacore.Guava;

import com.google.common.util.concurrent.RateLimiter;

//想要精细的控制QPS? 比如这样一个场景，你调用某个接口，对方明确需要你限制你的QPS在400之内你怎么控制
//http://ifeve.com/guava-ratelimiter/
public class RateLimiterTest {
	
	   public static void main(String[] args) {
		   //每秒两个许可
		   RateLimiter  rateLimiter=RateLimiter.create(2.0);
		   //获取一个许可
		   rateLimiter.acquire();
		   //获取指定数量的许可
		   rateLimiter.acquire(2);
		   
	   }
	
	

}
