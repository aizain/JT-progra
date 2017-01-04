package com.jt.common.spring.exetend;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
/**
 * 缓存下spring加载的静态资源
 * @author zain
 * 17/01/01
 */
public class ExtendedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	private Properties props;
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		this.props = props;
		System.err.println("ExtendedPropertyPlaceholderConfigurer: " + props);
	}
	
	public String getProperty(String key) {
		return props.getProperty(key);
	}
}
