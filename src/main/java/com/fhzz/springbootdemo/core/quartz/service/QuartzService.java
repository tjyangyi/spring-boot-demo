package com.fhzz.springbootdemo.core.quartz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhzz.springbootdemo.core.quartz.vo.CronJobInfo;
import com.fhzz.springbootdemo.util.json.JsonHelper;

@Service
public class QuartzService {

	@Autowired
    private Scheduler scheduler;

	@SuppressWarnings("unchecked")
	public CronJobInfo getCronJobInfo(String jobGroup, String jobName) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		List<CronTrigger> triggers = (List<CronTrigger>) scheduler.getTriggersOfJob(jobKey);
		TriggerKey triggerKey = triggers.get(0).getKey();
		return this.buildCronJobInfo(triggerKey);
	}

	public List<CronJobInfo> getAllCronJobInfos() throws SchedulerException {
		List<CronJobInfo> cronJobInfos = new ArrayList<CronJobInfo>();
		List<String> triggerGroupNames = scheduler.getTriggerGroupNames();// 获取所有TriggerGroup
		for (String triggerGroupName : triggerGroupNames) {
			Set<TriggerKey> triggerKeys = scheduler
					.getTriggerKeys(GroupMatcher.triggerGroupEquals(triggerGroupName));// 根据triggerGroup获取该group下的所有TriggerKey
			for (TriggerKey triggerKey : triggerKeys) {
				cronJobInfos.add(this.buildCronJobInfo(triggerKey));
			}
		}
		return cronJobInfos;
	}

	private CronJobInfo buildCronJobInfo(TriggerKey triggerKey) throws SchedulerException {
		CronJobInfo cronJobInfo = new CronJobInfo();
		// trigger key相关属性
		cronJobInfo.setTriggerName(triggerKey.getName());
		cronJobInfo.setTriggerGroupName(triggerKey.getGroup());
		// trigger相关属性
		Trigger trigger = scheduler.getTrigger(triggerKey);
		cronJobInfo.setPrevFireTime(trigger.getPreviousFireTime());
		cronJobInfo.setNextFireTime(trigger.getNextFireTime());
		cronJobInfo.setPriority(trigger.getPriority());
		cronJobInfo.setStartTime(trigger.getStartTime());
		cronJobInfo.setEndTime(trigger.getEndTime());
		TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
		cronJobInfo.setTriggerState(triggerState.name());
		if (trigger instanceof CronTrigger) {
			cronJobInfo.setCronExpression(((CronTrigger) trigger).getCronExpression());
		}
		// jobKey相关属性
		JobKey jobKey = trigger.getJobKey();
		cronJobInfo.setJobName(jobKey.getName());
		cronJobInfo.setJobGroup(jobKey.getGroup());
		// jobDetial相关属性
		JobDetail jobDetail = scheduler.getJobDetail(jobKey);
		JobDataMap map = jobDetail.getJobDataMap();
		cronJobInfo.setJobClass(jobDetail.getJobClass().getName());
		cronJobInfo.setJobDataMapJson(JsonHelper.object2json(map));
		return cronJobInfo;
	}

	@SuppressWarnings("unchecked")
	public void addJob(CronJobInfo cronJobInfo) throws ClassNotFoundException, SchedulerException {
		Class<Job> jobClass = (Class<Job>) Class.forName(cronJobInfo.getJobClass());
		JobDetail job = JobBuilder.newJob(jobClass).withIdentity(cronJobInfo.getJobName(), cronJobInfo.getJobGroup())
				.build();// 创建一项作业
		CronTrigger trigger = TriggerBuilder.newTrigger()
				.withIdentity(cronJobInfo.getTriggerName(), cronJobInfo.getTriggerGroupName())
				.withSchedule(CronScheduleBuilder.cronSchedule(cronJobInfo.getCronExpression())).build();// 创建一个触发器
		scheduler.scheduleJob(job, trigger);// 告诉调度器使用该触发器来安排作业
		if (!scheduler.isShutdown()) {
			scheduler.start();// 启动
		}
	}

	public void triggerJob(CronJobInfo cronJobInfo) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(cronJobInfo.getJobName(), cronJobInfo.getJobGroup());
		scheduler.triggerJob(jobKey);
	}

	public void modifyJobTime(CronJobInfo cronJobInfo) throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey(cronJobInfo.getTriggerName(), cronJobInfo.getTriggerGroupName());
		TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
		triggerBuilder.withIdentity(cronJobInfo.getTriggerName(), cronJobInfo.getTriggerGroupName());
		triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cronJobInfo.getCronExpression()));// 触发器时间设定
		CronTrigger trigger = (CronTrigger) triggerBuilder.build();
		scheduler.rescheduleJob(triggerKey, trigger);
	}

	/**
	 * 删除任务
	 */
	public void removeJob(CronJobInfo cronJobInfo) throws SchedulerException {
		scheduler
				.pauseTrigger(TriggerKey.triggerKey(cronJobInfo.getTriggerName(), cronJobInfo.getTriggerGroupName()));// 停止触发器
		scheduler
				.unscheduleJob(TriggerKey.triggerKey(cronJobInfo.getTriggerName(), cronJobInfo.getTriggerGroupName()));// 移除触发器
		scheduler.deleteJob(JobKey.jobKey(cronJobInfo.getJobName(), cronJobInfo.getJobGroup()));// 删除任务
	}

	public void startSchedule() throws SchedulerException {
		scheduler.start();
	}

	public void shutdownSchedule() throws SchedulerException {
		if (!scheduler.isShutdown()) {
			scheduler.shutdown();
		}
	}

	public void pauseJob(String jobName, String jobGroupName) throws SchedulerException {
		scheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
	}

	public void resumeJob(String jobName, String jobGroupName) throws SchedulerException {
		scheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
	}

}
