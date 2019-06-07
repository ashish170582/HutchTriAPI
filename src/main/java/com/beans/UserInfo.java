/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.springframework.stereotype.Component;

import com.app.beans.SubscriptionBenefits;

@XmlType(name = "userinfo", propOrder = { "userinfo" })

@XmlRootElement(name = "root")
@Component
public class UserInfo extends Root {

	private String transactionId;
	private UserInfoData userinfo;
	private SubscriptionBenefits subscriptionBenefits;
	private OptScreenConfig optScreenConfig;

	public UserInfo() {
		super(0, "Success");
		this.transactionId = "0";
	}

	public UserInfo(String transactionId, UserInfoData userinfo, SubscriptionBenefits subscriptionBenefits) {
		super(0, "Success");
		this.transactionId = transactionId;
		this.userinfo = userinfo;
		this.subscriptionBenefits = subscriptionBenefits;
	}

	public UserInfo(UserInfoData userinfo, SubscriptionBenefits subscriptionBenefits) {
		super(0, "Success");
		this.transactionId = "0";
		this.userinfo = userinfo;
		this.subscriptionBenefits = subscriptionBenefits;
	}

	public UserInfo(UserInfoData userinfo, SubscriptionBenefits subscriptionBenefits, OptScreenConfig optScreenConfig) {
		super(0, "Success");
		this.transactionId = "0";
		this.userinfo = userinfo;
		this.subscriptionBenefits = subscriptionBenefits;
		this.optScreenConfig = optScreenConfig;
	}

	public OptScreenConfig getOptScreenConfig() {
		return optScreenConfig;
	}

	public void setOptScreenConfig(OptScreenConfig optScreenConfig) {
		this.optScreenConfig = optScreenConfig;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public UserInfoData getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfoData userinfo) {
		this.userinfo = userinfo;
	}

	public SubscriptionBenefits getSubscriptionBenefits() {
		return subscriptionBenefits;
	}

	public void setSubscriptionBenefits(SubscriptionBenefits subscriptionBenefits) {
		this.subscriptionBenefits = subscriptionBenefits;
	}

}
