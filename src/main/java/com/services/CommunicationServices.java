package com.services;

import org.springframework.stereotype.Service;

import com.app.beans.RootResponse;
import com.beans.RequestParameter;


@Service
public interface CommunicationServices {

	RootResponse readNotification(RequestParameter reqParam);

	RootResponse userFeedback(RequestParameter reqParam);

	RootResponse socialSharingTrack(RequestParameter reqParam);

	RootResponse feedbackSubjectList(RequestParameter reqParam);

	RootResponse getNotificationInfo(RequestParameter reqParam);

	RootResponse sendOtp(RequestParameter reqParam);

	RootResponse resendOtp(RequestParameter reqParam);

	RootResponse varifyOtp(RequestParameter reqParam);

	

}
