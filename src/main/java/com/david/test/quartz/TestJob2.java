package com.david.test.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//@Component
@QuartzTimerConfiguration(jobClass=TestJob2.class,cronExpression="0/3 * * * * ?",jobName="test-job-2")
public class TestJob2 implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("test job 2");
	}
}
