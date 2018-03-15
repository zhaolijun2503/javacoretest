package org.javacore.Guava;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class Collections {
	
	public static void main(String[] args) {
		HashMap<String,Object> map = Maps.newHashMap();
		ConcurrentMap<String,Object> current = Maps.newConcurrentMap();
		List<String> lists = Lists.newArrayList();
		List<String> list = Lists.newArrayList("aa");
		Set<String> sets = Sets.newHashSet();
		
	}

}
