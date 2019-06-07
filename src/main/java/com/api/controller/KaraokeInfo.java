package com.api.controller;

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
import com.services.KaraokeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@Api(tags="Karaoke" ,description="EVT (240,241,242)")
public class KaraokeInfo {
	
	@Autowired
	KaraokeService karaokeServiceImpl;
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})

@ApiOperation(value = "GET Karaoke Info Evt(240)", response = Iterable.class)
	@RequestMapping(value = "/getKaraokeInfo", method = RequestMethod.POST)
	public @ResponseBody Object getKaraokeInfo(
			@RequestBody RequestParameter reqParam) {
	     	return   karaokeServiceImpl.getKaraokeInfo(reqParam);
	}
	
	 // Upload Karaoke Song
	@ApiOperation(value = " Upload Karaoke Song",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, response = Iterable.class)
	@RequestMapping(value = "/uploadKaraokeSong", method = RequestMethod.POST)
	public @ResponseBody Object uploadKaraokeSong(HttpServletRequest request,HttpServletResponse response,@RequestPart(value = "uploaded_file", required = true) MultipartFile uploaded_file) {
	     	//return   karaokeServiceImpl.uploadKaraokeSong(reqParam,uploaded_file);
		return   karaokeServiceImpl.uploadKaraokeSong(request,response,uploaded_file);
	}
	
	 //Comment On Karaoke Songs //Insert/update/Delete/Select
	@ApiOperation(value = " //Comment On Karaoke Songs //Insert/update/Delete/Select Evt(241)", response = Iterable.class)
	@RequestMapping(value = "/getKaraokeComment", method = RequestMethod.POST)
	public @ResponseBody Object getKaraokeComment(
			@RequestBody RequestParameter reqParam) {
	     	return   karaokeServiceImpl.getKaraokeComment(reqParam);
	}
	
	 // Artist Follow/Unfollow
	@ApiOperation(value = " // Artist Follow/Unfollow Evt(242)", response = Iterable.class)
	@RequestMapping(value = "/getKaraokeArtistFollow", method = RequestMethod.POST)
	public @ResponseBody Object getKaraokeArtistFollow(
			@RequestBody RequestParameter reqParam) {
	     	return   karaokeServiceImpl.getKaraokeArtistFollow(reqParam);
	}
	
}
