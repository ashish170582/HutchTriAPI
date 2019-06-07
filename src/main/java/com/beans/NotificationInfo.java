/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author Rajat.kumar
 */
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "notification", propOrder = { "notification" })

@XmlRootElement(name = "root")
public class NotificationInfo extends Root {

	private NotificationData notification;

	public NotificationInfo() {
		super(0, "Success");
	}

	public NotificationInfo(int code, String message) {
		super(code, message);
	}

	public NotificationInfo(NotificationData notification) {
		super(0, "Success");
		this.notification = notification;
	}

	public NotificationData getNotification() {
		return notification;
	}

	public void setNotification(NotificationData notification) {
		this.notification = notification;
	}

}
