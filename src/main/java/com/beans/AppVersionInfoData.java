package com.beans;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "", propOrder = { "android", "ios", "blackberry", "blackberry10", "windows", "cache" })
public class AppVersionInfoData {

	private String android;
	private String android_update_msg;
	private String android_tab;
	private String android_tab_update_msg;
	private String ios;
	private String ios_update_msg;
	private String blackberry;
	private String blackberry_update_msg;
	private String blackberry10;
	private String blackberry10_update_msg;
	private String windows;
	private String windows_update_msg;
	private int update_popup_frequency;
	private String cache;
	private int updateRequired;// 1-Not Required | 2 - Minor Update |3 - Major Update
	private String updateMessage;

	public AppVersionInfoData() {
	}

	public AppVersionInfoData(String android, String android_update_msg, String android_tab,
			String android_tab_update_msg, String ios, String ios_update_msg, String blackberry,
			String blackberry_update_msg, String blackberry10, String blackberry10_update_msg, String windows,
			String windows_update_msg, int update_popup_frequency, String cache, int updateRequired,
			String updateMessage) {
		this.android = android;
		this.android_update_msg = android_update_msg;
		this.android_tab = android_tab;
		this.android_tab_update_msg = android_tab_update_msg;
		this.ios = ios;
		this.ios_update_msg = ios_update_msg;
		this.blackberry = blackberry;
		this.blackberry_update_msg = blackberry_update_msg;
		this.blackberry10 = blackberry10;
		this.blackberry10_update_msg = blackberry10_update_msg;
		this.windows = windows;
		this.windows_update_msg = windows_update_msg;
		this.update_popup_frequency = update_popup_frequency;
		this.cache = cache;
		this.updateRequired = updateRequired;
		this.updateMessage = updateMessage;
	}

	public String getAndroid() {
		return android;
	}

	public void setAndroid(String android) {
		this.android = android;
	}

	public String getAndroid_update_msg() {
		return android_update_msg;
	}

	public void setAndroid_update_msg(String android_update_msg) {
		this.android_update_msg = android_update_msg;
	}

	public String getAndroid_tab() {
		return android_tab;
	}

	public void setAndroid_tab(String android_tab) {
		this.android_tab = android_tab;
	}

	public String getAndroid_tab_update_msg() {
		return android_tab_update_msg;
	}

	public void setAndroid_tab_update_msg(String android_tab_update_msg) {
		this.android_tab_update_msg = android_tab_update_msg;
	}

	public String getIos() {
		return ios;
	}

	public void setIos(String ios) {
		this.ios = ios;
	}

	public String getIos_update_msg() {
		return ios_update_msg;
	}

	public void setIos_update_msg(String ios_update_msg) {
		this.ios_update_msg = ios_update_msg;
	}

	public String getBlackberry() {
		return blackberry;
	}

	public void setBlackberry(String blackberry) {
		this.blackberry = blackberry;
	}

	public String getBlackberry_update_msg() {
		return blackberry_update_msg;
	}

	public void setBlackberry_update_msg(String blackberry_update_msg) {
		this.blackberry_update_msg = blackberry_update_msg;
	}

	public String getBlackberry10() {
		return blackberry10;
	}

	public void setBlackberry10(String blackberry10) {
		this.blackberry10 = blackberry10;
	}

	public String getBlackberry10_update_msg() {
		return blackberry10_update_msg;
	}

	public void setBlackberry10_update_msg(String blackberry10_update_msg) {
		this.blackberry10_update_msg = blackberry10_update_msg;
	}

	public String getWindows() {
		return windows;
	}

	public void setWindows(String windows) {
		this.windows = windows;
	}

	public String getWindows_update_msg() {
		return windows_update_msg;
	}

	public void setWindows_update_msg(String windows_update_msg) {
		this.windows_update_msg = windows_update_msg;
	}

	public int getUpdate_popup_frequency() {
		return update_popup_frequency;
	}

	public void setUpdate_popup_frequency(int update_popup_frequency) {
		this.update_popup_frequency = update_popup_frequency;
	}

	public String getCache() {
		return cache;
	}

	public void setCache(String cache) {
		this.cache = cache;
	}

	public int getUpdateRequired() {
		return updateRequired;
	}

	public void setUpdateRequired(int updateRequired) {
		this.updateRequired = updateRequired;
	}

	public String getUpdateMessage() {
		return updateMessage;
	}

	public void setUpdateMessage(String updateMessage) {
		this.updateMessage = updateMessage;
	}

}
