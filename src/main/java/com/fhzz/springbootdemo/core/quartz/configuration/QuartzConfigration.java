/**
 * 
 */
package com.fhzz.springbootdemo.core.quartz.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.fhzz.springbootdemo.core.quartz.factory.SpringSupportedJobFactory;

/**
 * @author Administrator
 *
 */
@Configuration
public class QuartzConfigration {

	@Autowired
	private SpringSupportedJobFactory jobFactory; // 自定义的factory

	// 获取工厂bean
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(DataSource masterDataSource) {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		try {
			schedulerFactoryBean.setQuartzProperties(quartzProperties());
			schedulerFactoryBean.setDataSource(masterDataSource);
			schedulerFactoryBean.setJobFactory(jobFactory);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return schedulerFactoryBean;
	}

	// 指定quartz.properties
	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/config/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

	// 创建schedule
	@Primary
	@Bean(name = "scheduler")
	public Scheduler scheduler(@Qualifier("masterDataSource") DataSource masterDataSource) {
		return schedulerFactoryBean(masterDataSource).getScheduler();
	}
}
