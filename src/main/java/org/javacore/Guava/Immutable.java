package org.javacore.Guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/*
 * 为什么需要不可变集合
    （1）保证线程安全：在并发程序中，使用Immutable既保证线程安全性，也大大增强了并发时的效率（跟并发锁方式相比）。尤其当一个对象是值对象时，更应该考虑采用Immutable方式；
    （2）被不可信的类库使用时会很安全；
    （3）如果一个对象不需要支持修改操作(mutation)，将会节省空间和时间的开销；经过分析，所有不可变的集合实现都比可变集合更加有效地利用内存；
    （4）可以当作一个常量来对待，并且这个对象在以后也不会被改变
 */
public class Immutable {
	
	public static void main(String[] args) {
		ImmutableList immutableList = ImmutableList.of("wyp", "good");
		ImmutableSet<String> immutableSet= ImmutableSet.of("a","b","a");
		ImmutableMap<String,Object> immutableMap= ImmutableMap.of("a", 1, "b", 2);
		System.out.println("不可以改变的集合"+immutableList+immutableSet+immutableMap);
	}

}
