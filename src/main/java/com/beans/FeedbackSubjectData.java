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
@XmlType(name = "", propOrder = { "id", "subject" })
public class FeedbackSubjectData {

	private int id;
	private String subject;

	public FeedbackSubjectData() {
	}

	public FeedbackSubjectData(int id, String subject) {
		this.id = id;
		this.subject = subject;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
