/*    */ package com.database;

/*    */
/*    */ import org.springframework.beans.factory.annotation.Value;
/*    */ import org.springframework.context.annotation.Configuration;

/*    */
/*    */ @Configuration
/*    */ public class DBConfiguration
/*    */ {
	/*    */
	/*    */ @Value("${spring.datasource.driver-class-name}")
	/*    */ private String driverClassName;
	/*    */
	/*    */ @Value("${spring.datasource.url}")
	/*    */ private String url;
	/*    */
	/*    */ @Value("${spring.datasource.username}")
	/*    */ private String username;
	/*    */
	/*    */ @Value("${spring.datasource.password}")
	/*    */ private String password;

	/*    */
	/*    */ public String getDriverClassName()
	/*    */ {
		/* 37 */ return this.driverClassName;
		/*    */ }

	/*    */
	/*    */ public void setDriverClassName(String driverClassName) {
		/* 41 */ this.driverClassName = driverClassName;
		/*    */ }

	/*    */
	/*    */ public String getUrl() {
		/* 45 */ return this.url;
		/*    */ }

	/*    */
	/*    */ public void setUrl(String url) {
		/* 49 */ this.url = url;
		/*    */ }

	/*    */
	/*    */ public String getUsername() {
		/* 53 */ return this.username;
		/*    */ }

	/*    */
	/*    */ public void setUsername(String username) {
		/* 57 */ this.username = username;
		/*    */ }

	/*    */
	/*    */ public String getPassword() {
		/* 61 */ return this.password;
		/*    */ }

	/*    */
	/*    */ public void setPassword(String password) {
		/* 65 */ this.password = password;
		/*    */ }

	/*    */
	/*    */ public String toString()
	/*    */ {
		/* 70 */ return "DBConfiguration [driverClassName=" + this.driverClassName + ", url=" + this.url + ", username="
				+ this.username + ", password=" + this.password + "]";
		/*    */ }
	/*    */ }

/*
 * Location: C:\Users\DigiSpice\Desktop\WEB-INF\classes\ Qualified Name:
 * com.database.DBConfiguration JD-Core Version: 0.6.0
 */