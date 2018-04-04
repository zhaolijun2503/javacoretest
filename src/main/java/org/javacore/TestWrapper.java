package org.javacore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestWrapper {
	
	public static void main(String args[]) throws Exception {
        //Map map = System.getProperties();
		
        Map<Integer,Long> map  = new HashMap<Integer,Long>(20*10000);
        Random r = new Random();
        while (true) {
        	for (int i = 0; i <10000; i++) {
    			new Thread(() -> {
    	        	Integer key = r.nextInt() ;
    				try {
    					System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
    	        		map.put(key, new Date().getTime());
                        System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
    				} catch (Exception e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} finally{
    					map.remove(key);
    				}
    			}).start();
    		
    		}
        }
        
        
    }

}
