package com.beans;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "", propOrder = { "countryid", "countryname", "dialingcode", "mobnolength", "code2digit", "code3digit",
		"userdevid", "audioadversion", "audioadurl", "splashversion", "splashurl", "logourl", "mlogourl" })
public class CountryInfoData {

	private int countryid;
	private String countryname;
	private String dialingcode;
	private String mobnolength;
	private String code2digit;
	private String code3digit;
	private String languageCode;
	private int userdevid;
	private int audioAdFirstTime = -1;
	private int audioAdFrequency = 1000;
	private int audioadversion;
	private String audioadurl;
	private int splashversion;
	private String splashurl;
	private String logourl;
	private String mlogourl;
	private String iosThemeColour;
	private int subscriptionAvailable;
	private int downloadAvailable;
	private boolean isDataCacheEnable;
	private int crbtAvailable;
	private String subscriptionUrl;
	private int subLinkOpenAction;
	private String shareUrl;
	private String msisdnHeaderUrl;
	private String songOptionsSequence;
	private int songPreviewDuration;
	private boolean showStickyIcon;
	private boolean showLanguageChangePopup;
	private boolean loginRequiredAll;
	private boolean loginRequiredShare;
	private boolean loginRequiredCrbt;
	private boolean loginRequiredPlayAudio;
	private boolean loginRequiredPlayVideo;
	private String cli;
	private int otpLength = 4;
	private int billingPopUpEnabled;
	private int promotionalPageEnabled;
	private String newLogoUrl;

	private boolean isSocialScreenEnable;
	private boolean loginRequiredDownload;
	private boolean loginRequiredOffline;
	private boolean loginRequiredFavourite;
	private boolean subscriptionRequiredShare;
	private boolean subscriptionRequiredCrbt;

	private boolean subscriptionRequiredPlayAudio;;
	private boolean subscriptionRequiredPlayVideo;
	private boolean subscriptionRequiredDownload;
	private boolean subscriptionRequiredOffline;
	private boolean subscriptionRequiredFavourite;
	private boolean uploadMusicVisibility;
	private int likeSongPopup;
	private int offlineDownloadPopup;
	private String leftMenuButtonTitle;
	private String leftMenuButtonTitle_SW;
	private String artistURL;
	private String msisdnHeaderFinalUrl;

	private boolean optScreenVisibility;
	private Boolean isPromoCodeScreen;
	private Boolean isKaraokeAvailable;

	
	
	public Boolean getIsKaraokeAvailable() {
		return isKaraokeAvailable;
	}
	public void setIsKaraokeAvailable(Boolean isKaraokeAvailable) {
		this.isKaraokeAvailable = isKaraokeAvailable;
	}
	public boolean isDataCacheEnable() {
		return isDataCacheEnable;
	}

	public void setDataCacheEnable(boolean isDataCacheEnable) {
		this.isDataCacheEnable = isDataCacheEnable;
	}

	public boolean isSocialScreenEnable() {
		return isSocialScreenEnable;
	}

	public void setSocialScreenEnable(boolean isSocialScreenEnable) {
		this.isSocialScreenEnable = isSocialScreenEnable;
	}

	public Boolean getIsPromoCodeScreen() {
		return isPromoCodeScreen;
	}

	public void setIsPromoCodeScreen(Boolean isPromoCodeScreen) {
		this.isPromoCodeScreen = isPromoCodeScreen;
	}

	public boolean isOptScreenVisibility() {
		return optScreenVisibility;
	}

	public void setOptScreenVisibility(boolean optScreenVisibility) {
		this.optScreenVisibility = optScreenVisibility;
	}

	public String getMsisdnHeaderFinalUrl() {
		return msisdnHeaderFinalUrl;
	}

	public void setMsisdnHeaderFinalUrl(String msisdnHeaderFinalUrl) {
		this.msisdnHeaderFinalUrl = msisdnHeaderFinalUrl;
	}

	private int otpPosition;

	public int getOtpPosition() {
		return otpPosition;
	}

	public void setOtpPosition(int otpPosition) {
		this.otpPosition = otpPosition;
	}

	public boolean isIsSocialScreenEnable() {
		return isSocialScreenEnable;
	}

	public void setIsSocialScreenEnable(boolean isSocialScreenEnable) {
		this.isSocialScreenEnable = isSocialScreenEnable;
	}

	public boolean isLoginRequiredDownload() {
		return loginRequiredDownload;
	}

	public void setLoginRequiredDownload(boolean loginRequiredDownload) {
		this.loginRequiredDownload = loginRequiredDownload;
	}

	public boolean isLoginRequiredOffline() {
		return loginRequiredOffline;
	}

	public void setLoginRequiredOffline(boolean loginRequiredOffline) {
		this.loginRequiredOffline = loginRequiredOffline;
	}

	public boolean isLoginRequiredFavourite() {
		return loginRequiredFavourite;
	}

	public void setLoginRequiredFavourite(boolean loginRequiredFavourite) {
		this.loginRequiredFavourite = loginRequiredFavourite;
	}

	public boolean isSubscriptionRequiredShare() {
		return subscriptionRequiredShare;
	}

	public void setSubscriptionRequiredShare(boolean subscriptionRequiredShare) {
		this.subscriptionRequiredShare = subscriptionRequiredShare;
	}

	public boolean isSubscriptionRequiredCrbt() {
		return subscriptionRequiredCrbt;
	}

	public void setSubscriptionRequiredCrbt(boolean subscriptionRequiredCrbt) {
		this.subscriptionRequiredCrbt = subscriptionRequiredCrbt;
	}

	public boolean isSubscriptionRequiredPlayAudio() {
		return subscriptionRequiredPlayAudio;
	}

	public void setSubscriptionRequiredPlayAudio(boolean subscriptionRequiredPlayAudio) {
		this.subscriptionRequiredPlayAudio = subscriptionRequiredPlayAudio;
	}

	public boolean isSubscriptionRequiredPlayVideo() {
		return subscriptionRequiredPlayVideo;
	}

	public void setSubscriptionRequiredPlayVideo(boolean subscriptionRequiredPlayVideo) {
		this.subscriptionRequiredPlayVideo = subscriptionRequiredPlayVideo;
	}

	public boolean isSubscriptionRequiredDownload() {
		return subscriptionRequiredDownload;
	}

	public void setSubscriptionRequiredDownload(boolean subscriptionRequiredDownload) {
		this.subscriptionRequiredDownload = subscriptionRequiredDownload;
	}

	public boolean isSubscriptionRequiredOffline() {
		return subscriptionRequiredOffline;
	}

	public void setSubscriptionRequiredOffline(boolean subscriptionRequiredOffline) {
		this.subscriptionRequiredOffline = subscriptionRequiredOffline;
	}

	public boolean isSubscriptionRequiredFavourite() {
		return subscriptionRequiredFavourite;
	}

	public void setSubscriptionRequiredFavourite(boolean subscriptionRequiredFavourite) {
		this.subscriptionRequiredFavourite = subscriptionRequiredFavourite;
	}

	public boolean isUploadMusicVisibility() {
		return uploadMusicVisibility;
	}

	public void setUploadMusicVisibility(boolean uploadMusicVisibility) {
		this.uploadMusicVisibility = uploadMusicVisibility;
	}

	public int getLikeSongPopup() {
		return likeSongPopup;
	}

	public void setLikeSongPopup(int likeSongPopup) {
		this.likeSongPopup = likeSongPopup;
	}

	public int getOfflineDownloadPopup() {
		return offlineDownloadPopup;
	}

	public void setOfflineDownloadPopup(int offlineDownloadPopup) {
		this.offlineDownloadPopup = offlineDownloadPopup;
	}

	public String getLeftMenuButtonTitle() {
		return leftMenuButtonTitle;
	}

	public void setLeftMenuButtonTitle(String leftMenuButtonTitle) {
		this.leftMenuButtonTitle = leftMenuButtonTitle;
	}

	public String getLeftMenuButtonTitle_SW() {
		return leftMenuButtonTitle_SW;
	}

	public void setLeftMenuButtonTitle_SW(String leftMenuButtonTitle_SW) {
		this.leftMenuButtonTitle_SW = leftMenuButtonTitle_SW;
	}

	public String getArtistURL() {
		return artistURL;
	}

	public void setArtistURL(String artistURL) {
		this.artistURL = artistURL;
	}

	public String getNewLogoUrl() {
		return newLogoUrl;
	}

	public void setNewLogoUrl(String newLogoUrl) {
		this.newLogoUrl = newLogoUrl;
	}

	public int getCountryid() {
		return countryid;
	}

	public void setCountryid(int countryid) {
		this.countryid = countryid;
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public String getDialingcode() {
		return dialingcode;
	}

	public void setDialingcode(String dialingcode) {
		this.dialingcode = dialingcode;
	}

	public String getMobnolength() {
		return mobnolength;
	}

	public void setMobnolength(String mobnolength) {
		this.mobnolength = mobnolength;
	}

	public String getCode2digit() {
		return code2digit;
	}

	public void setCode2digit(String code2digit) {
		this.code2digit = code2digit;
	}

	public String getCode3digit() {
		return code3digit;
	}

	public void setCode3digit(String code3digit) {
		this.code3digit = code3digit;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public int getUserdevid() {
		return userdevid;
	}

	public void setUserdevid(int userdevid) {
		this.userdevid = userdevid;
	}

	public int getAudioAdFirstTime() {
		return audioAdFirstTime;
	}

	public void setAudioAdFirstTime(int audioAdFirstTime) {
		this.audioAdFirstTime = audioAdFirstTime;
	}

	public int getAudioAdFrequency() {
		return audioAdFrequency;
	}

	public void setAudioAdFrequency(int audioAdFrequency) {
		this.audioAdFrequency = audioAdFrequency;
	}

	public int getAudioadversion() {
		return audioadversion;
	}

	public void setAudioadversion(int audioadversion) {
		this.audioadversion = audioadversion;
	}

	public String getAudioadurl() {
		return audioadurl;
	}

	public void setAudioadurl(String audioadurl) {
		this.audioadurl = audioadurl;
	}

	public int getSplashversion() {
		return splashversion;
	}

	public void setSplashversion(int splashversion) {
		this.splashversion = splashversion;
	}

	public String getSplashurl() {
		return splashurl;
	}

	public void setSplashurl(String splashurl) {
		this.splashurl = splashurl;
	}

	public String getLogourl() {
		return logourl;
	}

	public void setLogourl(String logourl) {
		this.logourl = logourl;
	}

	public String getMlogourl() {
		return mlogourl;
	}

	public void setMlogourl(String mlogourl) {
		this.mlogourl = mlogourl;
	}

	public String getIosThemeColour() {
		return iosThemeColour;
	}

	public void setIosThemeColour(String iosThemeColour) {
		this.iosThemeColour = iosThemeColour;
	}

	public int getSubscriptionAvailable() {
		return subscriptionAvailable;
	}

	public void setSubscriptionAvailable(int subscriptionAvailable) {
		this.subscriptionAvailable = subscriptionAvailable;
	}

	public int getDownloadAvailable() {
		return downloadAvailable;
	}

	public void setDownloadAvailable(int downloadAvailable) {
		this.downloadAvailable = downloadAvailable;
	}

	public String getSubscriptionUrl() {
		return subscriptionUrl;
	}

	public void setSubscriptionUrl(String subscriptionUrl) {
		this.subscriptionUrl = subscriptionUrl;
	}

	public int getSubLinkOpenAction() {
		return subLinkOpenAction;
	}

	public void setSubLinkOpenAction(int subLinkOpenAction) {
		this.subLinkOpenAction = subLinkOpenAction;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public String getMsisdnHeaderUrl() {
		return msisdnHeaderUrl;
	}

	public void setMsisdnHeaderUrl(String msisdnHeaderUrl) {
		this.msisdnHeaderUrl = msisdnHeaderUrl;
	}

	public String getSongOptionsSequence() {
		return songOptionsSequence;
	}

	public void setSongOptionsSequence(String songOptionsSequence) {
		this.songOptionsSequence = songOptionsSequence;
	}

	public int getSongPreviewDuration() {
		return songPreviewDuration;
	}

	public void setSongPreviewDuration(int songPreviewDuration) {
		this.songPreviewDuration = songPreviewDuration;
	}

	public boolean isShowStickyIcon() {
		return showStickyIcon;
	}

	public void setShowStickyIcon(boolean showStickyIcon) {
		this.showStickyIcon = showStickyIcon;
	}

	public boolean isShowLanguageChangePopup() {
		return showLanguageChangePopup;
	}

	public void setShowLanguageChangePopup(boolean showLanguageChangePopup) {
		this.showLanguageChangePopup = showLanguageChangePopup;
	}

	public boolean isLoginRequiredAll() {
		return loginRequiredAll;
	}

	public void setLoginRequiredAll(boolean loginRequiredAll) {
		this.loginRequiredAll = loginRequiredAll;
	}

	public boolean isLoginRequiredShare() {
		return loginRequiredShare;
	}

	public void setLoginRequiredShare(boolean loginRequiredShare) {
		this.loginRequiredShare = loginRequiredShare;
	}

	public boolean isLoginRequiredCrbt() {
		return loginRequiredCrbt;
	}

	public void setLoginRequiredCrbt(boolean loginRequiredCrbt) {
		this.loginRequiredCrbt = loginRequiredCrbt;
	}

	public boolean isLoginRequiredPlayAudio() {
		return loginRequiredPlayAudio;
	}

	public void setLoginRequiredPlayAudio(boolean loginRequiredPlayAudio) {
		this.loginRequiredPlayAudio = loginRequiredPlayAudio;
	}

	public boolean isLoginRequiredPlayVideo() {
		return loginRequiredPlayVideo;
	}

	public void setLoginRequiredPlayVideo(boolean loginRequiredPlayVideo) {
		this.loginRequiredPlayVideo = loginRequiredPlayVideo;
	}

	public String getCli() {
		return cli;
	}

	public void setCli(String cli) {
		this.cli = cli;
	}

	public int getOtpLength() {
		return otpLength;
	}

	public void setOtpLength(int otpLength) {
		this.otpLength = otpLength;
	}

	public int getBillingPopUpEnabled() {
		return billingPopUpEnabled;
	}

	public void setBillingPopUpEnabled(int billingPopUpEnabled) {
		this.billingPopUpEnabled = billingPopUpEnabled;
	}

	public int getPromotionalPageEnabled() {
		return promotionalPageEnabled;
	}

	public void setPromotionalPageEnabled(int promotionalPageEnabled) {
		this.promotionalPageEnabled = promotionalPageEnabled;
	}

	public boolean isIsDataCacheEnable() {
		return isDataCacheEnable;
	}

	public void setIsDataCacheEnable(boolean isDataCacheEnable) {
		this.isDataCacheEnable = isDataCacheEnable;
	}

	public int getCrbtAvailable() {
		return crbtAvailable;
	}

	public void setCrbtAvailable(int crbtAvailable) {
		this.crbtAvailable = crbtAvailable;
	}

}
