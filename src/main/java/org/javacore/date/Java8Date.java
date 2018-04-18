package org.javacore.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;


/*  https://www.cnblogs.com/comeboo/p/5378922.html
 *  https://www.cnblogs.com/exmyth/p/6425878.html
 *  java8引入了一套全新的时间日期API，本篇随笔将说明学习java8的这套API。

	java。time包中的是类是不可变且线程安全的。新的时间及日期API位于java.time中，下面是一些关键类
	
	●Instant——它代表的是时间戳
	
	●LocalDate——不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等。
	
	●LocalTime——它代表的是不含日期的时间
	
	●LocalDateTime——它包含了日期及时间，不过还是没有偏移信息或者说时区。
	
	●ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的
 */
public class Java8Date {
	public static void main(String[] args) {
		System.out.println(Instant.now());
		System.out.println(LocalTime.now());
		System.out.println(LocalDate.now());
		System.out.println(LocalDateTime.now());
	}
	
	public String ThreadSafeFormate(Long time){
		return DateFormatUtils.format(time, "yyyy-MM-dd");
	}
	
	public int CompareTime(Long startTime,Long endTime){
		if(LocalDateTime.ofInstant(new Date(startTime).toInstant(), ZoneId.systemDefault()).isBefore(LocalDateTime.ofInstant(new Date(endTime).toInstant(), ZoneId.systemDefault()))){
			return -1;
		}else if(LocalDateTime.ofInstant(new Date(startTime).toInstant(), ZoneId.systemDefault()).isAfter(LocalDateTime.ofInstant(new Date(endTime).toInstant(), ZoneId.systemDefault()))){
			return 1;
		}
		return 0;
	}

}
