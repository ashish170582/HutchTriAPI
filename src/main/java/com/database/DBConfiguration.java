package com.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Configuration
public class DBConfiguration {
	@Value("${spring.datasource.driver}")
	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

/*	@Value("${maxActive}")
	private int maxActive = 30;

	@Value("${maxIdle}")
	private int maxIdle = 8;

	@Value("${minIdle}")
	private int minIdle = 8;

	@Value("${initialSize}")
	private int initialSize = 10;*/
	
	
	public DBConfiguration() {
		// TODO Auto-generated constructor stub
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "DBConfiguration [driverClassName=" + driverClassName + ", url=" + url + ", username=" + username
				+ ", password=" + password + "]";
	}
	
}
