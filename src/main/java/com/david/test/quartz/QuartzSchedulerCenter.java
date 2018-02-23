package com.david.test.quartz;

import java.lang.annotation.Annotation;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.david.test.logger.LoggerManager;;

/**
 * QuartzScheduler调度控制中心
 * @author jia ji
 *
 */
@Component
public class QuartzSchedulerCenter implements ApplicationContextAware,InitializingBean{
	
	//quartz 调度器
	private static Scheduler scheduler = null;
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private static final String QUARTZ_HOLIDAYS_SUFFIX = "quartz_holidays";
	
	public QuartzSchedulerCenter(){
		try {
			scheduler =  StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		LoggerManager.quartzInfo("after properties set");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(QuartzTimerConfiguration.class);
		
		for(String key:beanMap.keySet()){
			Annotation[] annotations = beanMap.get(key).getClass().getAnnotations();
			//find quartz concerned annotation
			QuartzTimerConfiguration quartzTimerConfiguration = null;
			QuartzTimerBeanConfiguration quartzTimerBeanConfiguration = null;
			for(Annotation annotation:annotations){
				if(annotation instanceof QuartzTimerConfiguration){
					quartzTimerConfiguration = (QuartzTimerConfiguration)annotation;
				}else if(annotation instanceof QuartzTimerBeanConfiguration){
					quartzTimerBeanConfiguration = (QuartzTimerBeanConfiguration)annotation;
				}
			}
			
			if(null != quartzTimerConfiguration){
				Class<? extends Job> jobClass = quartzTimerConfiguration.jobClass();
				String jobName = quartzTimerConfiguration.jobName();
				String jobGroup = quartzTimerConfiguration.jobGroup();
				String triggerName = quartzTimerConfiguration.triggerName();
				String triggerGroup = quartzTimerConfiguration.triggerGroup();
				String cronExpression = quartzTimerConfiguration.cronExpression();
				String holidayDate = quartzTimerConfiguration.holidays();
				if(null == jobClass || jobName.isEmpty()){
					LoggerManager.quartzInfo("Job Class is not defined");
				}else{
					try {
						//如果trigger的name为空则默认为job的name
						if(triggerName.isEmpty()){
							triggerName = jobName;
						}
						
						LoggerManager.quartzInfo("Start a quartz job [jobClass=".concat(jobClass.toString()).concat(",cronExpression=").concat(cronExpression).concat(",jobName=").concat(jobName).concat(",jobGroup=").concat(jobGroup)
								.concat(",triggerName=").concat(triggerName).concat(",triggerGroup=").concat(triggerGroup).concat(",holidays=").concat(holidayDate));
						
						JobDetail job = JobBuilder.newJob(jobClass)
								.withIdentity(jobName, jobGroup)
								.build();
						JobDataMap jobDataMap = job.getJobDataMap();
						
						//查找依赖注入的bean对象
						if(null != quartzTimerBeanConfiguration){
							for(String beanName:quartzTimerBeanConfiguration.beanNames()){
								try{
									jobDataMap.put(beanName, applicationContext.getBean(beanName));
									LoggerManager.quartzInfo("Inject bean to Job["+jobName+"]: "+applicationContext.getBean(beanName));
								}catch (BeansException e) {
									LoggerManager.quartzInfo("Inject bean failure: Cannot find bean named "+beanName);
								}
							}
						}
						
						TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger()
								.withIdentity(triggerName, triggerGroup)
								.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)
										.withMisfireHandlingInstructionFireAndProceed());
						
						//排除节假日
						if(!holidayDate.isEmpty()){
							String holidayKey = jobGroup+"."+jobName+"."+QUARTZ_HOLIDAYS_SUFFIX;
							HolidayCalendar holidayCalendar = new HolidayCalendar();
							for(String holiday:holidayDate.split(",")){
								holidayCalendar.addExcludedDate(format.parse(holiday));
							}
							scheduler.addCalendar(holidayKey, holidayCalendar, true,true);
							triggerBuilder.modifiedByCalendar(holidayKey);
						}
						Trigger trigger = triggerBuilder.startNow().build();
						scheduler.scheduleJob(job, trigger);
						scheduler.start();
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}
