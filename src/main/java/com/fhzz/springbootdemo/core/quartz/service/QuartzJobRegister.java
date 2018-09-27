package com.fhzz.springbootdemo.core.quartz.service;

/**
 * 
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fhzz.springbootdemo.core.quartz.job.AbstractJob;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月9日 下午5:09:47
 * @Copyright: FHZZ
 */
@Service
public class QuartzJobRegister {
	Log logger = LogFactory.getLog(QuartzJobRegister.class);

	List<AbstractJob> jobs = new ArrayList<AbstractJob>();

	public void regist(AbstractJob job) {
		jobs.add(job);
		logger.info("job registed:" + job.getClass().getName());
	}

	public List<String> getAllJobsName() {
		List<String> list = new ArrayList<String>();
		for (AbstractJob job : jobs) {
			list.add(job.getClass().getName());
		}
		return list;
	}

}
