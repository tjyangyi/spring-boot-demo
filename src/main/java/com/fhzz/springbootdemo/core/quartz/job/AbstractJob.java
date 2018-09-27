package com.fhzz.springbootdemo.core.quartz.job;

/**
 * 
 */

import javax.annotation.PostConstruct;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;

import com.fhzz.springbootdemo.core.quartz.service.QuartzJobRegister;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月9日 下午5:05:46
 * @Copyright: FHZZ
 */
@DisallowConcurrentExecution
public abstract class AbstractJob implements Job {
	@Autowired
	private QuartzJobRegister quartzJobRegister;

	@PostConstruct
	public void init() {
		this.quartzJobRegister.regist(this);
	}

}
