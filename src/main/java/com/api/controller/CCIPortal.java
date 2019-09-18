package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.services.impl.CCIServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@Api(tags="CCI Portal" )
public class CCIPortal {
	
	@Autowired
	CCIServiceImpl cciServiceImpl;
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	
@ApiOperation(value = "", response = Iterable.class)
	@RequestMapping(value = "/getUserSubDetails", method = RequestMethod.GET)
	public @ResponseBody Object getUserSubDetails(
			@RequestParam(required=true) String msisdn) {
	     	return   cciServiceImpl.getUserSubDetails(msisdn);
	}
	@ApiOperation(value = "", response = Iterable.class)
	@RequestMapping(value = "/getUserSuccessDetails", method = RequestMethod.GET)
	public @ResponseBody Object getUserSuccessDetails(
			@RequestParam(required=true) String msisdn) {
	     	return   cciServiceImpl.getUserSuccessDetails(msisdn);
	}
	@ApiOperation(value = "", response = Iterable.class)
	@RequestMapping(value = "/getUserUnsubDetails", method = RequestMethod.GET)
	public @ResponseBody Object getUserUnsubDetails(
			@RequestParam(required=true) String msisdn) {
	     	return   cciServiceImpl.getUserUnsubDetails(msisdn);
	}
	
	/*
	@ApiOperation(value = "getOperatorData", response = Iterable.class)
	@RequestMapping(value = "/getOperatorData", method = RequestMethod.GET)
	public @ResponseBody Object getOperatorData(
			@RequestParam(required=true) String from ,  @RequestParam(required=true) String to) {
		
	     	return   cciServiceImpl.getOperatorData(from,to);
	}
	
	*/
	@ApiOperation(value = "getDashBoardData", response = Iterable.class)
	@RequestMapping(value = "/getDashBoardData", method = RequestMethod.GET)
	public @ResponseBody Object getDashBoardData() {		
	     	return   cciServiceImpl.getDashBoardData();
	}
	
	
	
	
}
