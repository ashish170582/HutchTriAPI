package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.RequestParameter;
import com.services.SubscriptionServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(tags="Subscription" ,description="EVT (45,74,75,76,82)")
public class SubscriptionController {
@Autowired
SubscriptionServices subscriptionServices;
	
	@ApiOperation(value = "CRBT_REQUEST  Evt(45)", response = Iterable.class)
	@RequestMapping(value = "/crbtRequest", method = RequestMethod.POST)
	public @ResponseBody Object crbtRequest(
			@RequestBody RequestParameter crbtRequest) {
	     	return   subscriptionServices.crbtRequest(crbtRequest);
	}
	
	@ApiOperation(value = "GET SUB PACKAGE  Evt(74)", response = Iterable.class)
	@RequestMapping(value = "/getSubPackage", method = RequestMethod.POST)
	public @ResponseBody Object getSubPackage(
			@RequestBody RequestParameter request) {
	     	return   subscriptionServices.getSubPackage(request);
	}
	@ApiOperation(value = "subscription  Evt(75)", response = Iterable.class)
	@RequestMapping(value = "/subscription", method = RequestMethod.POST)
	public @ResponseBody Object subscription(
			@RequestBody RequestParameter subscription) {
	     	return   subscriptionServices.subscription(subscription);
	}
	@ApiOperation(value = "unSubscription  Evt(76)", response = Iterable.class)
	@RequestMapping(value = "/unSubscription", method = RequestMethod.POST)
	public @ResponseBody Object unSubscription(
			@RequestBody RequestParameter unSubscription) {
	     	return   subscriptionServices.unSubscription(unSubscription);
	}
	
	@ApiOperation(value = "ACtive Subscription  Evt(82)", response = Iterable.class)
	@RequestMapping(value = "/activateSubscription", method = RequestMethod.POST)
	public @ResponseBody Object activateSubscription(
			@RequestBody RequestParameter active) {
	     	return   subscriptionServices.activateSubscription(active);
	}
	
}
