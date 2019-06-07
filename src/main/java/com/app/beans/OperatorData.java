package com.app.beans;

import java.io.Serializable;

public class OperatorData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	String dateTime;
	String packName;
	String subType;
	String price;
	int days;
	int totalCount;
	String revenue;
	
	
	
	
	public OperatorData() {
		super();
	}
	public OperatorData(String dateTime, String packName, String subType, String price, int days, int totalCount,
			String revenue) {
		super();
		this.dateTime = dateTime;
		this.packName = packName;
		this.subType = subType;
		this.price = price;
		this.days = days;
		this.totalCount = totalCount;
		this.revenue = revenue;
	}
	@Override
	public String toString() {
		return "OperatorData [dateTime=" + dateTime + ", packName=" + packName + ", subType=" + subType + ", price="
				+ price + ", days=" + days + ", totalCount=" + totalCount + ", revenue=" + revenue + "]";
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getPackName() {
		return packName;
	}
	public void setPackName(String packName) {
		this.packName = packName;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getRevenue() {
		return revenue;
	}
	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}
	
	
	
	

}
