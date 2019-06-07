package com.services.impl;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.beans.ContainerItem;
import com.beans.ContainerItemBean;
import com.beans.OperatorData;
import com.app.beans.RootResponse;
import com.beans.AllItem;
import com.beans.Artist;
import com.beans.ArtistData;
import com.beans.CciPortalResponse;
import com.beans.CciPortalResponseData;
import com.beans.Container;
import com.beans.ContainerBean;
import com.beans.RequestParameter;
import com.beans.Root;
import com.beans.Tracks;
import com.database.DataBaseProcedures;
import com.services.CCIServices;
import com.services.HomeServices;

@Service
public class CCIServiceImpl implements CCIServices {
	
	@Autowired
	DataBaseProcedures dbProcedures;

	@Override
	public CciPortalResponse getUserSubDetails(String mobile_number) {
		return dbProcedures.getUserSubDetails(mobile_number);
	}
	
	@Override
	public List<CciPortalResponse> getUserSuccessDetails(String mobile_number) {
		return dbProcedures.getUserSuccessDetails(mobile_number);
	}
	@Override
	public List<CciPortalResponse> getUserUnsubDetails(String mobile_number) {
		return dbProcedures.getUserUnsubDetails(mobile_number);
	}
	
	@Override
	public List<OperatorData> getOperatorData(String fromDate, String toDate) {
		 
		return    dbProcedures.getOperatorData(fromDate, toDate);
	}

}
