/**
 * 
 */
package com.fhzz.springbootdemo.configuration.datasource;

import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Administrator
 *
 */
@Configuration
@MapperScan(basePackages = "com.fhzz.springbootdemo.dao.slave1.mybatis", sqlSessionTemplateRef = "slave1SqlSessionTemplate")
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "slave1EntityManagerFactory", transactionManagerRef = "savle1JPATransactionManager", basePackages = {
		"com.fhzz.springbootdemo.dao.slave1.jpa" }) // 设置Repository所在位置
public class DataSource2Config {

	@Bean(name = "slave1DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.slave1")
	public DataSource slave1DataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "slave1SqlSessionFactory")
	public SqlSessionFactory setSqlSessionFactory(@Qualifier("slave1DataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

		Properties sqlSessionFactoryProperties = new Properties();
		sqlSessionFactoryProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		sqlSessionFactoryProperties.setProperty("hibernate.show_sql", "true");
		sqlSessionFactoryProperties.setProperty("hibernate.format_sql", "true");
		bean.setConfigurationProperties(sqlSessionFactoryProperties);

		bean.setDataSource(dataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/slave1/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "slave1TransactionManager")
	public DataSourceTransactionManager setTransactionManager(@Qualifier("slave1DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "slave1SqlSessionTemplate")
	public SqlSessionTemplate setSqlSessionTemplate(
			@Qualifier("slave1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	// ------------------JPA--------------------

	@Autowired
	private JpaProperties jpaProperties;

	private Map<String, Object> getVendorProperties() {
		return jpaProperties.getHibernateProperties(new HibernateSettings());
	}

	@Bean(name = "slave1EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean slave1EntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(slave1DataSource()).packages("com.fhzz.springbootdemo.entity.slave1") // 设置实体类所在位置
				.persistenceUnit("slave1PersistenceUnit").properties(getVendorProperties()).build();
	}

	@Bean(name = "savle1EntityManager")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return slave1EntityManagerFactory(builder).getObject().createEntityManager();
	}

	@Bean(name = "savle1JPATransactionManager")
	public PlatformTransactionManager savle1JPATransactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(slave1EntityManagerFactory(builder).getObject());
	}
}
