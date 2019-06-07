package com.services;

import org.springframework.stereotype.Service;

import com.app.beans.RootResponse;
import com.beans.RequestParameter;


@Service
public interface AppConfigServices {

	Object countryinfo(RequestParameter reqParam);

	RootResponse hikeKeyBoardItem(RequestParameter reqParam);

	RootResponse applicationInstallViaShare(RequestParameter reqParam);

	RootResponse applicationInstallViaShareClickTracking(RequestParameter reqParam);

	void sendNotification(String notificationID, String devPin, String lang, String type, String userId);

	RootResponse changeLanguage(RequestParameter reqParam);

	RootResponse PromotionShareConfig(RequestParameter reqParam);
	
}
