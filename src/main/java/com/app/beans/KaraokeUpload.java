package com.app.beans;

public class KaraokeUpload {
	private String karaokeId;
	private String resourceCode;
	private Long  userId;
	private String score;
	private String recordingMode;
	private String singerType;
	private String recordType;
	private Boolean isJoin;
	private String timstamp;
	public KaraokeUpload() {
		// TODO Auto-generated constructor stub
	}
	public KaraokeUpload(String karaokeId, String resourceCode, Long userId, String score, String recordingMode,
			String singerType, String recordType, Boolean isJoin, String timstamp) {
		super();
		this.karaokeId = karaokeId;
		this.resourceCode = resourceCode;
		this.userId = userId;
		this.score = score;
		this.recordingMode = recordingMode;
		this.singerType = singerType;
		this.recordType = recordType;
		this.isJoin = isJoin;
		this.timstamp = timstamp;
	}
	public String getKaraokeId() {
		return karaokeId;
	}
	public void setKaraokeId(String karaokeId) {
		this.karaokeId = karaokeId;
	}
	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getRecordingMode() {
		return recordingMode;
	}
	public void setRecordingMode(String recordingMode) {
		this.recordingMode = recordingMode;
	}
	public String getSingerType() {
		return singerType;
	}
	public void setSingerType(String singerType) {
		this.singerType = singerType;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public Boolean getIsJoin() {
		return isJoin;
	}
	public void setIsJoin(Boolean isJoin) {
		this.isJoin = isJoin;
	}
	public String getTimstamp() {
		return timstamp;
	}
	public void setTimstamp(String timstamp) {
		this.timstamp = timstamp;
	}
	@Override
	public String toString() {
		return "KaraokeUpload [karaokeId=" + karaokeId + ", resourceCode=" + resourceCode + ", userId=" + userId
				+ ", score=" + score + ", recordingMode=" + recordingMode + ", singerType=" + singerType
				+ ", recordType=" + recordType + ", isJoin=" + isJoin + ", timstamp=" + timstamp + "]";
	}
	
	}
