package org.jee.lombok;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Synchronized;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

/*
 * @Data   ：注解在类上；自动为所有字段添加@ToString, @EqualsAndHashCode, @Getter方法，为非final字段添加@Setter,和@RequiredArgsConstructor!
 * @Setter/@Gette：注解在属性上；为属性提供 setting/getting 方法(自动生成Getter/Setter方法)
 * @Log4j ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
 * @Slf4j :    注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
 * @NoArgsConstructor：注解在类上；为类提供一个无参的构造方法
 * @AllArgsConstructor：注解在类上；为类提供一个全参的构造方法
 * @RequiredArgsConstructor :部分参数构造器、
 * @NonNull : 注解在属性上，用来校验参数（可以帮助我们避免空指针）
 * @Cleanup: 自动帮我们调用close()方法。
 * @SneakyThrows ；作用就是抛出异常 
 * @Synchronized :自动添加同步方法
 * @ToString ;生成toString方法，默认情况下，会输出类名、所有属性，属性会按照顺序输出，以逗号分割。
 * @EqualsAndHashCode ：默认情况下，会使用所有非瞬态(non-transient)和非静态(non-static)字段来生成equals和hascode方法，也可以指定具体使用哪些属性
 * @Builder  :生成建造者模式
 */
@Data
@Slf4j
@AllArgsConstructor
public class Lombok {
	
	private String name;
    private final String country;
    private final Object lockObj = new Object();
 
    public void sayHello(@NonNull String target) {
        String content = String.format("hello,%s", target);
        System.out.println(content);
        log.info(content);
    }
 
    public void addBalabala() {
        val list = new ArrayList<String>();
        list.add("haha");
        System.out.println(list.size());
    }
 
    @SneakyThrows(IOException.class)
    public void closeBalabala() {
        @Cleanup InputStream is = new ByteArrayInputStream("hello world".getBytes());
        System.out.println(is.available());
    }
 
    @Synchronized("lockObj")
    public void lockMethod() {
        System.out.println("test lock method");
    }

}
