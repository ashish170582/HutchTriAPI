/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.app.beans.UserSubscriptionStatus;

/**
 *
 * @author ashish.vishwakarma
 */
@XmlType(name = "userSubscriptionStatusInfo", propOrder = { "userSubscriptionStatusInfo" })

@XmlRootElement(name = "root")
public class UserSubscriptionStatusInfo extends Root {

	private CountInfoData countinfo;
	private UserSubscriptionStatus userSubscriptionStatus;

	public UserSubscriptionStatusInfo(CountInfoData countinfo, UserSubscriptionStatus userSubscriptionStatus) {
		super(0, "Success");
		this.countinfo = countinfo;
		this.userSubscriptionStatus = userSubscriptionStatus;
	}

	public CountInfoData getCountinfo() {
		return countinfo;
	}

	public void setCountinfo(CountInfoData countinfo) {
		this.countinfo = countinfo;
	}

	public UserSubscriptionStatus getUserSubscriptionStatus() {
		return userSubscriptionStatus;
	}

	public void setUserSubscriptionStatus(UserSubscriptionStatus userSubscriptionStatus) {
		this.userSubscriptionStatus = userSubscriptionStatus;
	}

}
