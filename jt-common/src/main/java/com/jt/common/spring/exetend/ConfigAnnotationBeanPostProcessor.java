package com.jt.common.spring.exetend;

import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * bean后处理器
 * 处理Property标签
 * bean实例化后，将需要注入的值注入
 * 
 * @author zain
 * 17/01/01
 */
@Component
public class ConfigAnnotationBeanPostProcessor implements BeanPostProcessor{

    @Autowired
    private ExtendedPropertyPlaceholderConfigurer cfg;
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.err.println(beanName);
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(PropertyConfig.class)) {
                try {
                    field.setAccessible(true); // 破解私有变量
                    field.set(bean, cfg.getProperty(field.getName()));
                    System.err.println(field + " - " + field.get(bean));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

}
