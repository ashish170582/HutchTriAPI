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

@XmlType(name = "feedbacksubject", propOrder = { "feedbacksubject" })

@XmlRootElement(name = "root")
public class FeedbackSubject extends Root {
	private List<FeedbackSubjectData> feedbacksubject;

	public FeedbackSubject() {
		super(0, "Success");
	}

	public FeedbackSubject(List<FeedbackSubjectData> feedbacksubject) {
		super(0, "Success");
		this.feedbacksubject = feedbacksubject;
	}

	public List<FeedbackSubjectData> getFeedbacksubject() {
		return feedbacksubject;
	}

	public void setFeedbacksubject(List<FeedbackSubjectData> feedbacksubject) {
		this.feedbacksubject = feedbacksubject;
	}

}
