package com.beans;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "", propOrder = { "id", "title", "name", "info", "dt",

})

public class DedicateeData {

	private int id;
	private String title;
	private String name;
	private String info;
	private String dt;

	public DedicateeData() {
	}

	public DedicateeData(int id, String title, String name, String info, String dt) {
		this.id = id;
		this.title = title;
		this.name = name;
		this.info = info;
		this.dt = dt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

}
