package ru.springmvchibernate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

import java.util.Properties;

import static ru.springmvchibernate.util.PropertiesReader.getProperties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ru.springmvchibernate")
public class WebConfig {

	@Bean
	ViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(getProperties("driver.class"));
		dataSource.setUrl(getProperties("connection.url"));
		dataSource.setUsername(getProperties("username"));
		dataSource.setPassword(getProperties("password"));
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(getDataSource());
		emf.setJpaProperties(getJpaProperties());
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setPackagesToScan("ru.springmvchibernate.model");
		return emf;
	}

	@Bean
	public PlatformTransactionManager txManager(){
		return new JpaTransactionManager(getEntityManagerFactoryBean().getObject());
	}

	private Properties getJpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", getProperties("dialect"));
		properties.put("hibernate.show_sql", getProperties("show_sql"));
		properties.put("hibernate.hbm2ddl.auto", getProperties("hbm2ddl.auto"));
		return properties;
	}


}
