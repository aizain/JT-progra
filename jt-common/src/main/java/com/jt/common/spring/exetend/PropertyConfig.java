package com.jt.common.spring.exetend;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识该标签的变量
 * 会根据变量名
 * 注入spring加载的静态资源
 * @author zain
 * 17/01/01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PropertyConfig {
    // String value() default "";
    // boolean required() default true;
}
