package com.app.start;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import org.springframework.scheduling.annotation.EnableAsync;


//import org.springframework.jdbc.datasource.DriverManagerDataSource;
@Configuration
@SpringBootApplication
@EnableAsync 
@ComponentScan(basePackages="com")
public class SpringBootApp extends SpringBootServletInitializer  {
	
	 
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootApp.class);
	}
	@Bean
	public MessageSource messageSource() {
		System.out.println("Going to Load");
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("classpath:message");
	    //messageSource.setBasename("classpath:message_id");
	    //messageSource.setBasename("classpath:message_my");
/*	    messageSource.setBasenames("classpath:message_id", "classpath:message_en");
	    messageSource.setBasenames("classpath:message_en", "classpath:message_en");
*/	    messageSource.setDefaultEncoding("UTF-8");
	    System.out.println("After Load");
	//    messageSource.setCacheSeconds(10); //reload messages every 10 seconds
	    return messageSource;
	}
	
	 
}
