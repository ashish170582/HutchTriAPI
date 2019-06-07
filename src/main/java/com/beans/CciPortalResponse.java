package com.beans;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
public class CciPortalResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String datetime;
	private String mobile_number;
	private String remarks;
	private String packName;
	private String price;
	private String status;
	private String packageStartDate;
	private String packageEndtDate;
	private String packageRenewDate;
	private String lastSuccessBilling;
	private String unsubDate;
	
public CciPortalResponse() {
	// TODO Auto-generated constructor stub
}

public CciPortalResponse(String datetime, String mobile_number, String remarks, String packName, String price) {
	super();
	this.datetime = datetime;
	this.mobile_number = mobile_number;
	this.remarks = remarks;
	this.packName = packName;
	this.price = price;
}

public CciPortalResponse(String datetime, String mobile_number, String remarks, String packName, String price,
		String status, String packageStartDate, String packageEndtDate, String packageRenewDate,
		String lastSuccessBilling) {
	super();
	this.datetime = datetime;
	this.mobile_number = mobile_number;
	this.remarks = remarks;
	this.packName = packName;
	this.price = price;
	this.status = status;
	this.packageStartDate = packageStartDate;
	this.packageEndtDate = packageEndtDate;
	this.packageRenewDate = packageRenewDate;
	this.lastSuccessBilling = lastSuccessBilling;
}

public CciPortalResponse(String datetime, String mobile_number, String remarks, String packName, String price,
		String status, String packageStartDate, String packageEndtDate, String packageRenewDate,
		String lastSuccessBilling, String unsubDate) {
	super();
	this.datetime = datetime;
	this.mobile_number = mobile_number;
	this.remarks = remarks;
	this.packName = packName;
	this.price = price;
	this.status = status;
	this.packageStartDate = packageStartDate;
	this.packageEndtDate = packageEndtDate;
	this.packageRenewDate = packageRenewDate;
	this.lastSuccessBilling = lastSuccessBilling;
	this.unsubDate = unsubDate;
}

public String getDatetime() {
	return datetime;
}

public void setDatetime(String datetime) {
	this.datetime = datetime;
}

public String getMobile_number() {
	return mobile_number;
}

public void setMobile_number(String mobile_number) {
	this.mobile_number = mobile_number;
}

public String getRemarks() {
	return remarks;
}

public void setRemarks(String remarks) {
	this.remarks = remarks;
}

public String getPackName() {
	return packName;
}

public void setPackName(String packName) {
	this.packName = packName;
}

public String getPrice() {
	return price;
}

public void setPrice(String price) {
	this.price = price;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getPackageStartDate() {
	return packageStartDate;
}

public void setPackageStartDate(String packageStartDate) {
	this.packageStartDate = packageStartDate;
}

public String getPackageEndtDate() {
	return packageEndtDate;
}

public void setPackageEndtDate(String packageEndtDate) {
	this.packageEndtDate = packageEndtDate;
}

public String getPackageRenewDate() {
	return packageRenewDate;
}

public void setPackageRenewDate(String packageRenewDate) {
	this.packageRenewDate = packageRenewDate;
}

public String getLastSuccessBilling() {
	return lastSuccessBilling;
}

public void setLastSuccessBilling(String lastSuccessBilling) {
	this.lastSuccessBilling = lastSuccessBilling;
}

public String getUnsubDate() {
	return unsubDate;
}

public void setUnsubDate(String unsubDate) {
	this.unsubDate = unsubDate;
}

@Override
public String toString() {
	return "CciPortalResponse [datetime=" + datetime + ", mobile_number=" + mobile_number + ", remarks=" + remarks
			+ ", packName=" + packName + ", price=" + price + ", status=" + status + ", packageStartDate="
			+ packageStartDate + ", packageEndtDate=" + packageEndtDate + ", packageRenewDate=" + packageRenewDate
			+ ", lastSuccessBilling=" + lastSuccessBilling + ", unsubDate=" + unsubDate + "]";
}
}
