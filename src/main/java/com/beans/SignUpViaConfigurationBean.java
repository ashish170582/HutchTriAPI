package com.beans;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "", propOrder = { "radioId", "radioName", "radioDescription", "radioImageUrl" })

public class SignUpViaConfigurationBean {

	private int email;
	private int facebook;
	private int google;
	private int mobileNumber;

	public SignUpViaConfigurationBean() {
	}

	public SignUpViaConfigurationBean(int email, int facebook, int google, int mobileNumber) {
		this.email = email;
		this.facebook = facebook;
		this.google = google;
		this.mobileNumber = mobileNumber;
	}

	public int getEmail() {
		return email;
	}

	public void setEmail(int email) {
		this.email = email;
	}

	public int getFacebook() {
		return facebook;
	}

	public void setFacebook(int facebook) {
		this.facebook = facebook;
	}

	public int getGoogle() {
		return google;
	}

	public void setGoogle(int google) {
		this.google = google;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
