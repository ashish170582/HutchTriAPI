package com.services.impl;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.app.beans.RootResponse;
import com.beans.AppVersionInfoData;
import com.beans.CountryAppVersionInfo;
import com.beans.CountryInfoData;
import com.beans.HikeKeyboardItem;
import com.beans.HikeKeyboardItemBean;
import com.beans.OptScreenConfig;
import com.beans.PaymentMethod;
import com.beans.PaymentMethods;
import com.beans.RequestParameter;
import com.beans.Root;
import com.beans.UserInfoData;
import com.database.DataBaseProcedures;
import com.services.AppConfigServices;

import beans.config.AudioQualityConfiguration;
import beans.config.Configuration;
import beans.config.Visibility;

@Service
public class AppConfigServiceImpl implements AppConfigServices {
	
	@Autowired
	DataBaseProcedures dbProcedures;
	@Autowired
	UserInfoData userinfo;
	@Autowired
	private MessageSource messageSource;
private Root obj;
  @SuppressWarnings("unused")
private   Object obj12 = null, obj1 = null, obj2 = null, obj3 = null, obj4 = null, obj5 = null, obj6 = null, obj7 = null, obj8 = null, obj9 = null , obj10=null;
   @SuppressWarnings("rawtypes")
private  List list = null;

	@SuppressWarnings("unchecked")
	@Override
	public RootResponse countryinfo(RequestParameter reqParam) {
		 try {
/*             if (hmTestingDevice.containsKey(devInfo.getDeviceId())) {
                 reqParam.setCountryCode(hmTestingDevice.get(devInfo.getDeviceId()));
             }*/
             
             obj1 = (CountryInfoData)dbProcedures.getCountryInfo(reqParam);
             obj2 = (AppVersionInfoData) dbProcedures.getAppVersionInfo(reqParam.getCountryCode(), reqParam.getOperatingSystem(), reqParam.getApplicationVersion());
             obj3 = (Visibility) dbProcedures.getAppFeaturesVisibility(reqParam);
             obj4 = (Configuration) dbProcedures.getApplicationConfiguration(reqParam);                            
             obj7 = (AudioQualityConfiguration) dbProcedures.getAudioQualityConfiguration(reqParam);
             list = (List<PaymentMethod>) dbProcedures.getPaymentMethods(reqParam);
             if (list.isEmpty()) {
                 obj = new Root(271, messageSource.getMessage("271", null,new Locale(reqParam.getLanguageCode())));
             } else {
                 obj8 = new PaymentMethods(list);
             }
             obj9 = dbProcedures.getLeftMenuTitle(reqParam);
             obj7 = (AudioQualityConfiguration) dbProcedures.getAudioQualityConfiguration(reqParam);
             obj10=(OptScreenConfig)dbProcedures.getOptScreenConfig(reqParam);
             if (obj1 == null || obj2 == null || obj3 == null || obj4 == null ||  obj7 == null || obj9 == null) {
                 obj = new Root(194, messageSource.getMessage("194", null,new Locale(reqParam.getLanguageCode())));
             } else {
                 obj = new CountryAppVersionInfo(obj1, obj2, obj3, obj4, obj7, obj8,obj9,obj10);
             }
            
         } catch (Exception e) {
             System.out.println("Exception in HutchTriBeatzMainServlet. CASE 2: - " + e.getMessage());
             obj = new Root(194, messageSource.getMessage("194", null,new Locale(reqParam.getLanguageCode())));

         }
		return new RootResponse(obj);
	}
	@Override
	public RootResponse hikeKeyBoardItem(RequestParameter reqParam) {
        try {
            obj = new HikeKeyboardItem((List<HikeKeyboardItemBean>) dbProcedures.getHikeKeyboardItem(reqParam));
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 90: - " + e.getMessage());
            obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	@Override
	public RootResponse applicationInstallViaShare(RequestParameter reqParam) {
        int resultCode = dbProcedures.applicationInstallViaShare(reqParam);
        obj = new Root(resultCode, messageSource.getMessage(String.valueOf(resultCode), null,new Locale(reqParam.getLanguageCode())));
		return new RootResponse(obj);
	}
	@Override
	public RootResponse applicationInstallViaShareClickTracking(RequestParameter reqParam) {
		int resultCode = dbProcedures.appInstallViaShareClickTraking(reqParam);
		obj = new Root(resultCode, messageSource.getMessage(String.valueOf(resultCode), null,new Locale(reqParam.getLanguageCode())));
		return new RootResponse(obj);
	}	
	
	//Send Notification
	@Override
	public void sendNotification(String notificationID,String devPin,String lang,String type,String userId
			) {
				dbProcedures.sendNotification(notificationID,devPin,lang,type,userId);
	}	
	//Change Language
	@Override
	public RootResponse changeLanguage(RequestParameter reqParam) {
		obj = new Root(302, messageSource.getMessage("302", null,new Locale(reqParam.getLanguageCode())));
		return new RootResponse(obj);
	}

	@Override
	public RootResponse PromotionShareConfig(RequestParameter reqParam) {
			obj =dbProcedures.getPromotionShareConfig(reqParam);
			if(obj==null)
				obj = new Root(116, messageSource.getMessage("116", null,new Locale(reqParam.getLanguageCode())));
			return new RootResponse(obj);
	}	
}
