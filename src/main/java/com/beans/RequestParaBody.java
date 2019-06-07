package com.beans;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestParaBody {
private RequestParameter requestParameter;
private DeviceInformation deviceInfo;
public RequestParaBody() {
	// TODO Auto-generated constructor stub
}
public RequestParaBody(RequestParameter requestParameter, DeviceInformation deviceInfo) {
	super();
	this.requestParameter = requestParameter;
	this.deviceInfo = deviceInfo;
}
public RequestParameter getRequestParameter() {
	return requestParameter;
}
public void setRequestParameter(RequestParameter requestParameter) {
	this.requestParameter = requestParameter;
}
public DeviceInformation getDeviceInfo() {
	return deviceInfo;
}
public void setDeviceInfo(DeviceInformation deviceInfo) {
	this.deviceInfo = deviceInfo;
}
@Override
public String toString() {
	return "RequestBody [requestParameter=" + requestParameter + ", deviceInfo=" + deviceInfo + "]";
}
}
