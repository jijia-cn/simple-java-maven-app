package com.david.test.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//@Component
@QuartzTimerConfiguration(jobClass=TestJob.class,cronExpression="0/5 * * * * ?",jobName="test-job1")
public class TestJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("This is quartz test job");
	}
	
	public static void main(String[] args) {
		System.out.println(Job.class.isAssignableFrom(TestJob.class));
	}
}
