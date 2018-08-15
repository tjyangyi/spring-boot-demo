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
import org.springframework.context.annotation.Primary;
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
@MapperScan(basePackages = "com.fhzz.springbootdemo.dao.master.mybatis", sqlSessionTemplateRef = "masterSqlSessionTemplate")
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "masterEntityManagerFactory", transactionManagerRef = "masterJPATransactionManager", basePackages = {
		"com.fhzz.springbootdemo.dao.master.jpa" }) // 设置Repository所在位置
public class DataSource1Config {

	@Bean(name = "masterDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.master")
	@Primary
	public DataSource masterDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "masterSqlSessionFactory")
	@Primary
	public SqlSessionFactory setSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

		Properties sqlSessionFactoryProperties = new Properties();
		sqlSessionFactoryProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle9Dialect");
		sqlSessionFactoryProperties.setProperty("hibernate.show_sql", "true");
		sqlSessionFactoryProperties.setProperty("hibernate.format_sql", "true");
		bean.setConfigurationProperties(sqlSessionFactoryProperties);

		bean.setDataSource(dataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/master/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "masterTransactionManager")
	@Primary
	public DataSourceTransactionManager setTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "masterSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate setSqlSessionTemplate(
			@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	// ------------------JPA--------------------

	@Autowired
	private JpaProperties jpaProperties;

	private Map<String, Object> getVendorProperties() {
		return jpaProperties.getHibernateProperties(new HibernateSettings());
	}

	@Primary
	@Bean(name = "masterEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(masterDataSource()).packages("com.fhzz.springbootdemo.entity.master") // 设置实体类所在位置
				.persistenceUnit("masterPersistenceUnit").properties(getVendorProperties()).build();
	}

	@Primary
	@Bean(name = "masterEntityManager")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return masterEntityManagerFactory(builder).getObject().createEntityManager();
	}

	@Primary
	@Bean(name = "masterJPATransactionManager")
	public PlatformTransactionManager masterJPATransactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(masterEntityManagerFactory(builder).getObject());
	}

}
