package com.services.impl;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.app.beans.RootResponse;
import com.beans.FeedbackSubject;
import com.beans.Notification;
import com.beans.RequestParameter;
import com.beans.Root;
import com.database.DataBaseProcedures;
import com.services.CommunicationServices;

@Service
public class CommunicationServicesImpl implements CommunicationServices {
	private Root obj;
	@Autowired
	DataBaseProcedures dbProcedures;
	@Autowired
	private MessageSource messageSource;
	
	@SuppressWarnings("rawtypes")
	List list;
	
	//Evt-50 NOTIFICATION_READ
	@Override
	public RootResponse readNotification(RequestParameter reqParam) {
        try {
            new Thread() {
                public void run() {
                	dbProcedures.readNotification(reqParam);
                }
            }.start();
            //obj = getDownloadUrl(reqParam);
            obj = new Root(0, messageSource.getMessage("0", null,new Locale(reqParam.getLanguageCode())));
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 50: - " + e.getMessage());
            obj = new Root(0, messageSource.getMessage("0", null,new Locale(reqParam.getLanguageCode())));
            }
		return new RootResponse(obj);
	}

	//Evt-51 FEEDBACK
	@Override
	public RootResponse userFeedback(RequestParameter reqParam) {
        try {
            obj = new Root(176, messageSource.getMessage("176", null,new Locale(reqParam.getLanguageCode())));
            new Thread() {
                public void run() {
                	dbProcedures.userFeedback(reqParam);
                }
            }.start();
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 51: - " + e.getMessage());
            obj = new Root(176, messageSource.getMessage("176", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}

	//Evt-52 //Social Sharing
	@Override
	public RootResponse socialSharingTrack(RequestParameter reqParam) {
        try {
            new Thread() {
                public void run() {
                	dbProcedures.socialSharingTrack(reqParam);
                }
            }.start();
            obj = new Root(0, messageSource.getMessage("0", null,new Locale(reqParam.getLanguageCode())));
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 52: - " + e.getMessage());
            obj = new Root(0, messageSource.getMessage("0", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	
	//Evt-53 Feedback Subjects
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse feedbackSubjectList(RequestParameter reqParam) {
        try {
            list = dbProcedures.feedbackSubjectList(reqParam);
            if (list.isEmpty()) {
            	obj = new Root(100, messageSource.getMessage("100", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new FeedbackSubject(list);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 53: - " + e.getMessage());
            obj = new Root(100, messageSource.getMessage("100", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//Evt-57 NOTIFICATION
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getNotificationInfo(RequestParameter reqParam) {
		try {
			list = dbProcedures.getNotificationInfo(reqParam);
			if (list.isEmpty()) {
			obj = new Root(201, messageSource.getMessage("201", null,new Locale(reqParam.getLanguageCode())));
			} else {
			obj = new Notification(list);
			}
			} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.CASE 57: - " + e.getMessage());
			obj = new Root(201, messageSource.getMessage("201", null,new Locale(reqParam.getLanguageCode())));
			}
		return new RootResponse(obj);
	}
	
	//Evt-71 SEnd Otp
	@Override
	public RootResponse sendOtp(RequestParameter reqParam) {
        try {
            if (dbProcedures.validateMobileNumber(reqParam)) {
                obj = dbProcedures.mobileNumberVerificationRequest(reqParam);
            } else {
                obj = new Root(500, messageSource.getMessage("500", null,new Locale(reqParam.getLanguageCode())));
            }
        } catch (Exception e) {
            System.out.println(" Exception in MainServlet.case 71: - " + e.getMessage());
            obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	
	//Evt-72 RE-send Otp
	@Override
	public RootResponse resendOtp(RequestParameter reqParam) {
		
		obj = new Root(187, messageSource.getMessage(String.valueOf(187), null,new Locale(reqParam.getLanguageCode())));
        try {
            int responseCode = dbProcedures.mobileNumberVerificationResendOTP(reqParam);
            obj = new Root(responseCode, messageSource.getMessage(String.valueOf(responseCode), null,new Locale("my")));
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.case 72: - " + e.getMessage());
            obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//Evt-73 Varify OTP
	@Override
	public RootResponse varifyOtp(RequestParameter reqParam) {
        try {
            int responseCode = dbProcedures.mobileNumberVerificationOTPVerification(reqParam);
            obj = new Root(responseCode, messageSource.getMessage(String.valueOf(responseCode), null,new Locale(reqParam.getLanguageCode())));
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.case 73: - " + e.getMessage());
            obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
}
