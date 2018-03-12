package org.jee.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/*
 * Spring的InitializingBean接口有很好的用处,位于spring beans中,它只提供一个方法afterPropertiesSet(),
 * 当你实现了该方法后,spring就会对你提供框架级的支持:当你通过sring容器生产出实现了该接口的类的实例后,
 * 它就会调用afterPropertiesSet方法,通过这个方法,你可以检查你的bean是否正确地被初始化了.
 * 当然,你也可以用init-method方法.这两种方式可以同时使用,调用的顺序为init-method后调用
 * 
 * 实现org.springframework.beans.factory.DisposableBean接口的bean允许在容器销毁该bean的时候获得一次回调。DisposableBean接口也只规定了一个方法：
 * void destroy() throws Exception;
 * 通常，要避免使用DisposableBean标志接口而且不鼓励使用该接口，
 * 因为这样会将代码与Spring耦合在一起，有一个可选的方案是，
 * 在bean定义中指定一个普通的析构方法，然后在XML配置文件中通过指定destroy-method属性来完成。如下面的定义所示：
 * <bean id="exampleInitBean" class="examples.ExampleBean" destroy-method="cleanup"/>
 */
public class Springinitializingbean implements InitializingBean,DisposableBean{

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("spring初始化时候加载我");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("spring销毁时候加载我");
	}

}
