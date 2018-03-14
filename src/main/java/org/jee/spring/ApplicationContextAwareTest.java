package org.jee.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;

/*
 * 在Web应用中，Spring容器通常采用声明式方式配置产生：开发者只要在web.xml中配置一个Listener，该Listener将会负责初始化Spring容器，MVC框架可以直接调用Spring容器中的Bean，无需访问Spring容器本身。
 * 在这种情况下，容器中的Bean处于容器管理下，无需主动访问容器，只需接受容器的依赖注入即可。
 * 但在某些特殊的情况下，Bean需要实现某个功能，但该功能必须借助于Spring容器才能实现，此时就必须让该Bean先获取Spring容器，然后借助于Spring容器实现该功能。
 * 为了让Bean获取它所在的Spring容器，可以让该Bean实现ApplicationContextAware接口
 */
public class ApplicationContextAwareTest implements ApplicationContextAware{
	
	private org.springframework.context.ApplicationContext context;

	@Override
	public void setApplicationContext(
			org.springframework.context.ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		this.context=arg0;
	}

}
