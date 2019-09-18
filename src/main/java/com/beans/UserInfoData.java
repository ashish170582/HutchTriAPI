/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import javax.xml.bind.annotation.XmlType;

import org.springframework.stereotype.Component;

import com.app.beans.SubscribedPackage;

/**
 *
 * @author Rajat.kumar
 */
@XmlType(name = "", propOrder = { "userid", "name", "msisdn", "email", "gender", "password", "image", "substatus",
		"registered" })
@Component
public class UserInfoData {

	private int userid;
	private String name;
	private String msisdn;
	private String email;
	private int gender;
	private String password;
	private String image;
	private int userTypeId;
	private int userStatus;
	private int actionId;
	private int registered;
	private int mobileNumberVerification;
	private int playCounter;
	private String billingMobileNumber;
	private String dialingCode;
	private int mobileNumberLength;
	private String promotionCode;

	private String popUpMsg = "";
	private int popUpAction;

	private SubscribedPackage subscribedPackage;

	public UserInfoData() {
	}

	public UserInfoData(int userid, String promoCode, String name, String msisdn, String email, int gender,
			String password, String image, int userTypeId, int userStatus, int actionId, int registered,
			int mobileNumberVerification, String billingMobileNumber, String dialingCode, int mobileNumberLength,
			SubscribedPackage subscribedPackage) {
		this.userid = userid;
		this.promotionCode = promoCode;
		this.name = name;
		this.msisdn = msisdn;
		this.email = email;
		this.gender = gender;
		this.password = password;
		this.image = image;
		this.userTypeId = userTypeId;
		this.userStatus = userStatus;
		this.actionId = actionId;
		this.registered = registered;
		this.mobileNumberVerification = mobileNumberVerification;
		this.billingMobileNumber = billingMobileNumber;
		this.dialingCode = dialingCode;
		this.mobileNumberLength = mobileNumberLength;
		this.subscribedPackage = subscribedPackage;

	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public int getRegistered() {
		return registered;
	}

	public void setRegistered(int registered) {
		this.registered = registered;
	}

	public int getMobileNumberVerification() {
		return mobileNumberVerification;
	}

	public void setMobileNumberVerification(int mobileNumberVerification) {
		this.mobileNumberVerification = mobileNumberVerification;
	}

	public String getBillingMobileNumber() {
		return billingMobileNumber;
	}

	public void setBillingMobileNumber(String billingMobileNumber) {
		this.billingMobileNumber = billingMobileNumber;
	}

	public String getDialingCode() {
		return dialingCode;
	}

	public void setDialingCode(String dialingCode) {
		this.dialingCode = dialingCode;
	}

	public int getMobileNumberLength() {
		return mobileNumberLength;
	}

	public void setMobileNumberLength(int mobileNumberLength) {
		this.mobileNumberLength = mobileNumberLength;
	}

	public SubscribedPackage getSubscribedPackage() {
		return subscribedPackage;
	}

	public void setSubscribedPackage(SubscribedPackage subscribedPackage) {
		this.subscribedPackage = subscribedPackage;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public int getPlayCounter() {
		return playCounter;
	}

	public void setPlayCounter(int playCounter) {
		this.playCounter = playCounter;
		// if (playCounter <= 0)
		// {
		// this.popUpMsg = "Y’ello! Subscribe to MTN Radio and get 3 days of free
		// subscription and enjoy listening to Unlimited HD full tracks, without any ads
		// and also enjoy Offline downloads, where you can hear songs without
		// internet.";
		// this.popUpAction = 2;
		// } else {
		// this.popUpMsg = "";
		// this.popUpAction = 0;
		// }
		this.popUpMsg = "";
		this.popUpAction = 0;

	}

	public String getPopUpMsg() {
		return popUpMsg;
	}

	public void setPopUpMsg(String popUpMsg) {
		this.popUpMsg = popUpMsg;
	}

	public int getPopUpAction() {
		return popUpAction;
	}

	public void setPopUpAction(int popUpAction) {
		this.popUpAction = popUpAction;
	}

	@Override
	public String toString() {
		return "UserInfoData [userid=" + userid + ", name=" + name + ", msisdn=" + msisdn + ", email=" + email
				+ ", gender=" + gender + ", password=" + password + ", image=" + image + ", userTypeId=" + userTypeId
				+ ", userStatus=" + userStatus + ", actionId=" + actionId + ", registered=" + registered
				+ ", mobileNumberVerification=" + mobileNumberVerification + ", playCounter=" + playCounter
				+ ", billingMobileNumber=" + billingMobileNumber + ", dialingCode=" + dialingCode
				+ ", mobileNumberLength=" + mobileNumberLength + ", promotionCode=" + promotionCode + ", popUpMsg="
				+ popUpMsg + ", popUpAction=" + popUpAction + ", subscribedPackage=" + subscribedPackage + "]";
	}

	
	
}
