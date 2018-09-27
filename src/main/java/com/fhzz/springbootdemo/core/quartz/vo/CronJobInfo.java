package com.fhzz.springbootdemo.core.quartz.vo;

import java.util.Date;

public class CronJobInfo {

	enum TriggerState {
		// NONE无,NORMAL正常,PAUSED暂停,COMPLETE完全,ERROR错误,BLOCKED阻塞
		NONE, NORMAL, PAUSED, COMPLETE, ERROR, BLOCKED
	}

	private int jobId;// 任务ID
	private String jobGroup;// JOB_GROUP
	private String jobName;// JOB_NAME
	private String triggerGroupName;// TRIGGER_GROUP_NAME
	private String triggerName;// TRIGGER_NAME
	private String cronExpression;// CRON表达式
	private Date prevFireTime;// 上次执行时间
	private Date nextFireTime;// 下次执行时间
	private String triggerState;// 触发器状态
	private int priority;// 优先级
	private Date startTime;// 开始时间
	private Date endTime;// 结束时间
	private String jobClass;// 任务类名
	private String jobDataMapJson;// JSON格式jobDataMap

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getTriggerGroupName() {
		return triggerGroupName;
	}

	public void setTriggerGroupName(String triggerGroupName) {
		this.triggerGroupName = triggerGroupName;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Date getPrevFireTime() {
		return prevFireTime;
	}

	public void setPrevFireTime(Date prevFireTime) {
		this.prevFireTime = prevFireTime;
	}

	public Date getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public String getTriggerState() {
		return triggerState;
	}

	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getJobDataMapJson() {
		return jobDataMapJson;
	}

	public void setJobDataMapJson(String jobDataMapJson) {
		this.jobDataMapJson = jobDataMapJson;
	}

}
