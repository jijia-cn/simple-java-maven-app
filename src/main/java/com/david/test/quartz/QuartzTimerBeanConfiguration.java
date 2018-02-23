package com.david.test.quartz;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于quartz绑定bean 通过JobDataMap的方式传入 且默认key为类名
 * @author jijia
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface QuartzTimerBeanConfiguration {
	//导入的bean
	String[] beanNames();
}
