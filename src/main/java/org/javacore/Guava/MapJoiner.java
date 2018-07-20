package org.javacore.Guava;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Joiner;

public class MapJoiner {
     public static void main(String[] args) {
		
    	 Map<String, String> map = new HashMap<>();
    	 map.put("key1", "value1");
    	 map.put("key2", "value2");
    	 map.put("key3", "value3");
    	 com.google.common.base.Joiner.MapJoiner mapJoiner = Joiner.on("&").withKeyValueSeparator("=");
    	 System.out.println(mapJoiner.join(map)); //key3=value3,key2=value2,key1=value1
    	 String aa="fsfs  sdfds  sdfdsf  fdsfs";
    	 System.out.println(Arrays.toString(StringUtils.split(aa, "  ")));
	}
}
