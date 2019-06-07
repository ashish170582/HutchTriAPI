package com.services;

import org.springframework.stereotype.Service;

import com.app.beans.RootResponse;
import com.beans.RequestParameter;


@Service
public interface SubscriptionServices {

	RootResponse crbtRequest(RequestParameter reqParam);

	RootResponse getSubPackage(RequestParameter reqParam);

	RootResponse subscription(RequestParameter reqParam);

	RootResponse unSubscription(RequestParameter reqParam);

	RootResponse activateSubscription(RequestParameter reqParam);

}
