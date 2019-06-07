package com.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.beans.RootResponse;
import com.beans.DeviceInformationData;
import com.beans.RequestParameter;
import com.beans.Root;


@Service
public interface UserDetailsServices {
	
	
	Object checkLogin(RequestParameter reqParam);

	Object SignOut(RequestParameter reqParam);

	Object userinfo(RequestParameter reqParam);

	Object updateuserinfo(RequestParameter reqParam);

	RootResponse getFavouriteTrackList(RequestParameter reqParam);

	RootResponse addFavouriteTrack(RequestParameter reqParam);

	RootResponse removeFavouriteTrack(RequestParameter reqParam);

	RootResponse getNotificationFavouriteCount(RequestParameter reqParam);

	RootResponse getUserNotification(RequestParameter reqParam);

	RootResponse getUserFavourite(RequestParameter reqParam);

	RootResponse changeUserLanguage(RequestParameter reqParam);

	DeviceInformationData getUserDeviceInformation(RequestParameter reqParam);

	Object userDeviceLoginInformation(RequestParameter reqParam);

	RootResponse uploadUserImage(HttpServletRequest request, HttpServletResponse respnse, MultipartFile file);

}
