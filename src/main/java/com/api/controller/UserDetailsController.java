package com.api.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.beans.RequestParameter;
import com.services.UserDetailsServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
//@Api(value="HutchTri", description="EVT (1,5)")
@Api(tags="User Details" ,description="EVT (1,5,6,36,37,38,48,49,91,98,215)")
public class UserDetailsController {
	
	@Autowired
	UserDetailsServices userDetailsServices;
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})

@ApiOperation(value = "SignIn User Evt-1", response = Iterable.class)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Object login(
			@RequestBody RequestParameter reqParam) {
	     	return   userDetailsServices.checkLogin(reqParam);
	}
	@ApiOperation(value = "SignOut User Evt-5",notes="ActionId Value \r\n" + 
			"__________________ \r\n" + 
			"1 - Subscription \r\n" + 
			"2 - Un-Subscription \r\n" + 
			"3 - Activate \r\n" + 
			"4 - Renew \r\n" + 
			"5 - None[] \r\n" + 
			"", response = Iterable.class)
	@RequestMapping(value = "/signout", method = RequestMethod.POST)
	public @ResponseBody Object signout(@RequestBody RequestParameter reqParam) {
	     	return   userDetailsServices.SignOut(reqParam);
	}
	@ApiOperation(value = "Get User Info Evt-6", response = Iterable.class)
	@RequestMapping(value = "/userinfo", method = RequestMethod.POST)
	public @ResponseBody Object userinfo(@RequestBody RequestParameter reqParam) {
	     	return   userDetailsServices.userinfo(reqParam);
	}
	
	@ApiOperation(value = "Update User Info Evt-7",notes="gender=1 /2/3 (male/ female/other) \r\n" + 
			"etype =0/1/2/3( \r\n" + 
			"0- Only User Info & \r\n" + 
			"1 - User Info + Image & \r\n" + 
			"2 - User Info +Mobile Number Verification & \r\n" + 
			"3- User Info + Image + Mobile Number Verification) \r\n" + 
			"", response = Iterable.class)
	@RequestMapping(value = "/updateuserinfo", method = RequestMethod.POST)
	public @ResponseBody Object updateuserinfo(@RequestBody RequestParameter reqParam) {
	     	return   userDetailsServices.updateuserinfo(reqParam);
	}
	
/*	@ApiOperation(value = "Update User Image Evt-8", response = Iterable.class)
	@RequestMapping(value = "/uploadUserImage", method = RequestMethod.POST)
	public @ResponseBody Object uploadImage(@RequestParam   String usere_id,@RequestPart String lang,@RequestParam(value="file",required=true) @Valid @NotNull @NotBlank MultipartFile file) {
	    return   userDetailsServices.uploadUserImage(usere_id,lang,file);
	}*/
	@ApiOperation(value = "Update User Image Evt-8", response = Iterable.class)
	@RequestMapping(value = "/uploadUserImage" ,method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public @ResponseBody Object uploadImage(HttpServletRequest request,HttpServletResponse response, @RequestPart MultipartFile  uploaded_file) {
		System.out.println("Here---1"+request.getQueryString()); 
		
		Enumeration<String> params = request.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = params.nextElement();
		 System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		}
		//return "Done";
	    return   userDetailsServices.uploadUserImage(request,response,uploaded_file);
	}
	
	@ApiOperation(value = "GET_FAVOURITE_TRACK_LIST Evt-36", response = Iterable.class)
	@RequestMapping(value = "/getFavouriteTrackList" ,method = RequestMethod.POST)
	public @ResponseBody Object getFavouriteTrackList(@RequestBody RequestParameter reqParam) {
	     	return   userDetailsServices.getFavouriteTrackList(reqParam);
	}
	
	@ApiOperation(value = "ADD_TRACK_TO_FAVOURITE  Evt-37", response = Iterable.class)
	@RequestMapping(value = "/addFavouriteTrack", method = RequestMethod.POST)
	public @ResponseBody Object addFavouriteTrack(@RequestBody RequestParameter reqParam) {
	     	return   userDetailsServices.addFavouriteTrack(reqParam);
	}
	
	@ApiOperation(value = "REMOVE_TRACK_FROM_FAVOURITE  Evt-38", response = Iterable.class)
	@RequestMapping(value = "/removeFavouriteTrack", method = RequestMethod.POST)
	public @ResponseBody Object removeFavouriteTrack(@RequestBody RequestParameter reqParam) {
	     	return   userDetailsServices.removeFavouriteTrack(reqParam);
	}
	
	
	@ApiOperation(value = "COUNT(NOTIFICATION/FAVOURITE)  Evt-48", response = Iterable.class)
	@RequestMapping(value = "/getNotificationFavouriteCount", method = RequestMethod.POST)
	public @ResponseBody Object getNotificationFavouriteCount(@RequestBody RequestParameter reqParam) {
	     	return   userDetailsServices.getNotificationFavouriteCount(reqParam);
	}
	@ApiOperation(value = "Get User NOTIFICATION)  Evt-49",notes="etype=(1/ 2) for notification / favourite.", response = Iterable.class)
	@RequestMapping(value = "/getUserNotification", method = RequestMethod.POST)
	public @ResponseBody Object getUserNotification(@RequestBody RequestParameter reqParam) {
	     	return   userDetailsServices.getUserNotification(reqParam);
	}
	
	@ApiOperation(value = "Get User FAVOURITE NEw)  Evt-91", response = Iterable.class)
	@RequestMapping(value = "/getUserFavouriteNew", method = RequestMethod.POST)
	public @ResponseBody Object getUserFavouriteNew(@RequestBody RequestParameter reqParam) {
	     	return   userDetailsServices.getUserFavourite(reqParam);
	}
	
	
	@ApiOperation(value = "Change User Language)  Evt-93", response = Iterable.class)
	@RequestMapping(value = "/changeUserLanguage", method = RequestMethod.POST)
	public @ResponseBody Object changeUserLanguage(@RequestBody RequestParameter reqParam) {
	     	return   userDetailsServices.changeUserLanguage(reqParam);
	}
	
	@ApiOperation(value = "getUserDeviceInformation)  Evt-98", response = Iterable.class)
	@RequestMapping(value = "/getUserDeviceInformation", method = RequestMethod.POST)
	public @ResponseBody Object getUserDeviceInformation(@RequestBody RequestParameter reqParam) {
	     	return   userDetailsServices.getUserDeviceInformation(reqParam);
	}
	// UserDeviceLoginInformation	
	@ApiOperation(value = "UserDeviceLoginInformation)  Evt-215", response = Iterable.class)
	@RequestMapping(value = "/userDeviceLoginInformation", method = RequestMethod.POST)
	public @ResponseBody Object userDeviceLoginInformation(@RequestBody RequestParameter reqParam) {
	     	return   userDetailsServices.userDeviceLoginInformation(reqParam);
	}

	
}
