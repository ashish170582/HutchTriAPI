package com.services;

import java.util.List;

import org.springframework.stereotype.Service;


import com.beans.CciPortalResponse;
import com.beans.DashBoardData;
import com.beans.OperatorData;



@Service
public interface CCIServices {

	List<CciPortalResponse> getUserSuccessDetails(String mobile_number);

	CciPortalResponse getUserSubDetails(String mobile_number);

	List<CciPortalResponse> getUserUnsubDetails(String mobile_number);
	
	public List<OperatorData> getOperatorData(String fromDate, String toDate );
	
	public List<DashBoardData> getDashBoardData();
	
	

	
}
