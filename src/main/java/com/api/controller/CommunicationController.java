package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.beans.RequestParameter;
import com.services.CommunicationServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@Api(tags="Communication - NOTIFICATION/FEEDBACK/SHARING " ,description="EVT (49,50,51,52,53,71,72,73)")
public class CommunicationController {
	@Autowired
	CommunicationServices communicationServices;
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})

	@ApiOperation(value = "readNotification Evt-50", response = Iterable.class)
	@RequestMapping(value = "/readNotification", method = RequestMethod.POST)
	public @ResponseBody Object readNotification(
			@RequestBody RequestParameter reqParam) {
	     	return   communicationServices.readNotification(reqParam);
	}
	
	@ApiOperation(value = "userFeedback Evt-51",notes="etype =1/2/3/… (i.e Feedback Subject Id)", response = Iterable.class)
	@RequestMapping(value = "/userFeedback", method = RequestMethod.POST)
	public @ResponseBody Object userFeedback(
			@RequestBody RequestParameter reqParam) {
	     	return   communicationServices.userFeedback(reqParam);
	}
	
	@ApiOperation(value = "socialSharingTrack Evt-52",notes="etype=(facebook/ twitter/sms/email/googleplus/other) for notification / favourite. ", response = Iterable.class)
	@RequestMapping(value = "/socialSharingTrack", method = RequestMethod.POST)
	public @ResponseBody Object socialSharingTrack(
			@RequestBody RequestParameter reqParam) {
	     	return   communicationServices.socialSharingTrack(reqParam);
	}
	
	@ApiOperation(value = "feedbackSubjectList Evt-53", response = Iterable.class)
	@RequestMapping(value = "/feedbackSubjectList", method = RequestMethod.POST)
	public @ResponseBody Object feedbackSubjectList(
			@RequestBody RequestParameter reqParam) {
	     	return   communicationServices.feedbackSubjectList(reqParam);
	}
	@ApiOperation(value = "getNotificationInfo Evt-57", response = Iterable.class)
	@RequestMapping(value = "/getNotificationInfo", method = RequestMethod.POST)
	public @ResponseBody Object getNotificationInfo(
			@RequestBody RequestParameter reqParam) {
	     	return   communicationServices.getNotificationInfo(reqParam);
	}
	
	@ApiOperation(value = "sendOtp Evt-71", notes="etype-� 1/2/3/4/5 [Profile/Subscription/Download/CRBT/Login] \r\n" + 
			"msisdn – Mobile Number for Verification. \r\n" + 
			"",response = Iterable.class)
	@RequestMapping(value = "/sendOtp", method = RequestMethod.POST)
	public @ResponseBody Object sendOtp(
			@RequestBody RequestParameter reqParam) {
	     	return   communicationServices.sendOtp(reqParam);
	}
	@ApiOperation(value = "resendOtp Evt-72", response = Iterable.class)
	@RequestMapping(value = "/resendOtp", method = RequestMethod.POST)
	public @ResponseBody Object resendOtp(
			@RequestBody RequestParameter reqParam) {
	     	return   communicationServices.resendOtp(reqParam);
	}
	@ApiOperation(value = "varifyOtp Evt-73", response = Iterable.class)
	@RequestMapping(value = "/varifyOtp", method = RequestMethod.POST)
	public @ResponseBody Object varifyOtp(
			@RequestBody RequestParameter reqParam) {
	     	return   communicationServices.varifyOtp(reqParam);
	}
}
