package com.david.test.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 定时器测试demo
 * 
 * @author jia ji
 *
 */
public class TestQuartzConfig {

	public static void main(String[] args) {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			JobDetail job = JobBuilder.newJob(TestJob.class).withIdentity("testjob", "test-job").build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("testtriger", "test-triger").startNow()
					.withSchedule(SimpleScheduleBuilder.repeatMinutelyForever()).build();
			scheduler.scheduleJob(job, trigger);
			scheduler.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
