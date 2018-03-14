package org.jee.spring;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/*
 * 凡是被Spring管理的类，实现接口 EnvironmentAware 重写方法 setEnvironment
 *  可以在工程启动时，获取到系统环境变量和application配置文件中的变量
 *  或可以使用`@Value("${propertyName}")`注解
 */
public class EnvironmentAwareTest implements EnvironmentAware{

	private String protocol;
	private String serverAddress;
	
	
	@Override
	public void setEnvironment(Environment arg0) {
		// TODO Auto-generated method stub
		this.protocol= arg0.getProperty("protocol");
		this.serverAddress=arg0.getProperty("serverAddress");
		
	}

}
