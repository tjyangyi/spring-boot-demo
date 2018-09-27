package com.fhzz.springbootdemo.core.quartz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fhzz.springbootdemo.core.quartz.service.QuartzJobRegister;
import com.fhzz.springbootdemo.core.quartz.service.QuartzService;
import com.fhzz.springbootdemo.core.quartz.vo.CronJobInfo;
import com.fhzz.springbootdemo.util.SystemStaticConst;

@Controller
@RequestMapping(value = "/quartz")
public class QuartzController {
	Log logger = LogFactory.getLog(QuartzController.class);

	@Autowired
	private QuartzService quartzService;

	@Autowired
	private QuartzJobRegister quartzJobRegister;

	/**
	 * 获取所有使用Cron表达式的JOB
	 * 
	 * @throws SchedulerException
	 */
	@RequestMapping("/queryQuartzJobList")
	@ResponseBody
	public Map<String, Object> queryQuartzJobList() throws SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		List<CronJobInfo> jobInfos = quartzService.getAllCronJobInfos();
		result.put("totalCount", jobInfos.size());
		result.put("result", jobInfos);
		return result;

	}

	/**
	 * 新增界面
	 */
	@RequestMapping("/openAddJob")
	public String openAddJob(Model model) {
		List<String> jobNameList = quartzJobRegister.getAllJobsName();
		model.addAttribute("jobNameList", jobNameList);
		return "quartz/add-job";
	}

	/**
	 * 跳转到编辑
	 * 
	 * @throws SchedulerException
	 */
	@RequestMapping(value = "/openEditJob")
	public String openEditJob(String jobName, String jobGroup, Model model) throws SchedulerException {
		CronJobInfo cronJobInfo = quartzService.getCronJobInfo(jobGroup, jobName);
		model.addAttribute("cronJobInfo", cronJobInfo);
		return "quartz/edit-job";
	}

	/**
	 * 删除任务
	 */
	@RequestMapping(value = "/deleteJob")
	@ResponseBody
	public Map<String, Object> deleteJob(CronJobInfo cronJobInfo) throws SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			quartzService.removeJob(cronJobInfo);
			result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
			result.put(SystemStaticConst.MSG, "删除定时任务成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
			result.put(SystemStaticConst.MSG, "删除定时任务失败！");
		}
		return result;
	}

	/**
	 * 新增job
	 * 
	 * @throws SchedulerException
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "/addJob")
	@ResponseBody
	public Map<String, Object> addJob(CronJobInfo cronJobInfo) throws ClassNotFoundException, SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			quartzService.addJob(cronJobInfo);
			result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
			result.put(SystemStaticConst.MSG, "保存定时任务成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
			result.put(SystemStaticConst.MSG, "保存定时任务失败！");
		}
		return result;
	}

	/**
	 * 运行任务
	 * 
	 * @throws SchedulerException
	 */
	@RequestMapping(value = "/triggerJob")
	@ResponseBody
	public Map<String, Object> triggerJob(CronJobInfo cronJobInfo) throws SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			quartzService.triggerJob(cronJobInfo);
			result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
			result.put(SystemStaticConst.MSG, "运行定时任务成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
			result.put(SystemStaticConst.MSG, "运行定时任务失败！");
		}
		return result;
	}

	/**
	 * 修改触发时间
	 * 
	 * @throws SchedulerException
	 */
	@RequestMapping(value = "/editJob")
	@ResponseBody
	public Map<String, Object> editJob(CronJobInfo cronJobInfo) throws SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			quartzService.modifyJobTime(cronJobInfo);
			result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
			result.put(SystemStaticConst.MSG, "保存定时任务成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
			result.put(SystemStaticConst.MSG, "保存定时任务失败！");
		}
		return result;
	}

	/**
	 * 暂停任务
	 */
	@RequestMapping(value = "/pauseJob")
	@ResponseBody
	public Map<String, Object> pauseJob(String jobName, String jobGroup) throws SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			quartzService.pauseJob(jobName, jobGroup);
			result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
			result.put(SystemStaticConst.MSG, "暂停定时任务成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
			result.put(SystemStaticConst.MSG, "暂停定时任务失败！");
		}
		return result;
	}

	/**
	 * 恢复任务
	 */
	@RequestMapping(value = "/resumeJob")
	@ResponseBody
	public Map<String, Object> resumeJob(String jobName, String jobGroup) throws SchedulerException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			quartzService.resumeJob(jobName, jobGroup);
			result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
			result.put(SystemStaticConst.MSG, "暂停定时任务成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
			result.put(SystemStaticConst.MSG, "暂停定时任务失败！");
		}
		return result;
	}

}
