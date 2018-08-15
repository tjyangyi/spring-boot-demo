/**
 * 
 */
package com.fhzz.springbootdemo.configuration.datasource;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.DruidDataSource;
import com.fhzz.springbootdemo.configuration.druid.DruidConfiguration;

/**
 * @author Administrator
 *
 */
@Service
public class DruidDataSourceBuilder {
	private static Logger logger = LoggerFactory.getLogger(DruidConfiguration.class);
	@Value("5")
	private int initialSize;

	@Value("5")
	private int minIdle;

	@Value("20")
	private int maxActive;

	@Value("60000")
	private int maxWait;

	// 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
	@Value("60000")
	private int timeBetweenEvictionRunsMillis;

	// 配置一个连接在池中最小生存的时间，单位是毫秒
	@Value("300000")
	private int minEvictableIdleTimeMillis;

	@Value("SELECT 1 FROM DUAL")
	private String validationQuery;

	@Value("true")
	private boolean testWhileIdle;

	@Value("false")
	private boolean testOnBorrow;

	@Value("false")
	private boolean testOnReturn;

	// 打开PSCache，并且指定每个连接上PSCache的大小
	@Value("true")
	private boolean poolPreparedStatements;

	@Value("20")
	private int maxPoolPreparedStatementPerConnectionSize;

	// 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
	@Value("stat,wall,log4j")
	private String filters;

	// 通过connectProperties属性来打开mergeSql功能；慢SQL记录
	@Value("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500;")
	private String connectionProperties;

	public DruidDataSource getDruidDataSource(String username, String password, String url, String driverClassName) {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(url);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName(driverClassName);
		// configuration
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		datasource.setPoolPreparedStatements(poolPreparedStatements);
		datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter : {0}", e);
		}
		datasource.setConnectionProperties(connectionProperties);
		return datasource;
	}
}
