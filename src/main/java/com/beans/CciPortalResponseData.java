package com.beans;

import java.util.List;

public class CciPortalResponseData {
private List<CciPortalResponse>data;

public CciPortalResponseData() {
	// TODO Auto-generated constructor stub
}

public CciPortalResponseData(List<CciPortalResponse> data) {
	super();
	this.data = data;
}

public List<CciPortalResponse> getData() {
	return data;
}

public void setData(List<CciPortalResponse> data) {
	this.data = data;
}

@Override
public String toString() {
	return "CciPortalResponseData [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
			+ super.toString() + "]";
}
}
