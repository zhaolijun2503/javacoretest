package org.javacore;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringEscapeUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class TestWrapper {
	
	public static void main(String args[]) throws Exception {
        //Map map = System.getProperties();
		
        /*Map<Integer,Long> map  = new HashMap<Integer,Long>(20*10000);
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
        }*/
		System.out.println(new String(Base64.decodeBase64("cElKTG5rUjYyT3JGeGRYVXpWRjlJaWx1UklnPTpWT2FKcG1CenJDNTV5Ry16N0VKWGsxUjlBaklmOGdHR2xFT1NqbVhqOjAxVmRoSUdVTF8tSVljaXhQQUJ3Z0x5dm1nVT06ZXlKelkyOXdaU0k2SW5SbGMzUXRaRzkxZHlJc0ltMXBiV1ZNYVcxcGRDSTZJbWx0WVdkbEx5b2lMQ0prWldGa2JHbHVaU0k2TVRVek5UVXpPVFkzTUgwPQ==")));
		
	        
	        String s = "<pre class=\"brush: java;\"> \n dd";
	        System.out.println(StringEscapeUtils.escapeHtml4(s));
	        
		
	    List<String> list=Lists.newArrayList();
	    list.add("aa");
	    list.add("bb");
	    list.add("cc");
	    list.add("dd");
	    List<String> newList=Lists.newArrayList("ff","gg","jj");
	    List<String> tlist=list.subList(0, 3);
	    tlist.addAll(newList);
	    list=tlist;
        System.out.println(list);
        String sessionId = "type" + "_" + 3333 + "_" + System.currentTimeMillis() + "_"
                + "keyword" + "_" + ThreadLocalRandom.current().nextInt(1000);
        //String encodeSessionId = Base64.getUrlSafeEncoder().encodeToString(sessionId.getBytes());
        System.out.println(Base64.encodeBase64URLSafeString((System.currentTimeMillis() + "_"+ThreadLocalRandom.current().nextInt(1000)).getBytes()));
        System.out.println(new String(Base64.decodeBase64(Base64.encodeBase64URLSafeString(sessionId.getBytes()))));
        
    }
	

}
