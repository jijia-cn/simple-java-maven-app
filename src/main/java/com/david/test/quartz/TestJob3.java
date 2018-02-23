package com.david.test.quartz;

import org.mybatis.spring.SqlSessionTemplate;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.david.test.exception.UserException;
import com.david.test.mapper.UserDao;
import com.david.test.service.UserService;

@Component
@QuartzTimerConfiguration(jobClass=TestJob3.class,
	cronExpression="0/4 * * * * ?",jobName="testjob3",
	holidays="2017-07-08")
@QuartzTimerBeanConfiguration(beanNames={"userDao","sqlSessionTemplate","userServiceImpl"})
public class TestJob3 implements Job{
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobDetail jobDetail = context.getJobDetail();
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		System.out.println("这是测试job3");
		UserDao userDao = (UserDao)jobDataMap.get("userDao");
		UserService userService = (UserService)jobDataMap.get("userServiceImpl");
		SqlSessionTemplate sqlSessionTemplate = (SqlSessionTemplate)jobDataMap.get("sqlSessionTemplate");
		System.out.println(userDao);
		System.out.println(userService);
		try {
			System.out.println(userDao.findUsers());
			System.out.println(userService.findUsers());
			System.out.println(sqlSessionTemplate);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
