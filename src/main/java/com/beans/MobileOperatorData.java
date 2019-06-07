package com.beans;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "", propOrder = { "id", "operator", })

public class MobileOperatorData {

	private int id;
	private String operator;

	public MobileOperatorData() {
	}

	public MobileOperatorData(int id, String operator) {
		this.id = id;
		this.operator = operator;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
