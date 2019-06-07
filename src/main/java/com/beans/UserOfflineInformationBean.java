package com.beans;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "", propOrder = { "totalCredits", "availableCredits", "usedCredits" })

public class UserOfflineInformationBean extends Root {

	private int totalCredits;
	private int availableCredits;
	private int usedCredits;
	private String message1;
	private String message2;

	public UserOfflineInformationBean() {
		super(0, "Sucess");
	}

	public UserOfflineInformationBean(int code, String message, int totalCredits, int availableCredits, int usedCredits,
			String message1, String message2) {
		super(code, message);
		this.totalCredits = totalCredits;
		this.availableCredits = availableCredits;
		this.usedCredits = usedCredits;
		this.message1 = message1;
		this.message2 = message2;
	}

	public int getTotalCredits() {
		return totalCredits;
	}

	public void setTotalCredits(int totalCredits) {
		this.totalCredits = totalCredits;
	}

	public int getAvailableCredits() {
		return availableCredits;
	}

	public void setAvailableCredits(int availableCredits) {
		this.availableCredits = availableCredits;
	}

	public int getUsedCredits() {
		return usedCredits;
	}

	public void setUsedCredits(int usedCredits) {
		this.usedCredits = usedCredits;
	}

	public String getMessage1() {
		return message1;
	}

	public void setMessage1(String message1) {
		this.message1 = message1;
	}

	public String getMessage2() {
		return message2;
	}

	public void setMessage2(String message2) {
		this.message2 = message2;
	}

}
