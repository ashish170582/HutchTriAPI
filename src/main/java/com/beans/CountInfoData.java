/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Rajat.kumar
 */
@XmlType(name = "", propOrder = { "userid", "count", "dt" })
public class CountInfoData {

	private int userid;
	private int count;
	private String dt;
	private int userTypeId;
	private int userStatus;
	private int playCounter;
	private String popUpMsg = "";
	private int popUpAction;
	private int deviceStatus;
	private String deviceStatusMsg;
	private long timestamp; 
	public CountInfoData() {
		this.dt = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}

	public CountInfoData(int userid, int count, int userTypeId, int userStatus, int playCounter, int deviceStatus,
			String deviceStatusMsg,long timestamp) {
		this.userid = userid;
		this.count = count;
		this.dt = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		this.userTypeId = userTypeId;
		this.userStatus = userStatus;
		this.playCounter = playCounter;
		this.deviceStatusMsg = deviceStatusMsg;
		this.deviceStatus = deviceStatus;
		// if (playCounter <= 0) {
		//
		// this.popUpMsg = "Y’ello! Subscribe to MTN Radio and get 3 days of free
		// subscription and enjoy listening to Unlimited HD full tracks, without any ads
		// and also enjoy Offline downloads, where you can hear songs without
		// internet.";
		// this.popUpAction = 2;
		// }
		// else {
		// this.popUpMsg = "";
		// this.popUpAction = 0;
		// }

		this.popUpMsg = "";
		this.popUpAction = 0;
		this.timestamp=timestamp;

	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public void setDeviceStatusMsg(String deviceStatusMsg) {
		this.deviceStatusMsg = deviceStatusMsg;
	}

	public void setDeviceStatus(int deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getDeviceStatusMsg() {
		return deviceStatusMsg;
	}

	public int getDeviceStatus() {
		return deviceStatus;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
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

	public int getPlayCounter() {
		return playCounter;
	}

	public void setPlayCounter(int playCounter) {
		this.playCounter = playCounter;
		if (playCounter <= 0) {
			this.popUpMsg = "Y’ello! Subscribe to MTN Radio and get 3 days of free subscription and enjoy listening to Unlimited HD full tracks, without any ads and also enjoy Offline downloads, where you can hear songs without internet.";
			this.popUpAction = 1;
		} else {
			this.popUpMsg = "";
			this.popUpAction = 0;
		}
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

}
