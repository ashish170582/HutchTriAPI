package com.api.controller;

//import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.beans.RootResponse;
import com.beans.RequestParameter;
import com.services.DownloadServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@Api(tags="Download - " ,description="EVT (58,59,60,77)")
public class DownloadController {
	
	@Autowired
	DownloadServices downloadServices;
	
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})

	//58) SAVE_TRACK_OFFLINE_REQUEST
	@ApiOperation(value = "Save Track OFF LINE Request Info Evt-58", response = Iterable.class)
	@RequestMapping(value = "/saveTrackOfflineRequest", method = RequestMethod.POST)
	public @ResponseBody RootResponse saveTrackOfflineRequest(@RequestBody(required=true) RequestParameter reqParam    ) {
	     	return   downloadServices.saveTrackOfflineRequest(reqParam);
	}
	 //59) SAVE_TRACK_OFFLINE_REQUEST 
	@ApiOperation(value = "SUCCESSFUL_SAVED_OFFLINE  Evt-59", response = Iterable.class)
	@RequestMapping(value = "/successfullySaveTrackOffline", method = RequestMethod.POST)
	public @ResponseBody RootResponse successfullySaveTrackOffline(@RequestBody(required=true) RequestParameter reqParam    ) {
	     	return   downloadServices.successfullySaveTrackOffline(reqParam);
	}
	//60) GET_USER_OFFLINE_INFORMATION 
	@ApiOperation(value = "REQUEST [MOBILE_NUMBER_VERIFICATION]  Evt-60",notes="etype – 1/2/3/4/5 [Profile/Subscription/Download/CRBT/Login] \r\n" + 
			"msisdn – Mobile Number for Verification. \r\n" + 
			"", response = Iterable.class)
	@RequestMapping(value = "/getUserOfflineInformation", method = RequestMethod.POST)
	public @ResponseBody RootResponse getUserOfflineInformation(@RequestBody(required=true) RequestParameter reqParam    ) {
	     	return   downloadServices.getUserOfflineInformation(reqParam);
	}
	
	//77) OFFLINE_STREAMING LOGS 
	@ApiOperation(value = "OFFLINE_STREAMING LOGS  Evt-77", response = Iterable.class)
	@RequestMapping(value = "/offLineStreamingLogs", method = RequestMethod.POST)
	public @ResponseBody RootResponse offLineStreamingLogs(@RequestBody(required=true) RequestParameter reqParam    ) {
	     	return   downloadServices.offLineStreamingLogs(reqParam);
	}
	
	//Download Track
	@ApiOperation(value = "Download Track", response = Iterable.class)
	@RequestMapping(value = "/downloadtrack", method = {RequestMethod.GET ,RequestMethod.POST})
	public void downloadTrack(HttpServletRequest request, HttpServletResponse response) {
		 downloadServices.downloadTrack(request,response);
	}
	//Download Track
	@ApiOperation(value = "Play Done Evt-", response = Iterable.class)
	@RequestMapping(value = "/playDone", method = {RequestMethod.GET ,RequestMethod.POST})
	public @ResponseBody RootResponse playDone(@RequestBody(required=true) RequestParameter reqParam    ) {
		return downloadServices.playDone(reqParam);
	}

	
}
