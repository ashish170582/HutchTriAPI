/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Rajat.kumar
 */
@XmlType(name = "", propOrder = { "id", "heading", "notification", "value", "ntype", "read", "dt", "image" })
public class NotificationData {

	private int id;
	private String sendername;
	private String title;
	private String subtitle;
	private String notification;
	private String value;
	private String ntype;
	private int read;
	private String dt;
	private String image;

	public NotificationData() {
	}

	public NotificationData(int id, String sendername, String title, String subtitle, String notification, String value,
			String ntype, int read, String dt, String image) {
		this.id = id;
		this.sendername = sendername;
		this.title = title;
		this.subtitle = subtitle;
		this.notification = notification;
		this.value = value;
		this.ntype = ntype;
		this.read = read;
		this.dt = dt;
		this.image = image;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSendername() {
		return sendername;
	}

	public void setSendername(String sendername) {
		this.sendername = sendername;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getNtype() {
		return ntype;
	}

	public void setNtype(String ntype) {
		this.ntype = ntype;
	}

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
