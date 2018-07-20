package org.javacore.stream;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * Copyright [2015] [Jeff Lee]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 创建流
 *
 * Created by bysocket on 16/7/18.
 */
public class CreateStreamTest {
    public static void main(String[] args) {
    	
    	String[] values = { "aaa", "bbbb", "ddd", "cccc" };
    	Stream<String> stringStream = Arrays.stream(values);
    	Stream<String> stringStreamAlternative = Stream.of(values);
    	Stream<Integer> integerStream = Stream.of(1, 2, 3);
    	IntStream intStream = IntStream.of(1, 2, 3);
    	DoubleStream doubleStream = DoubleStream.of(1.0, 2.0, 3.0);
    	IntStream intStreams = Arrays.stream(new int[]{ 1, 2, 3 });
    	
    	Map<String, List<Integer>> map = new LinkedHashMap<>();
    	map.put("a", Arrays.asList(1, 2, 3));
    	map.put("b", Arrays.asList(4, 5, 6));

    	List<Integer> allValues = map.values() // Collection<List<Integer>>
    	        .stream()                      // Stream<List<Integer>>
    	        .flatMap(List::stream)         // Stream<Integer>
    	        .collect(Collectors.toList());

    	System.out.println(allValues);
    	// [1, 2, 3, 4, 5, 6]
    	//含Map的List可以被扁平化处理成一个连续的Stream：
    	
    }
}
