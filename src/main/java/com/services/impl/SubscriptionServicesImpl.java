package com.services.impl;

import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.app.beans.RootResponse;
import com.app.beans.SubscriptionPackageList;
import com.beans.RequestParameter;
import com.beans.Root;
import com.database.DataBaseProcedures;
import com.services.SubscriptionServices;

@Service
public class SubscriptionServicesImpl implements SubscriptionServices {
	private Root obj;
	@Autowired
	DataBaseProcedures dbProcedures;
	@Autowired
	private MessageSource messageSource;
	@SuppressWarnings("rawtypes")
	private  List list = null;

	//CRBT_REQUEST 
	@Override
	public RootResponse crbtRequest(RequestParameter reqParam) {
        try {
            int responseCode =dbProcedures.resendOTP(reqParam);
            obj = new Root(responseCode, messageSource.getMessage(""+responseCode, null,new Locale(reqParam.getLanguageCode())));
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 46: - " + e.getMessage());
            obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getSubPackage(RequestParameter reqParam) {
		
        try {
            list = dbProcedures.getSubscriptionPackageList(reqParam);
            if (list.isEmpty()) {
                obj = new Root(222, messageSource.getMessage("222", null,new Locale(reqParam.getLanguageCode())));
            } else {
            	 obj = new SubscriptionPackageList(list, dbProcedures.getSubscriptionTrialStatus(reqParam.getUserId()),dbProcedures.SubscriptionBenifits(reqParam.getCountryId(),reqParam.getLanguageCode()));
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.case 74: - " + e.getMessage());
            obj = new Root(222, messageSource.getMessage("222", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	
	@Override
	public RootResponse subscription(RequestParameter reqParam) {
        try {
            if (reqParam.getUserId() <= 0 || reqParam.getMsisdn().length() < 7 || reqParam.getEventType().length() > 2) {
            	obj = new Root(244, messageSource.getMessage("244", null,new Locale(reqParam.getLanguageCode())));
            } else {
                int responseCode = dbProcedures.requestSubscription(reqParam);
                obj = new Root(responseCode, messageSource.getMessage(String.valueOf(responseCode), null,new Locale(reqParam.getLanguageCode())));
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.case 75: - " + e.getMessage());
            obj = new Root(244, messageSource.getMessage("244", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	
	@Override
	public RootResponse unSubscription(RequestParameter reqParam) {
        //UN-SUBSCRIPTION_REQUEST
        try {
            int responseCode = dbProcedures.requestUnSubscription(reqParam);
            obj = new Root(responseCode, messageSource.getMessage(String.valueOf(responseCode), null,new Locale(reqParam.getLanguageCode())));
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.case 76: - " + e.getMessage());
            obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	@Override
	public RootResponse activateSubscription(RequestParameter reqParam) {
		
        try {
            int responseCode = dbProcedures.requestActivate(reqParam);
            obj = new Root(responseCode, messageSource.getMessage(String.valueOf(responseCode), null,new Locale(reqParam.getLanguageCode())));
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.(case 82): - " + e.getMessage());
            obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}

}
