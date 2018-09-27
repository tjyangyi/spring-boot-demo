package com.fhzz.springbootdemo.core.quartz.service;

/**
 * 
 */

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhzz.springbootdemo.core.quartz.dao.QuartzDao;
import com.fhzz.springbootdemo.core.quartz.entity.QrtzJobDetails;

/**
 * @author YangYi
 * 
 */
@Service
public class QuartzStarter {
	Log logger = LogFactory.getLog(QuartzStarter.class);

	@Autowired
	private QuartzDao quartzDao;
	@Autowired
	private Scheduler quartzScheduler;

	@PostConstruct
	public void init() throws SchedulerException {
		try {
			this.cleanNotExistedJob();
		} catch (SchedulerException e) {
			logger.error(e.getMessage(), e);
		}
		quartzScheduler.start();
	}

	private void cleanNotExistedJob() throws SchedulerException {
		List<QrtzJobDetails> jobDetails = quartzDao.findAll();
		for (QrtzJobDetails jobDetail : jobDetails) {
			if (!isClassExist(jobDetail.getJobClassName())) {
				logger.info("Quartz Job类未找到,即将删除与之相关的任务,类名=" + jobDetail.getJobClassName());
				quartzDao.delete(jobDetail);
				logger.info("无效任务删除成功:" + jobDetail.getJobClassName());
			}
		}
	}

	private boolean isClassExist(String className) {
		try {
			Thread.currentThread().getContextClassLoader().loadClass(className);
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

}
