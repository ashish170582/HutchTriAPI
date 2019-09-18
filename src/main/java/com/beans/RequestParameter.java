/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author Rajat.kumar
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestParameter {
	@JsonProperty("id")
	private String id = "0";
	@JsonProperty("sessionId")
	private String sessionId = "";
	@JsonProperty("datetime")
	private String datetime = "";
	@ApiModelProperty(notes = "User Country Code",example="108",position=-2)
	@JsonProperty("ocid")
	private int countryId;
	
	@JsonProperty("opid")
	private int operatorId;
	@JsonProperty("tabid")
	private int tabId;
	@JsonProperty("banner")
	private int bannerTypeId;
	@JsonProperty("osid")
	private int operatingSystemId;
	@ApiModelProperty(notes = "SourceID",example="1",position=-7)
	@JsonProperty("src")
	private int sourceId;
	@JsonProperty("evt")
	private int eventId;
	@ApiModelProperty(notes = "User ID",example="1",position=-6)
	@JsonProperty("userid")
	private int userId;
	@JsonProperty("udevid")
	private int userDeviceId;
	@JsonProperty("albumid")
	private int albumId;
	@JsonProperty("artistid")
	private int artistId;
	@JsonProperty("genreid")
	private int genreId;
	@JsonProperty("xid")
	private String notificationId;
	@JsonProperty("dedicationId")
	private int dedicationId;
	@JsonProperty("transactionId")
	private String transactionId = "0";
	@JsonProperty("via")
	private int viaId;
	@JsonProperty("gender")
	private int genderId;
	@JsonProperty("playlistid")
	private int playlistId;
	@JsonProperty("radioid")
	private int radioId;
	@JsonProperty("audioTechRefId")
	private int audioTechRefId;
	@JsonProperty("imageTechRefId")
	private int imageTechRefId;
	@JsonProperty("oneTimePassword")
	private String oneTimePassword = "";
	@JsonProperty("uname")
	private String userName = "";
	@JsonProperty("fbProfileId")
	private String fbProfileId = "";
	@JsonProperty("email")
	private String emailAddress = "";
	@ApiModelProperty(notes = "User Mobile Number",example="62111111111",position=-1)
	@JsonProperty("msisdn")
	private String msisdn = "";
	@JsonProperty("dedicationMessage")
	private String dedicationMessage = "";
	@JsonProperty("oldpasswd")
	private String oldPassword = "";
	@JsonProperty("passwd")
	private String password = "";
	@ApiModelProperty(notes = "User 2 Digit Country Code",example="ID",position=-3)
	@JsonProperty("ccode")
	private String countryCode = "ID";
	@ApiModelProperty(notes = "EType",example="1",position=-1)
	@JsonProperty("etype")
	private String eventType = "0";
	@JsonProperty("trackid")
	private String trackCode = "";
	@JsonProperty("search")
	private String searchKeyword = "";
	@JsonProperty("feedbackData")
	private String feedbackData = "";
	@JsonProperty("iformat")
	private String imageFormat = "";
	@JsonProperty("size")
	private String imageSize = "";
	@JsonProperty("network")
	private String network = "";
	@JsonProperty("utype")
	private String streamingProtocol = "";
	@JsonProperty("aformat")
	private String audioFormat = "";
	@JsonProperty("quality")
	private String audioQuality = "";
	@JsonProperty("ResourceTags")
	private String responseFormat = "";
	@JsonProperty("apiversion")
	private String apiVersion = "";
	@JsonProperty("start")
	@ApiModelProperty(notes = "start limit",example="0",position=7)
	private int startLimit;
	@JsonProperty("limit")
	@ApiModelProperty(notes = "End Limit",example="10",position=7)
	private int endLimit;
	@JsonProperty("rating")
	private int rating;
	@JsonProperty("action")
	private String action = "";
	@JsonProperty("old")
	private int oldOrderId;
	@JsonProperty("new")
	private int newOrderId;
	@ApiModelProperty(notes = "User Language Code",example="en",position=-4)
	@JsonProperty("lang")
	private String languageCode;
	@JsonProperty("packid")
	private String packageId = "";
	@JsonProperty("filter")
	private String filter = "";
	@JsonProperty("promocode")
	private String promotionCode = "";
	@JsonProperty("msg")
	private String message = "";
	@JsonProperty("sender")
	private String sender = "";
	@JsonProperty("imageUrl")
	private String imageUrl = "";
	@JsonProperty("dwldquality")
	private String downloadQuality;
	@JsonProperty("utm_source")
	private String source;
	@JsonProperty("utm_medium")
	private String medium;
	@JsonProperty("utm_term")
	private String term;
	@JsonProperty("utm_content")
	private String content;
	@JsonProperty("utm_campaign")
	private String campaign;

	@JsonProperty("utm_compaignId")
	private String utm_compaignId;

	@JsonProperty("usertype")
	private String usertype;
	
	
	@ApiModelProperty(notes = "applicationVersion",example="1.0.0",position=1)
	@JsonProperty("appv")
	private String applicationVersion = "";
	@ApiModelProperty(notes = "os name",example="android",position=2)
	@JsonProperty("os")
	private String operatingSystem = "";
	@ApiModelProperty(notes = "os version",example="7.0",position=3)
	@JsonProperty("osv")
	private String operatingSystemVersion = "";
	@ApiModelProperty(notes = "device",example="1111111111111111",position=4)
	@JsonProperty("device")
	private String device = "";
	@ApiModelProperty(notes = "model",example="samsung 7.0",position=4)
	@JsonProperty("model")
	private String deviceModel = "";
	@ApiModelProperty(notes = "model",example="222222222222",position=4)
	@JsonProperty("devid")
	private String deviceId = "";
	@ApiModelProperty(notes = "model",example="8178272947318947182977129",position=4)
	@JsonProperty("devpin")
	private String devicePin = "";
	
	@JsonProperty("json")
	private String json = "";
	
	@JsonProperty("karaokeId")
	private String karaokeId;
	
	@JsonProperty("recordingMode")
	private String recordingMode;
	
	
	@JsonProperty("umgsourceid")
	private String umgsourceid;
	
	@JsonProperty("sourcetype")
	private String sourcetype;
	
	
	
	
	
	
	
	
	public String getUtm_compaignId() {
		return utm_compaignId;
	}
	public void setUtm_compaignId(String utm_compaignId) {
		this.utm_compaignId = utm_compaignId;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getUmgsourceid() {
		return umgsourceid;
	}
	public void setUmgsourceid(String umgsourceid) {
		this.umgsourceid = umgsourceid;
	}
	public String getSourcetype() {
		return sourcetype;
	}
	public void setSourcetype(String sourcetype) {
		this.sourcetype = sourcetype;
	}
	public String getKaraokeId() {
		return karaokeId;
	}
	public String getRecordingMode() {
		return recordingMode;
	}
	public void setKaraokeId(String karaokeId) {
		this.karaokeId = karaokeId;
	}
	public void setRecordingMode(String recordingMode) {
		this.recordingMode = recordingMode;
	}
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	public void setTabId(int tabId) {
		this.tabId = tabId;
	}

	public void setBannerTypeId(int bannerTypeId) {
		this.bannerTypeId = bannerTypeId;
	}

	public void setOperatingSystemId(int operatingSystemId) {
		this.operatingSystemId = operatingSystemId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserDeviceId(int userDeviceId) {
		this.userDeviceId = userDeviceId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
		try {
		this.dedicationId=Integer.parseInt(notificationId);
		}catch(NumberFormatException e) {
			this.dedicationId=0;
		}
		this.transactionId=notificationId+"";
	}
/*	public void setationId(String notificationId) {
		this.notificationId = notificationId;
		try {
		this.dedicationId=Integer.parseInt(notificationId);
		}catch(NumberFormatException e) {
			this.dedicationId=0;
		}
		this.transactionId=notificationId+"";
	}*/

	public void setDedicationId(int dedicationId) {
		this.dedicationId = dedicationId;
	}

	public void setViaId(int viaId) {
		this.viaId = viaId;
	}

	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}

	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}

	public void setRadioId(int radioId) {
		this.radioId = radioId;
	}

	public void setStartLimit(int startLimit) {
		this.startLimit = startLimit;
	}

	public void setEndLimit(int endLimit) {
		this.endLimit = endLimit;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setOldOrderId(int oldOrderId) {
		this.oldOrderId = oldOrderId;
	}

	public void setNewOrderId(int newOrderId) {
		this.newOrderId = newOrderId;
	}

	public String getDownloadQuality() {
		return downloadQuality;
	}

	public void setDownloadQuality(String downloadQuality) {
		try {
			// System.out.println("downloadQuality :: "+ downloadQuality);
			if (downloadQuality == null) {
				downloadQuality = "0";
			}
			this.downloadQuality = downloadQuality;

		} catch (Exception e) {
			this.downloadQuality = "0";
		}

	}

	public RequestParameter() {
		this.sessionId = UUID.randomUUID().toString();
		this.datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
		this.responseFormat = "json";
		// this.imageTechRefId = 120;
		this.imageTechRefId = 126;
		this.userId = 0;
		this.tabId = 1;
		this.bannerTypeId = 1;
		this.languageCode = "en";
	}

	
	
	
	
	
	public RequestParameter(String id, String sessionId, String datetime, int countryId, int operatorId, int tabId,
			int bannerTypeId, int operatingSystemId, int sourceId, int eventId, int userId, int userDeviceId,
			int albumId, int artistId, int genreId, String notificationId, int dedicationId, String transactionId,
			int viaId, int genderId, int playlistId, int radioId, int audioTechRefId, int imageTechRefId,
			String oneTimePassword, String userName, String fbProfileId, String emailAddress, String msisdn,
			String dedicationMessage, String oldPassword, String password, String countryCode, String eventType,
			String trackCode, String searchKeyword, String feedbackData, String imageFormat, String imageSize,
			String network, String streamingProtocol, String audioFormat, String audioQuality, String responseFormat,
			String apiVersion, int startLimit, int endLimit, int rating, String action, int oldOrderId, int newOrderId,
			String languageCode, String packageId, String filter, String promotionCode, String message, String sender,
			String imageUrl, String downloadQuality, String source, String medium, String term, String content,
			String campaign, String applicationVersion, String operatingSystem, String operatingSystemVersion,
			String device, String deviceModel, String deviceId, String devicePin, String json, String karaokeId,
			String recordingMode, String umgsourceid, String sourcetype) {
		super();
		this.id = id;
		this.sessionId = sessionId;
		this.datetime = datetime;
		this.countryId = countryId;
		this.operatorId = operatorId;
		this.tabId = tabId;
		this.bannerTypeId = bannerTypeId;
		this.operatingSystemId = operatingSystemId;
		this.sourceId = sourceId;
		this.eventId = eventId;
		this.userId = userId;
		this.userDeviceId = userDeviceId;
		this.albumId = albumId;
		this.artistId = artistId;
		this.genreId = genreId;
		this.notificationId = notificationId;
		this.dedicationId = dedicationId;
		this.transactionId = transactionId;
		this.viaId = viaId;
		this.genderId = genderId;
		this.playlistId = playlistId;
		this.radioId = radioId;
		this.audioTechRefId = audioTechRefId;
		this.imageTechRefId = imageTechRefId;
		this.oneTimePassword = oneTimePassword;
		this.userName = userName;
		this.fbProfileId = fbProfileId;
		this.emailAddress = emailAddress;
		this.msisdn = msisdn;
		this.dedicationMessage = dedicationMessage;
		this.oldPassword = oldPassword;
		this.password = password;
		this.countryCode = countryCode;
		this.eventType = eventType;
		this.trackCode = trackCode;
		this.searchKeyword = searchKeyword;
		this.feedbackData = feedbackData;
		this.imageFormat = imageFormat;
		this.imageSize = imageSize;
		this.network = network;
		this.streamingProtocol = streamingProtocol;
		this.audioFormat = audioFormat;
		this.audioQuality = audioQuality;
		this.responseFormat = responseFormat;
		this.apiVersion = apiVersion;
		this.startLimit = startLimit;
		this.endLimit = endLimit;
		this.rating = rating;
		this.action = action;
		this.oldOrderId = oldOrderId;
		this.newOrderId = newOrderId;
		this.languageCode = languageCode;
		this.packageId = packageId;
		this.filter = filter;
		this.promotionCode = promotionCode;
		this.message = message;
		this.sender = sender;
		this.imageUrl = imageUrl;
		this.downloadQuality = downloadQuality;
		this.source = source;
		this.medium = medium;
		this.term = term;
		this.content = content;
		this.campaign = campaign;
		this.applicationVersion = applicationVersion;
		this.operatingSystem = operatingSystem;
		this.operatingSystemVersion = operatingSystemVersion;
		this.device = device;
		this.deviceModel = deviceModel;
		this.deviceId = deviceId;
		this.devicePin = devicePin;
		this.json = json;
		this.karaokeId = karaokeId;
		this.recordingMode = recordingMode;
		this.umgsourceid = umgsourceid;
		this.sourcetype = sourcetype;
	}
	public String getId() {
		return id;
	}

	public int getNumericId() {
		try {
			int i = Integer.parseInt(id);
			return i;
		} catch (Exception e) {
			return 0;
		}

	}

	public void setId(String id) {
		if (id != null) {
			this.id = id;
		}
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		try {
			if (sessionId == null) {
				this.sessionId = "";
			} else {
				this.sessionId = sessionId;
			}
		} catch (Exception e) {
			this.sessionId = "";
		}
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		try {
			if (datetime == null) {
				this.datetime = "";
			} else {
				this.datetime = datetime;
			}
		} catch (Exception e) {
			this.datetime = "";
		}
	}

	public int getViaId() {
		return viaId;
	}

	public void setViaId(String viaId) {
		if (viaId != null) {
			this.viaId = getIntegerValue(viaId);
		} else {
			this.viaId = 0;
		}
	}

	public int getGenderId() {
		return genderId;
	}

	public void setGenderId(String genderId) {
		this.genderId = getIntegerValue(genderId);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		try {
			if (userName != null) {
				this.userName = userName.replaceAll("'", "''");
			} else {
				this.userName = "";
			}
		} catch (Exception e) {
			this.userName = "";
			System.out.println("Exception in Mziiki RequestParameter.setUserName(String userName) - " + e.getMessage());
		}
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		try {
			if (emailAddress == null) {
				this.emailAddress = "";
			} else {
				this.emailAddress = emailAddress;
			}
		} catch (Exception e) {
			this.emailAddress = "";
			System.out.println("Exception in Mziiki setEmailAddress(String emailAddress) - " + e.getMessage());
		}

	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		try {
			if (msisdn == null) {
				this.msisdn = "NA";
			} else {
				this.msisdn = msisdn;
			}
		} catch (Exception e) {
			this.msisdn = "";
		}

	}

	public String getDedicationMessage() {
		return dedicationMessage;
	}

	public void setDedicationMessage(String dedicationMessage) {
		try {
			if (dedicationMessage == null) {
				this.dedicationMessage = "";
			} else {
				this.dedicationMessage = dedicationMessage;
			}
		} catch (Exception e) {
			this.dedicationMessage = "";
		}

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		try {
			if (password == null) {
				this.password = "";
				this.oneTimePassword = "";
				this.fbProfileId = "";
			} else {
				this.password = password;
				this.oneTimePassword= password;
				this.fbProfileId= password;
			}
		} catch (Exception e) {
			this.password = "";
			this.oneTimePassword = "";
			this.fbProfileId = "";
		}

	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		try {
			if (oldPassword == null) {
				this.oldPassword = "";
			} else {
				this.oldPassword = oldPassword;
			}
		} catch (Exception e) {
			this.oldPassword = "";
		}
	}

	public String getFbProfileId() {
		return fbProfileId;
	}

	public void setFbProfileId(String fbProfileId) {
		try {
			if (fbProfileId == null) {
				this.fbProfileId = "";
			} else {
				this.fbProfileId = fbProfileId;
			}
		} catch (Exception e) {
			this.fbProfileId = "";
		}

	}

	public int getTabId() {
		return tabId;
	}

	public void setTabId(String tabId) {
		try {
			this.tabId = getIntegerValue(tabId);
		} catch (Exception e) {
			this.tabId = 1;
			System.out.println("Exception in Mziiki RequestParameter.setTabId(String tabId) - " + e.getMessage());
		}
	}

	public int getBannerTypeId() {
		return bannerTypeId;
	}

	public void setBannerTypeId(String bannerTypeId) {
		try {
			this.bannerTypeId = getIntegerValue(bannerTypeId);
		} catch (Exception e) {
			this.bannerTypeId = 2;
			System.out.println(
					"Exception in Mziiki RequestParameter.setBannerTypeId(String bannerTypeId) - " + e.getMessage());
		}
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		try {
			this.countryId = getIntegerValue(countryId);
		} catch (Exception e) {
			this.countryId = 0;
			System.out
					.println("Exception in Mziiki RequestParameter.setCountryId(String countryId) - " + e.getMessage());
		}
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		try {
			this.operatorId = getIntegerValue(operatorId);
		} catch (Exception e) {
			this.operatorId = 0;
			System.out.println(
					"Exception in Mziiki RequestParameter.setOperatorId(String operatorId) - " + e.getMessage());
		}
	}

	public int getOperatingSystemId() {
		return operatingSystemId;
	}

	public void setOperatingSystemId(String operatingSystemId) {
		try {
			this.operatingSystemId = getIntegerValue(operatingSystemId);
		} catch (Exception e) {
			this.operatingSystemId = 0;
			System.out.println("Exception in Mziiki RequestParameter.setOperatingSystemId(String operatingSystemId) - "
					+ e.getMessage());
		}
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		try {
			this.sourceId = getIntegerValue(sourceId);
		} catch (Exception e) {
			this.sourceId = 0;
			System.out.println("Exception in Mziiki RequestParameter.setSourceId(String sourceId) - " + e.getMessage());
		}
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = getIntegerValue(eventId);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = getIntegerValue(userId);
	}

	public int getUserDeviceId() {
		return userDeviceId;
	}

	public void setUserDeviceId(String userDeviceId) {
		this.userDeviceId = getIntegerValue(userDeviceId);
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = getIntegerValue(albumId);
	}

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = getIntegerValue(artistId);
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(String genreId) {
		this.genreId = getIntegerValue(genreId);
	}

	public String getNotificationId() {
		
		if(notificationId==null)
			return "0";
		else
			return notificationId;
	}

	public int getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(String playlistId) {
		this.playlistId = getIntegerValue(playlistId);
	}

	public int getRadioId() {
		return radioId;
	}

	public void setRadioId(String radioId) {
		this.radioId = getIntegerValue(radioId);
	}

	public int getDedicationId() {
		return dedicationId;
	}

	public void setDedicationId(String dedicationId) {
		this.dedicationId = getIntegerValue(dedicationId);
	}

	public int getAudioTechRefId() {
		try {
			try {
				if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("3g")) {
					this.audioTechRefId = 63;
				} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("wifi")) {
					// audioTechRefId=65;
					this.audioTechRefId = 64;
				} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("gprs")) {
					this.audioTechRefId = 112;
				} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("bis")) {
					this.audioTechRefId = 112;
				} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("3g")) {
					this.audioTechRefId = 57;
				} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("wifi")) {
					this.audioTechRefId = 56;
				} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("gprs")) {
					this.audioTechRefId = 78;
				} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("bis")) {
					this.audioTechRefId = 78;
				} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("3g")) {
					this.audioTechRefId = 67;
				} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("wifi")) {
					this.audioTechRefId = 66;
				} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("gprs")) {
					this.audioTechRefId = 111;
				} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("bis")) {
					this.audioTechRefId = 111;
				} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("high")) {
					this.audioTechRefId = 63;
				} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("medium")) {
					this.audioTechRefId = 64;
				} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("low")) {
					this.audioTechRefId = 112;
				} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("high")) {
					this.audioTechRefId = 57;
				} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("medium")) {
					this.audioTechRefId = 56;
				} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("low")) {
					this.audioTechRefId = 78;
				} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("high")) {
					this.audioTechRefId = 67;
				} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("medium")) {
					this.audioTechRefId = 66;
				} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("low")) {
					this.audioTechRefId = 111;
				} else {
					this.audioTechRefId = 63;
				}
			} catch (Exception e) {
				this.audioTechRefId = 63;
				System.out.println("Exception in Mziiki RequestParameter.setAudioTechRefId() - " + e.getMessage());
			}
		} catch (Exception e) {
			this.audioTechRefId = 64;
		}
		return audioTechRefId;
	}

	public void setAudioTechRefId(int audioTechRefId) {
		try {
			try {
				if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("3g")) {
					this.audioTechRefId = 63;
				} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("wifi")) {
					// audioTechRefId=65;
					this.audioTechRefId = 64;
				} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("gprs")) {
					this.audioTechRefId = 112;
				} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("bis")) {
					this.audioTechRefId = 112;
				} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("3g")) {
					this.audioTechRefId = 57;
				} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("wifi")) {
					this.audioTechRefId = 56;
				} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("gprs")) {
					this.audioTechRefId = 78;
				} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("bis")) {
					this.audioTechRefId = 78;
				} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("3g")) {
					this.audioTechRefId = 67;
				} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("wifi")) {
					this.audioTechRefId = 66;
				} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("gprs")) {
					this.audioTechRefId = 111;
				} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("auto")
						&& this.network.equalsIgnoreCase("bis")) {
					this.audioTechRefId = 111;
				} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("high")) {
					this.audioTechRefId = 63;
				} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("medium")) {
					this.audioTechRefId = 64;
				} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("low")) {
					this.audioTechRefId = 112;
				} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("high")) {
					this.audioTechRefId = 57;
				} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("medium")) {
					this.audioTechRefId = 56;
				} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("low")) {
					this.audioTechRefId = 78;
				} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("high")) {
					this.audioTechRefId = 67;
				} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("medium")) {
					this.audioTechRefId = 66;
				} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("low")) {
					this.audioTechRefId = 111;
				} else {
					this.audioTechRefId = 63;
				}
			} catch (Exception e) {
				this.audioTechRefId = 63;
				System.out.println("Exception in Mziiki RequestParameter.setAudioTechRefId() - " + e.getMessage());
			}
		} catch (Exception e) {
			this.audioTechRefId = 64;
		}
	}

	public int getImageTechRefId() {
		return imageTechRefId;
	}

	public void setImageTechRefId(int imageTechRefId) {
		try {
			this.imageTechRefId = imageTechRefId;
		} catch (Exception e) {
			this.imageTechRefId = 0;
		}
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		try {
			if (transactionId == null) {
				this.transactionId = "0";
			} else {
				this.transactionId = transactionId;
			}
		} catch (Exception e) {
			this.transactionId = "0";
		}
	}

	public String getOneTimePassword() {
		return oneTimePassword;
	}

	public void setOneTimePassword(String oneTimePassword) {
		try {
			if (oneTimePassword == null) {
				this.oneTimePassword = "";
			} else {
				this.oneTimePassword = oneTimePassword;
			}
		} catch (Exception e) {
			this.oneTimePassword = "";
		}
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		try {
			if (apiVersion == null) {
				this.apiVersion = "0";
			} else {
				this.apiVersion = apiVersion;
			}
		} catch (Exception e) {
			this.apiVersion = "0";
		}

	}

	public int getStartLimit() {
		return startLimit;
	}

	public void setStartLimit(String startLimit) {
		try {
			this.startLimit = getIntegerValue(startLimit);
			if (this.startLimit < 0) {
				this.startLimit = 0;
			}
		} catch (Exception e) {
			this.startLimit = 0;
			System.out.println(
					"Exception in Mziiki RequestParameter.setStartLimit(String startLimit) - " + e.getMessage());
		}

	}

	public int getEndLimit() {
		return endLimit;
	}

	public void setEndLimit(String endLimit) {
		try {
			this.endLimit = getIntegerValue(endLimit);
			if (this.endLimit < 0) {
				this.endLimit = 50;
			}
		} catch (Exception e) {
			this.endLimit = 50;
			System.out.println("Exception in Mziiki RequestParameter.setEndLimit(String endLimit) - " + e.getMessage());
		}
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		try {
			if (countryCode == null || countryCode.trim().length() > 3 || countryCode.trim().length() < 2) {
				this.countryCode = "WW";
			} else {
				this.countryCode = countryCode;
			}
		} catch (Exception e) {
			this.countryCode = "WW";
			System.out.println(
					"Exception in Mziiki RequestParameter.setCountryCode(String countryCode) - " + e.getMessage());
		}

	}

	public String getEventType() {
		if (eventType == null) {
			eventType = "0";
		}
		return eventType;
	}

	public void setEventType(String eventType) {
		try {

			if (eventType == null || eventType.trim().length() <= 0) {
				this.eventType = "1";
			} else {
				this.eventType = eventType;
			}
		} catch (Exception e) {
			this.eventType = "1";
			System.out
					.println("Exception in Mziiki RequestParameter.setEventType(String eventType) - " + e.getMessage());
		}

	}

	public String getTrackCode() {
		return trackCode;
	}

	public void setTrackCode(String trackCode) {
		try {
			if ((trackCode != null)) {
				this.trackCode = trackCode;	
			}else {
				this.trackCode = "0000000000";
			}
			
//			if ((trackCode != null) && (trackCode.trim().length() == 10)) {
//				this.trackCode = trackCode;
//			} else {
//				
//			}
		} catch (Exception e) {
			this.trackCode = "0000000000";
		}

	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		try {
			if (searchKeyword != null) {
				this.searchKeyword = searchKeyword.replaceAll("'", "''");
			} else {
				this.searchKeyword = "a";
			}
		} catch (Exception e) {
			this.searchKeyword = "a";
			System.out.println(
					"Exception in Mziiki RequestParameter.setSearchKeyword(String searchKeyword) - " + e.getMessage());
		}
	}

	public String getFeedbackData() {
		return feedbackData;
	}

	public void setFeedbackData(String feedbackData) {
		try {
			if (feedbackData == null) {
				this.feedbackData = "";
			} else {
				this.feedbackData = feedbackData;
			}
		} catch (Exception e) {
			this.feedbackData = "";
		}
	}

	public String getImageFormat() {
		return imageFormat;
	}

	public void setImageFormat(String imageFormat) {
		try {
			if (imageFormat == null) {
				this.imageFormat = "jpg";
			} else {
				this.imageFormat = imageFormat;
			}
		} catch (Exception e) {
			this.imageFormat = "jpg";
		}
	}

	public String getImageSize() {
		return imageSize;
	}

	public void setImageSize(String imageSize) {
		try {
			this.imageSize = imageSize;
			if (imageSize == null) {
				// this.imageTechRefId = 119;
				this.imageTechRefId = 125;
			} else if (imageSize.equalsIgnoreCase("XS")) {
				// this.imageTechRefId = 119;
				this.imageTechRefId = 125;
			} else if (imageSize.equalsIgnoreCase("S")) {
				// this.imageTechRefId = 120;
				this.imageTechRefId = 126;
			} else if (imageSize.equalsIgnoreCase("M")) {
				// this.imageTechRefId = 121;
				this.imageTechRefId = 127;
			} else if (imageSize.equalsIgnoreCase("L")) {
				// this.imageTechRefId = 122;
				this.imageTechRefId = 128;
			} else if (imageSize.equalsIgnoreCase("XL")) {
				// this.imageTechRefId = 122;
				this.imageTechRefId = 128;
			} else if (imageSize.equalsIgnoreCase("XXL")) {
				// this.imageTechRefId = 122;
				this.imageTechRefId = 128;
			} else {
				// this.imageTechRefId = 120;
				this.imageTechRefId = 126;
			}
		} catch (Exception e) {
			this.imageTechRefId = 126;
			System.out
					.println("Exception in Mziiki RequestParameter.setImageSize(String imageSize) - " + e.getMessage());
		}
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		try {
			if (network == null) {
				this.network = "gprs";
			} else {
				this.network = network;
			}
		} catch (Exception e) {
			this.network = "gprs";
			System.out.println("Exception in Mziiki RequestParameter.setNetwork(String network) - " + e.getMessage());
		}
	}

	public String getStreamingProtocol() {
		return streamingProtocol;
	}

	public void setStreamingProtocol(String streamingProtocol) {
		try {
			if (streamingProtocol == null) {
				this.streamingProtocol = "http";
			} else {
				this.streamingProtocol = streamingProtocol;
			}
		} catch (Exception e) {
			this.streamingProtocol = "http";
		}
	}

	public String getAudioFormat() {
        if (this.getAudioFormat().equalsIgnoreCase("m4a")) {
            this.setAudioFormat("mp4");
        }
		return audioFormat;
	}

	public void setAudioFormat(String audioFormat) {
		try {
			if (audioFormat == null) {
				this.audioFormat = "m4a";
			} else {
				this.audioFormat = audioFormat;
			}
		} catch (Exception e) {
			this.audioFormat = "m4a";
			System.out.println(
					"Exception in Mziiki RequestParameter.setAudioFormat(String audioFormat) - " + e.getMessage());
		}
	}

	public String getAudioQuality() {
		return audioQuality;
	}

	public void setAudioQuality(String audioQuality) {
		try {
			if (audioQuality == null) {
				this.audioQuality = "auto";
			} else {
				this.audioQuality = audioQuality;
			}
		} catch (Exception e) {
			this.audioQuality = "auto";
			System.out.println(
					"Exception in Mziiki RequestParameter.setAudioQuality(String audioQuality) - " + e.getMessage());
		}
	}

	public String getResponseFormat() {
		return responseFormat;
	}

	public void setResponseFormat(String responseFormat) {
		try {
			if (responseFormat == null) {
				this.responseFormat = "json";
				this.imageUrl = "";
			} else {
				this.responseFormat = responseFormat;
				this.imageUrl = responseFormat;
			}
		} catch (Exception e) {
			this.responseFormat = "json";
			System.out.println("Exception in Mziiki RequestParameter.setResponseFormat(String responseFormat) - "
					+ e.getMessage());
		}

	}

	public void setAudioTechRefId() {
		try {
			if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("auto")
					&& this.network.equalsIgnoreCase("3g")) {
				this.audioTechRefId = 63;
			} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("auto")
					&& this.network.equalsIgnoreCase("wifi")) {
				// audioTechRefId=65;
				this.audioTechRefId = 64;
			} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("auto")
					&& this.network.equalsIgnoreCase("gprs")) {
				this.audioTechRefId = 112;
			} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("auto")
					&& this.network.equalsIgnoreCase("bis")) {
				this.audioTechRefId = 112;
			} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("auto")
					&& this.network.equalsIgnoreCase("3g")) {
				this.audioTechRefId = 57;
			} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("auto")
					&& this.network.equalsIgnoreCase("wifi")) {
				this.audioTechRefId = 56;
			} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("auto")
					&& this.network.equalsIgnoreCase("gprs")) {
				this.audioTechRefId = 78;
			} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("auto")
					&& this.network.equalsIgnoreCase("bis")) {
				this.audioTechRefId = 78;
			} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("auto")
					&& this.network.equalsIgnoreCase("3g")) {
				this.audioTechRefId = 67;
			} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("auto")
					&& this.network.equalsIgnoreCase("wifi")) {
				this.audioTechRefId = 66;
			} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("auto")
					&& this.network.equalsIgnoreCase("gprs")) {
				this.audioTechRefId = 111;
			} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("auto")
					&& this.network.equalsIgnoreCase("bis")) {
				this.audioTechRefId = 111;
			} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("high")) {
				this.audioTechRefId = 63;
			} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("medium")) {
				this.audioTechRefId = 64;
			} else if (this.audioFormat.equalsIgnoreCase("m4a") && this.audioQuality.equalsIgnoreCase("low")) {
				this.audioTechRefId = 112;
			} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("high")) {
				this.audioTechRefId = 57;
			} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("medium")) {
				this.audioTechRefId = 56;
			} else if (this.audioFormat.equalsIgnoreCase("mp3") && this.audioQuality.equalsIgnoreCase("low")) {
				this.audioTechRefId = 78;
			} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("high")) {
				this.audioTechRefId = 67;
			} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("medium")) {
				this.audioTechRefId = 66;
			} else if (this.audioFormat.equalsIgnoreCase("3gp") && this.audioQuality.equalsIgnoreCase("low")) {
				this.audioTechRefId = 111;
			} else {
				this.audioTechRefId = 63;
			}
		} catch (Exception e) {
			this.audioTechRefId = 63;
			System.out.println("Exception in Mziiki RequestParameter.setAudioTechRefId() - " + e.getMessage());
		}
	}

	public static int getIntegerValue(String str) {
		int i = -1;
		try {
			if (str == null) {
				i = -1;
			} else {
				i = Integer.parseInt(str);
			}
		} catch (NumberFormatException nfe) {
			i = -1;
		} catch (Exception e) {
			i = -1;
		}
		return i;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		try {
			if (action == null) {
				this.action = "";
			} else {
				this.action = action;
			}
		} catch (Exception e) {
			this.action = "";
		}

	}

	public int getRating() {
		return rating;
	}

	public void setRating(String rating) {
		try {
			this.rating = Integer.parseInt(rating);
		} catch (NumberFormatException nfe) {
			this.rating = 0;
		}
	}

	public int getOldOrderId() {
		return oldOrderId;
	}

	public void setOldOrderId(String oldOrderId) {
		try {
			this.oldOrderId = Integer.parseInt(oldOrderId);
		} catch (NumberFormatException nfe) {
			this.oldOrderId = 0;
		}
	}

	public int getNewOrderId() {
		return newOrderId;
	}

	public void setNewOrderId(String newOrderId) {
		try {
			this.newOrderId = Integer.parseInt(newOrderId);
		} catch (NumberFormatException nfe) {
			this.newOrderId = 0;
		}
	}

	public String getLanguageCode() {
			return languageCode;
	}

	public void setLanguageCode(String languageCode) {

		if(languageCode!=null && languageCode.equalsIgnoreCase("id")) {
			languageCode="in";
		}
		try {
			if (languageCode == null || languageCode.trim().length() < 2 || languageCode.startsWith("en")) {
				this.languageCode = "en";
			} else if (languageCode.startsWith("en")) {
				this.languageCode = "en";
			} else
				this.languageCode = languageCode;
		} catch (Exception e) {
			this.languageCode = "en";
		}

	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		try {
			if (packageId == null || packageId.trim().length() <= 0) {
				this.packageId = "5";
			} else {
				this.packageId = packageId;
			}
		} catch (Exception e) {
			this.packageId = "5";
		}
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		try {
			if (filter == null || filter.trim().length() <= 0) {
				this.filter = "1,2,3,4,5,6,7,8,9,10";
			} else {
				this.filter = filter;
			}
		} catch (Exception e) {
			this.filter = "1,2,3,4,5,6,7,8,9,10";
		}
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		if (promotionCode != null) {
			this.promotionCode = promotionCode;
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		try {
			if (message == null || message.trim().length() <= 0) {
				this.message = "";
				this.feedbackData = "";
				this.dedicationMessage = "";
			} else {
				this.message = message;
				this.feedbackData=message;
				this.dedicationMessage=message;
			}
		} catch (Exception e) {
			this.message = "";
			this.feedbackData = "";
			this.dedicationMessage = "";
		}
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		try {
			if (sender == null || sender.trim().length() <= 0) {
				this.sender = "InfoSMS";
			} else {
				this.sender = sender;
			}
		} catch (Exception e) {
			this.sender = "InfoSMS";
		}

	}

	@SuppressWarnings("finally")
	public String getImageUrl() {
		try {
			imageUrl = java.net.URLDecoder.decode(imageUrl, "UTF-8");
		} catch (Exception ex) {
			Logger.getLogger(RequestParameter.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			return imageUrl;
		}
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	
	// Device Information --
	
	
	public String getApplicationVersion() {
		return applicationVersion;
	}

	public void setApplicationVersion(String applicationVersion) {
		if (applicationVersion == null || applicationVersion.equalsIgnoreCase("-")) {
			this.applicationVersion = "1.0.0";
		} else {
			this.applicationVersion = applicationVersion;
		}
		
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		if (operatingSystem != null) {
			this.operatingSystem = operatingSystem;
		} else {
			this.operatingSystem = "Unknown";
		}

	}

	public String getOperatingSystemVersion() {
		return operatingSystemVersion;
	}

	public void setOperatingSystemVersion(String operatingSystemVersion) {
		if (operatingSystemVersion != null) {
			this.operatingSystemVersion = operatingSystemVersion;
		} else {
			this.operatingSystemVersion = "0.0.0";
		}
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		if (device != null) {
			this.device = device;
		} else {
			this.device = "Unknown";
		}
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		if (deviceModel != null) {
			this.deviceModel = deviceModel;
		} else {
			this.deviceModel = "Unknown";
		}
	}

	@SuppressWarnings("finally")
	public String getDeviceId() {
		try {
			if (deviceId.length() > 0 && deviceId.length() < 50) {
				return this.deviceId;
			} else if (deviceId.length() > 50) {
				this.deviceId = deviceId.substring(0, 50);
			} else {
				this.deviceId = "";
			}
		} catch (Exception e) {
			deviceId = "";
		} finally {
			return deviceId;
		}

	}

	public void setDeviceId(String deviceId) {
		if (deviceId != null) {
			this.deviceId = deviceId;
		} else {
			this.deviceId = "NotAvailable";
		}
	}

	@SuppressWarnings("finally")
	public String getDevicePin() {
		try {
			if (devicePin.length() > 0 && devicePin.length() < 350) {
				return this.devicePin;
			} else if (devicePin.length() > 350) {
				this.devicePin = devicePin.substring(0, 350);
			} else {
				this.devicePin = "NotAvailable";
			}

			// if (devicePin.length() > 350) {
			// devicePin = devicePin.substring(0, 350);
			// } else {
			// devicePin = "NotAvailable";
			// }
		} catch (Exception e) {
			devicePin = "NotAvailable";
		} finally {
			return devicePin;
		}
	}

	public void setDevicePin(String devicePin) {
		if (devicePin != null) {
			this.devicePin = devicePin;
		} else {
			this.devicePin = "NotAvailable";
		}
	}
	@Override
	public String toString() {
		return "RequestParameter [id=" + id + ", sessionId=" + sessionId + ", datetime=" + datetime + ", countryId="
				+ countryId + ", operatorId=" + operatorId + ", tabId=" + tabId + ", bannerTypeId=" + bannerTypeId
				+ ", operatingSystemId=" + operatingSystemId + ", sourceId=" + sourceId + ", eventId=" + eventId
				+ ", userId=" + userId + ", userDeviceId=" + userDeviceId + ", albumId=" + albumId + ", artistId="
				+ artistId + ", genreId=" + genreId + ", notificationId=" + notificationId + ", dedicationId="
				+ dedicationId + ", transactionId=" + transactionId + ", viaId=" + viaId + ", genderId=" + genderId
				+ ", playlistId=" + playlistId + ", radioId=" + radioId + ", audioTechRefId=" + audioTechRefId
				+ ", imageTechRefId=" + imageTechRefId + ", oneTimePassword=" + oneTimePassword + ", userName="
				+ userName + ", fbProfileId=" + fbProfileId + ", emailAddress=" + emailAddress + ", msisdn=" + msisdn
				+ ", dedicationMessage=" + dedicationMessage + ", oldPassword=" + oldPassword + ", password=" + password
				+ ", countryCode=" + countryCode + ", eventType=" + eventType + ", trackCode=" + trackCode
				+ ", searchKeyword=" + searchKeyword + ", feedbackData=" + feedbackData + ", imageFormat=" + imageFormat
				+ ", imageSize=" + imageSize + ", network=" + network + ", streamingProtocol=" + streamingProtocol
				+ ", audioFormat=" + audioFormat + ", audioQuality=" + audioQuality + ", responseFormat="
				+ responseFormat + ", apiVersion=" + apiVersion + ", startLimit=" + startLimit + ", endLimit="
				+ endLimit + ", rating=" + rating + ", action=" + action + ", oldOrderId=" + oldOrderId
				+ ", newOrderId=" + newOrderId + ", languageCode=" + languageCode + ", packageId=" + packageId
				+ ", filter=" + filter + ", promotionCode=" + promotionCode + ", message=" + message + ", sender="
				+ sender + ", imageUrl=" + imageUrl + ", downloadQuality=" + downloadQuality + ", source=" + source
				+ ", medium=" + medium + ", term=" + term + ", content=" + content + ", campaign=" + campaign
				+ ", utm_compaignId=" + utm_compaignId + ", usertype=" + usertype + ", applicationVersion="
				+ applicationVersion + ", operatingSystem=" + operatingSystem + ", operatingSystemVersion="
				+ operatingSystemVersion + ", device=" + device + ", deviceModel=" + deviceModel + ", deviceId="
				+ deviceId + ", devicePin=" + devicePin + ", json=" + json + ", karaokeId=" + karaokeId
				+ ", recordingMode=" + recordingMode + ", umgsourceid=" + umgsourceid + ", sourcetype=" + sourcetype
				+ "]";
	}

	
}
