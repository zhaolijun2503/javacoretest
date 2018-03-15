package org.javacore.Guava;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.ImmutableClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;


/*
 * ClassToInstanceMap提供了一种是用Class作为Key, 对应实例作为Value的途径.他定义了T getInstance(Class<T>)和T putInstance(Class<T> T)两个方法, 这两个方法消除了元素类型转换的过程并保证了元素在Map中是类型安全的.
 * 返回一个使用new HashMap<Class<? extends B>, B>()作为代理的MutableClassToInstanceMap
 * 我们之所以使用ClassToInstanceMap而不是使用Map<Class, Object>,
 * 就是因为ClassToInstanceMap使用了MapConstraint, 
 * 他保证了我们放入的Class和Object的类型是对应的, 而不会出现 put(Integer.class, "string")这样的情况
 */
public class ClassToInstanceMapDemo {
	
	public static void main(String[] args) {
		ClassToInstanceMap<Integer> maps=MutableClassToInstanceMap.create();
		maps.putInstance(Integer.class, 1);
		maps.putInstance(Integer.class, 2);
		
		
		ImmutableClassToInstanceMap<Number> map =
                new ImmutableClassToInstanceMap.Builder<Number>()
                .put(Integer.class, 100)
                .put(Float.class, 10.01f)
                .build();
        ImmutableClassToInstanceMap<Number> map2 = ImmutableClassToInstanceMap.copyOf(map);
        // throws UnsupportedOperationException
         map.putInstance(Integer.class, 1000);
        // map.put(Integer.class, 1000);
        System.out.println(map.getInstance(Integer.class));
        System.out.println(map2.getInstance(Float.class));
	}

}
