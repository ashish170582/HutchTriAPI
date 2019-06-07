/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author Rajat.kumar
 */

public class FollowKaraokeArtistData {

    private long userId;
    private String userName;
    private String image;
    private Boolean isArtistFollowed;
    
    
public FollowKaraokeArtistData() {
	// TODO Auto-generated constructor stub
}


public FollowKaraokeArtistData(long userId, String userName, String image, Boolean isArtistFollowed) {
	super();
	this.userId = userId;
	this.userName = userName;
	this.image = image;
	this.isArtistFollowed = isArtistFollowed;
}


public long getUserId() {
	return userId;
}


public void setUserId(long userId) {
	this.userId = userId;
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


public Boolean getIsArtistFollowed() {
	return isArtistFollowed;
}


public void setIsArtistFollowed(Boolean isArtistFollowed) {
	this.isArtistFollowed = isArtistFollowed;
}


@Override
public String toString() {
	return "FollowKaraokeArtistData [userId=" + userId + ", userName=" + userName + ", image=" + image
			+ ", isArtistFollowed=" + isArtistFollowed + "]";
}
 

}
