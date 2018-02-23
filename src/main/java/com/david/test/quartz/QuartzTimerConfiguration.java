package com.david.test.quartz;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.quartz.Job;

/**
 * 定义quartz 定时器的任务接口
 * @author jia ji
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface QuartzTimerConfiguration {
	//job-name
	String jobName();
	//job-group
	String jobGroup() default "job-default-group";
	//trigger-name
	String triggerName() default "";
	//trigger-group
	String triggerGroup() default "trigger-default-group";
	//cron 表达式
	String cronExpression();
	//Holidays 格式如下 2017-06-03,2017-08-09
	String holidays() default "";
	//任务类  实现org.quartz.Job接口
	Class<? extends Job> jobClass();
}

/**
 * quartz 定时器的类型
 * @author jia ji
 */
enum QuartzTriggerType{
	
}
