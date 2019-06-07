package com.api.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.beans.RootResponse;
import com.beans.RequestParameter;
import com.services.impl.AppConfigServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@Api(tags="AppConfigration - " ,description="EVT (2,90,95,238)")
public class AppConfigrationController {
	
	@Autowired
	AppConfigServiceImpl appConfigServiceImpl;
	
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})

	@ApiOperation(value = "Country Info Evt-2", response = Iterable.class)
	@RequestMapping(value = "/countryinfo", method = RequestMethod.POST)
	public @ResponseBody RootResponse countryinfo(@RequestBody(required=true) RequestParameter reqParam    ) {
	     	return   appConfigServiceImpl.countryinfo(reqParam);
	}
	
	@ApiOperation(value = "HikeKeyboardItem Evt-90", response = Iterable.class)
	@RequestMapping(value = "/hikeKeyBoardItem", method = RequestMethod.POST)
	public @ResponseBody RootResponse hikeKeyBoardItem(@RequestBody(required=true) RequestParameter reqParam    ) {
	     	return   appConfigServiceImpl.hikeKeyBoardItem(reqParam);
	}
	
	@ApiOperation(value = "applicationInstallViaShare Evt-95", response = Iterable.class)
	@RequestMapping(value = "/applicationInstallViaShare", method = RequestMethod.POST)
	public @ResponseBody RootResponse applicationInstallViaShare(@RequestBody(required=true) RequestParameter reqParam    ) {
	     	return   appConfigServiceImpl.applicationInstallViaShare(reqParam);
	}
	@ApiOperation(value = "applicationInstallViaShareClickTracking Evt-223", response = Iterable.class)
	@RequestMapping(value = "/applicationInstallViaShareClickTracking", method = RequestMethod.POST)
	public @ResponseBody RootResponse applicationInstallViaShareClickTracking(@RequestBody(required=true) RequestParameter reqParam    ) {
	     	return   appConfigServiceImpl.applicationInstallViaShareClickTracking(reqParam);
	}
	// GET SERVER DATE TiME
	@ApiOperation(value = "getServerDateTime", response = Iterable.class)
	@RequestMapping(value = "/getServerDateTime", method = RequestMethod.POST)
	public @ResponseBody String getServerDateTime() {
	     	return   new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}
	
	// SEND NOTIFICATION
	@ApiOperation(value = "sendNotification", response = Iterable.class)
	@RequestMapping(value = "/sendNotification", method = RequestMethod.GET)
	public @ResponseBody String sendNotification(@RequestParam String notificationID,
			@RequestParam String devPin,
			@RequestParam String lang,
			@RequestParam String type,
			@RequestParam String userId
			) {
					appConfigServiceImpl.sendNotification(notificationID,devPin,lang,type,userId);
	     	return  "Notification Successfully Sent......."; 
	}
	// Change Language
	@ApiOperation(value = "changeLanguage", response = Iterable.class)
	@RequestMapping(value = "/changeLanguage", method = RequestMethod.POST)
	public @ResponseBody RootResponse changeLanguage(@RequestBody(required=true) RequestParameter reqParam ) {
		return   appConfigServiceImpl.changeLanguage(reqParam);
	}
	@ApiOperation(value = "PromotionShareConfig Evt-238", response = Iterable.class)
	@RequestMapping(value = "/PromotionShareConfig", method = RequestMethod.POST)
	public @ResponseBody RootResponse PromotionShareConfig(@RequestBody(required=true) RequestParameter reqParam ) {
		return   appConfigServiceImpl.PromotionShareConfig(reqParam);
	}
	
	
}
