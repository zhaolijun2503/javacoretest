package org.jee.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;



public class BeanNameAwareTest  implements BeanNameAware , BeanFactoryAware{
	
	private String beanName;

	@Override
	public void setBeanName(String arg0) {
		// TODO Auto-generated method stub
		this.beanName=arg0;
	}

	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		// TODO Auto-generated method stub
		
	}

}
