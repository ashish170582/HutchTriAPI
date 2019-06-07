/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author Rajat.kumar
 */

public class KaraokeCommentData {

    private int id;
    private int karaokeId;
    private long userId;
    private String comment;
    private String commentDateTime;
    private String commentUpdateDateTime;
    private String isUpdated;
    private String userName;
    private String image;
    
public KaraokeCommentData() {
	// TODO Auto-generated constructor stub
}

public KaraokeCommentData(int id, int karaokeId, long userId, String comment,
		String commentDateTime, String commentUpdateDateTime, String isUpdated,
		String userName, String image) {
	super();
	this.id = id;
	this.karaokeId = karaokeId;
	this.userId = userId;
	this.comment = comment;
	this.commentDateTime = commentDateTime;
	this.commentUpdateDateTime = commentUpdateDateTime;
	this.isUpdated = isUpdated;
	this.userName = userName;
	this.image = image;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getKaraokeId() {
	return karaokeId;
}
public void setKaraokeId(int karaokeId) {
	this.karaokeId = karaokeId;
}

public long getUserId() {
	return userId;
}

public void setUserId(long userId) {
	this.userId = userId;
}

public String getComment() {
	return comment;
}

public void setComment(String comment) {
	this.comment = comment;
}

public String getCommentDateTime() {
	return commentDateTime;
}

public void setCommentDateTime(String commentDateTime) {
	this.commentDateTime = commentDateTime;
}

public String getCommentUpdateDateTime() {
	return commentUpdateDateTime;
}

public void setCommentUpdateDateTime(String commentUpdateDateTime) {
	this.commentUpdateDateTime = commentUpdateDateTime;
}

public String getIsUpdated() {
	return isUpdated;
}

public void setIsUpdated(String isUpdated) {
	this.isUpdated = isUpdated;
}
 public String getUserName() {
	return userName;
}
 public void setUserName(String userName) {
	this.userName = userName;
}

public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}

@Override
public String toString() {
	return "KaraokeCommentData [id=" + id + ", karaokeId=" + karaokeId
			+ ", userId=" + userId + ", comment=" + comment
			+ ", commentDateTime=" + commentDateTime
			+ ", commentUpdateDateTime=" + commentUpdateDateTime
			+ ", isUpdated=" + isUpdated + ", userName=" + userName + ", image="
			+ image + "]";
}

}
