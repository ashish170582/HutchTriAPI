package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.beans.RequestParameter;
import com.services.HomeServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@Api(tags="Home Page" ,description="EVT (201,202,203,204,205)")
public class AppHome {
	
	@Autowired
	HomeServices homeServiceImpl;
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})

@ApiOperation(value = "GET Container Evt(201)", response = Iterable.class)
	@RequestMapping(value = "/getContainer", method = RequestMethod.POST)
	public @ResponseBody Object getContainer(
			@RequestBody RequestParameter reqParam) {
	     	return   homeServiceImpl.getContainer(reqParam);
	}
	@ApiOperation(value = "GET Container Item Evt(202)", response = Iterable.class)
	@RequestMapping(value = "/getContainerItem", method = RequestMethod.POST)
	public @ResponseBody Object ContainerItem(
			@RequestBody RequestParameter reqParam) {
	     	return   homeServiceImpl.ContainerItem(reqParam);
	}
	@ApiOperation(value = "GET Artist Info Evt(203)", response = Iterable.class)
	@RequestMapping(value = "/getArtistInfo", method = RequestMethod.POST)
	public @ResponseBody Object getArtistInfo(
			@RequestBody RequestParameter reqParam) {
	     	return   homeServiceImpl.getArtistInfo(reqParam);
	}
	
	@ApiOperation(value = "Follow Artist Evt(204)",notes="etype: follow/unfollow", response = Iterable.class)
	@RequestMapping(value = "/followArtist", method = RequestMethod.POST)
	public @ResponseBody Object followArtist(
			@RequestBody RequestParameter reqParam) {
	     	return   homeServiceImpl.followArtist(reqParam);
	}
	
	@ApiOperation(value = "DISCOVER_ALL_ITEMS Evt(205)",notes="etype: follow/unfollow", response = Iterable.class)
	@RequestMapping(value = "/discoverAllItems", method = RequestMethod.POST)
	public @ResponseBody Object discoverAllItems(
			@RequestBody RequestParameter reqParam) {
	     	return   homeServiceImpl.discoverAllItems(reqParam);
	}
	//Artist See All
	@ApiOperation(value = "Artist See All Evt(206)",notes="etype: follow/unfollow", response = Iterable.class)
	@RequestMapping(value = "/getArtistSeeAll", method = RequestMethod.POST)
	public @ResponseBody Object getArtistSeeAll(
			@RequestBody RequestParameter reqParam) {
	     	return   homeServiceImpl.getArtistSeeAll(reqParam);
	}
}
