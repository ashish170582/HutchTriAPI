package com.beans;
import java.io.Serializable;
import java.util.Arrays;

import javax.swing.text.AbstractDocument.Content;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
public class karaokeTrackData  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int karaokeId;
	private String rcode; 
	private String title; 
	private String artistId; 
	private String artistTitle; 
	private String firstUserId; 
	private String secondUserId; 
	private String singerType;
	private String recordingMode;
	private String recordingType;
	private Boolean isJoin;
	private String media;
	private String image;
	private String 	score;
    private Boolean isCrbtAvailable;
    private Boolean isKaraokeAvailable;
    private String UserName;
    private int firstKaraokeId;
    private Boolean isArtistFollowed;
    private Boolean isReadyToPlay;
    private String karaokeLang[]; 
public karaokeTrackData() {
	// TODO Auto-generated constructor stub
}
public karaokeTrackData(int karaokeId, String rcode, String title, String artistId, String artistTitle,
		String firstUserId, String secondUserId, String singerType, String recordingMode, String recordingType,
		Boolean isJoin, String media, String image, String score, Boolean isCrbtAvailable, Boolean isKaraokeAvailable,
		String userName, int firstKaraokeId, Boolean isArtistFollowed, Boolean isReadyToPlay, String[] karaokeLang) {
	super();
	this.karaokeId = karaokeId;
	this.rcode = rcode;
	this.title = title;
	this.artistId = artistId;
	this.artistTitle = artistTitle;
	this.firstUserId = firstUserId;
	this.secondUserId = secondUserId;
	this.singerType = singerType;
	this.recordingMode = recordingMode;
	this.recordingType = recordingType;
	this.isJoin = isJoin;
	this.media = media;
	this.image = image;
	this.score = score;
	this.isCrbtAvailable = isCrbtAvailable;
	this.isKaraokeAvailable = isKaraokeAvailable;
	UserName = userName;
	this.firstKaraokeId = firstKaraokeId;
	this.isArtistFollowed = isArtistFollowed;
	this.isReadyToPlay = isReadyToPlay;
	this.karaokeLang = karaokeLang;
}
public int getKaraokeId() {
	return karaokeId;
}
public void setKaraokeId(int karaokeId) {
	this.karaokeId = karaokeId;
}
public String getRcode() {
	return rcode;
}
public void setRcode(String rcode) {
	this.rcode = rcode;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getArtistId() {
	return artistId;
}
public void setArtistId(String artistId) {
	this.artistId = artistId;
}
public String getArtistTitle() {
	return artistTitle;
}
public void setArtistTitle(String artistTitle) {
	this.artistTitle = artistTitle;
}
public String getFirstUserId() {
	return firstUserId;
}
public void setFirstUserId(String firstUserId) {
	this.firstUserId = firstUserId;
}
public String getSecondUserId() {
	return secondUserId;
}
public void setSecondUserId(String secondUserId) {
	this.secondUserId = secondUserId;
}
public String getSingerType() {
	return singerType;
}
public void setSingerType(String singerType) {
	this.singerType = singerType;
}
public String getRecordingMode() {
	return recordingMode;
}
public void setRecordingMode(String recordingMode) {
	this.recordingMode = recordingMode;
}
public String getRecordingType() {
	return recordingType;
}
public void setRecordingType(String recordingType) {
	this.recordingType = recordingType;
}
public Boolean getIsJoin() {
	return isJoin;
}
public void setIsJoin(Boolean isJoin) {
	this.isJoin = isJoin;
}
public String getMedia() {
	return media;
}
public void setMedia(String media) {
	this.media = media;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getScore() {
	return score;
}
public void setScore(String score) {
	this.score = score;
}
public Boolean getIsCrbtAvailable() {
	return isCrbtAvailable;
}
public void setIsCrbtAvailable(Boolean isCrbtAvailable) {
	this.isCrbtAvailable = isCrbtAvailable;
}
public Boolean getIsKaraokeAvailable() {
	return isKaraokeAvailable;
}
public void setIsKaraokeAvailable(Boolean isKaraokeAvailable) {
	this.isKaraokeAvailable = isKaraokeAvailable;
}
public String getUserName() {
	return UserName;
}
public void setUserName(String userName) {
	UserName = userName;
}
public int getFirstKaraokeId() {
	return firstKaraokeId;
}
public void setFirstKaraokeId(int firstKaraokeId) {
	this.firstKaraokeId = firstKaraokeId;
}
public Boolean getIsArtistFollowed() {
	return isArtistFollowed;
}
public void setIsArtistFollowed(Boolean isArtistFollowed) {
	this.isArtistFollowed = isArtistFollowed;
}
public Boolean getIsReadyToPlay() {
	return isReadyToPlay;
}
public void setIsReadyToPlay(Boolean isReadyToPlay) {
	this.isReadyToPlay = isReadyToPlay;
}
public String[] getKaraokeLang() {
	return karaokeLang;
}
public void setKaraokeLang(String[] karaokeLang) {
	this.karaokeLang = karaokeLang;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}

@Override
public String toString() {
	return "karaokeTrackData [karaokeId=" + karaokeId + ", rcode=" + rcode + ", title=" + title + ", artistId="
			+ artistId + ", artistTitle=" + artistTitle + ", firstUserId=" + firstUserId + ", secondUserId="
			+ secondUserId + ", singerType=" + singerType + ", recordingMode=" + recordingMode + ", recordingType="
			+ recordingType + ", isJoin=" + isJoin + ", media=" + media + ", image=" + image + ", score=" + score
			+ ", isCrbtAvailable=" + isCrbtAvailable + ", isKaraokeAvailable=" + isKaraokeAvailable + ", UserName="
			+ UserName + ", firstKaraokeId=" + firstKaraokeId + ", isArtistFollowed=" + isArtistFollowed
			+ ", isReadyToPlay=" + isReadyToPlay + ", karaokeLang=" + Arrays.toString(karaokeLang) + "]";
}

}
