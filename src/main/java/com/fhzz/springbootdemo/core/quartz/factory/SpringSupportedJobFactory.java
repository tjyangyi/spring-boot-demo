package com.fhzz.springbootdemo.core.quartz.factory;

/**
 * 
 */

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月11日 下午2:08:34
 * @Copyright: FHZZ
 * @Desc: 一般情况下，quartz的job中使用autowired注解注入的对象为空，这时候我们就要使用spring-
 *        quartz提供的AdaptableJobFactory类。 自定义一个类
 */

@Component
public class SpringSupportedJobFactory extends AdaptableJobFactory {
	@Autowired
	private AutowireCapableBeanFactory capableBeanFactory;

	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		// 调用父类的方法
		Object jobInstance = super.createJobInstance(bundle);
		// 进行注入
		capableBeanFactory.autowireBean(jobInstance);
		return jobInstance;
	}
}
