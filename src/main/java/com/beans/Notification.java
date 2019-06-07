/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author Rajat.kumar
 */
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "notification", propOrder = { "notification" })

@XmlRootElement(name = "root")
public class Notification extends Root {
	private List<NotificationData> notification;

	public Notification() {
		super(0, "Success");
	}

	public Notification(List<NotificationData> notification) {
		super(0, "Success");
		this.notification = notification;
	}

	public List<NotificationData> getNotification() {
		return notification;
	}

	public void setNotification(List<NotificationData> notification) {
		this.notification = notification;
	}

}
