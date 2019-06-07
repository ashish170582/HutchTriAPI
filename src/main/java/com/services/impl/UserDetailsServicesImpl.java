package com.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.beans.RootResponse;
import com.app.beans.SubscriptionBenefits;
import com.beans.DeviceInformationData;
import com.beans.Notification;
import com.beans.RequestParameter;
import com.beans.Root;
import com.beans.Tracks;
import com.beans.Transaction;
import com.beans.UserInfo;
import com.beans.UserInfoData;
import com.database.DataBaseProcedures;
import com.services.UserDetailsServices;

@Service
public class UserDetailsServicesImpl implements UserDetailsServices {
	private Root obj;
	@Autowired
	DataBaseProcedures dbProcedures;
	@Autowired
	UserInfoData userinfo;
	@Autowired
	private MessageSource messageSource;
	
	@Value("${user-profile-path}")
	private String userProfilePath;
	
	@SuppressWarnings("rawtypes")
	private  List list = null;
	
	@Override
	public RootResponse checkLogin(RequestParameter reqParam) {
		try {
				if (dbProcedures.validateMobileNumber(reqParam)) {			
					userinfo = dbProcedures.signIn(reqParam);
					if (userinfo == null) {
						obj = new Root(105, messageSource.getMessage("105", null,new Locale(reqParam.getLanguageCode())));
					} else if (userinfo.getUserid() < 0) {
						obj = new Root(106, messageSource.getMessage("106", null,new Locale(reqParam.getLanguageCode())));
					} else {
						obj = new UserInfo(userinfo,dbProcedures.SubscriptionBenifits(reqParam.getCountryId(), reqParam.getLanguageCode()),	dbProcedures.getOptScreenConfig(reqParam));
					}
				} else {
					obj = new Root(500, messageSource.getMessage("500", null,new Locale(reqParam.getLanguageCode())));
				}
		} catch (Exception e) {
			obj = new Root(105, messageSource.getMessage("105", null,new Locale(reqParam.getLanguageCode())));
			e.printStackTrace();
		}
		return new RootResponse(obj);
	}
	@Override
	public RootResponse SignOut(RequestParameter reqParam) {
		try {
			dbProcedures.signOut(reqParam);
			obj = new Root(174, messageSource.getMessage("174", null,new Locale(reqParam.getLanguageCode())));
		} catch (Exception e) {
			obj = new Root(174, messageSource.getMessage("174", null,new Locale(reqParam.getLanguageCode())));
			e.printStackTrace();
		}
		return new RootResponse(obj);
	}
	@Override
	public RootResponse userinfo(RequestParameter reqParam) {
		SubscriptionBenefits subscriptionBenefits;
        try {
            userinfo = dbProcedures.getUserInfo(reqParam);
            if (userinfo == null) {
                obj = new Root(150, messageSource.getMessage("150", null,new Locale(reqParam.getLanguageCode())));
            } else {
              //String outRes [] = dbProcedures.GetSubscriptionTrialStatus(reqParam.getUserId()).split("#");                               
                subscriptionBenefits=dbProcedures.SubscriptionBenifits(reqParam.getCountryId(),reqParam.getLanguageCode());
                obj = new UserInfo(userinfo, subscriptionBenefits,dbProcedures.getOptScreenConfig(reqParam));
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet. CASE 6: - " + e.getMessage());
            obj = new Root(150, messageSource.getMessage("150", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	
	@Override
	public RootResponse updateuserinfo(RequestParameter reqParam) {
		SubscriptionBenefits subscriptionBenefits = new SubscriptionBenefits();
        try {
            String xid = "0";
            userinfo = dbProcedures.setUserInfo(reqParam);
            if (userinfo == null) {
            	obj = new Root(150, messageSource.getMessage("150", null,new Locale(reqParam.getLanguageCode())));
            } else {
                if (reqParam.getEventType().equalsIgnoreCase("1")) {
                    File file = new File(userProfilePath + reqParam.getUserId() + "_0.jpg");
                    if (file.exists()) {
                        file.renameTo(new File(userProfilePath + reqParam.getUserId() + ".jpg"));
                    }
                } else if (reqParam.getEventType().equalsIgnoreCase("2")) {
                    Transaction x = dbProcedures.mobileNumberVerificationRequest(reqParam);
                    xid = x.getXid();
                } else if (reqParam.getEventType().equalsIgnoreCase("3")) {
                    File file = new File(userProfilePath + reqParam.getUserId() + "_0.jpg");
                    if (file.exists()) {
                        file.renameTo(new File(userProfilePath + reqParam.getUserId() + ".jpg"));
                    }
                    Transaction x = dbProcedures.mobileNumberVerificationRequest(reqParam);
                    xid = x.getXid();
                }
                obj = new UserInfo(xid, userinfo, subscriptionBenefits);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet. CASE 7: - " + e.getMessage());
            obj = new Root(150, messageSource.getMessage("150", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	@Override
	//public RootResponse uploadUserImage(HttpServletRequest request,HttpServletResponse respnse,String user_id,String lang ,MultipartFile file) {
	public RootResponse uploadUserImage(HttpServletRequest request,HttpServletResponse respnse,MultipartFile file) {
		String lang=request.getParameter("lang");
        try {
            String filePath = userProfilePath + request.getParameter("userid") + "_0.jpg";
            System.out.println("Profile Pictures path-->>> "+ filePath);
            boolean uploadError = true;
            try {
            	File filePathcreate=new File(userProfilePath);
            	if(!filePathcreate.isDirectory())
            		filePathcreate.mkdirs();
                byte[] bytes = file.getBytes();
                Path path = Paths.get(filePath);
                Files.write(path, bytes);
                uploadError=false;
                if (uploadError) {
                    obj = new Root(303, messageSource.getMessage("303", null,new Locale(lang)));
                } else {
                	obj = new Root(182, messageSource.getMessage("182", null,new Locale(lang)));
                }
                
            } catch (Exception e) {
            	System.out.println("Exception in upload File--"+e);
            	e.printStackTrace();
            	obj = new Root(100, messageSource.getMessage("100", null,new Locale(lang)));
            } finally {
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception in HutchTriBeatzMainServlet.case 8://UPDATE_PHOTO - " + e.getMessage());
            obj = new Root(100, messageSource.getMessage("100", null,new Locale(lang)));
        }
        return new RootResponse(obj);
	}
	
	
//GET_FAVOURITE_TRACK_LIST 
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getFavouriteTrackList(RequestParameter reqParam) {
        try {
            list = dbProcedures.getFavouriteTracks(reqParam);
            if (list.isEmpty()) {
                obj = new Root(183, messageSource.getMessage("183", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new Tracks(list);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 36: - " + e.getMessage());
            obj = new Root(183, messageSource.getMessage("183", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	
	// ADD_TRACK_TO_FAVOURITE 
	@Override
	public RootResponse addFavouriteTrack(RequestParameter reqParam) {
        try {
            new Thread() {
                public void run() {
                	dbProcedures.addTrackToFavourite(reqParam);
                }
            }.start();
            obj = new Root(141, messageSource.getMessage("141", null,new Locale(reqParam.getLanguageCode())));
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 37: - " + e.getMessage());
            obj = new Root(141, messageSource.getMessage("141", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	
	
	
	//REMOVE_TRACK_FROM_FAVOURITE 
	@Override
	public RootResponse removeFavouriteTrack(RequestParameter reqParam) {
        try {
            new Thread() {
                public void run() {
                	dbProcedures.delTrackFromFavourite(reqParam);
                }
            }.start();
            obj = new Root(144, messageSource.getMessage("144", null,new Locale(reqParam.getLanguageCode())));
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 38: - " + e.getMessage());
            obj = new Root(144, messageSource.getMessage("144", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	
	
	//COUNT(NOTIFICATION/FAVOURITE)
	@Override
	public RootResponse getNotificationFavouriteCount(RequestParameter reqParam) {
        try {
            if (reqParam.getEventType().equalsIgnoreCase("1")) {
                obj = dbProcedures.getNotificationCount(reqParam);
            } else if (reqParam.getEventType().equalsIgnoreCase("2")) {
                obj = dbProcedures.getFavouriteCount(reqParam);
            } else {
                obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
            }
        } catch (Exception e) {
            System.out.println(" Exception in MainServlet.CASE 48: - " + e.getMessage());
            obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//NOTIFICATION
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getUserNotification(RequestParameter reqParam) {
        try {
            list = dbProcedures.getUserNotification(reqParam);
            if (list.isEmpty()) {
                obj = new Root(168, messageSource.getMessage("168", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new Notification(list);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 49: - " + e.getMessage());
            obj = new Root(168, messageSource.getMessage("168", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//GET USER FAVOURATE
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getUserFavourite(RequestParameter reqParam) {
        try {
            if (reqParam.getEventType().equalsIgnoreCase("select")) {
                list = dbProcedures.selectFavourite(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(261, messageSource.getMessage("261", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Tracks(list);
                }

            } else if (reqParam.getEventType().equalsIgnoreCase("insert")) {
                new Thread() {
                    public void run() {
                    	dbProcedures.insertFavourite(reqParam);
                    }
                }.start();
                obj = new Root(262, messageSource.getMessage("262", null,new Locale(reqParam.getLanguageCode())));

            } else if (reqParam.getEventType().equalsIgnoreCase("update")) {
                new Thread() {
                    public void run() {
                    	dbProcedures.updateFavourite(reqParam);
                    }
                }.start();
                obj = new Root(263, messageSource.getMessage("263", null,new Locale(reqParam.getLanguageCode())));
            } else if (reqParam.getEventType().equalsIgnoreCase("delete")) {
                new Thread() {
                    public void run() {
                    	dbProcedures.deleteFavourite(reqParam);
                    }
                }.start();
                obj = new Root(264, messageSource.getMessage("264", null,new Locale(reqParam.getLanguageCode())));
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.(case 91): - " + e.getMessage());
            obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//GET_FAVOURITE_TRACK_LIST 
	@Override
	public RootResponse changeUserLanguage(RequestParameter reqParam) {
		obj = new Root(302, messageSource.getMessage("302", null,new Locale(reqParam.getLanguageCode())));
		return new RootResponse(obj);
	}
	//Get User Device Info dbProcedures.getUserDeviceInformation(reqParam.getEmailAddress())
	@Override
	public DeviceInformationData getUserDeviceInformation(RequestParameter reqParam) {
		DeviceInformationData info=new  DeviceInformationData(dbProcedures.getUserDeviceInformation(reqParam.getEmailAddress()));
		return info;
	}
	
	@Override
	public RootResponse userDeviceLoginInformation(RequestParameter reqParam) {
		//DeviceInformationData info = null;
		try {
			obj = (Root) dbProcedures.getUserDeviceLoginInformation(reqParam);
			System.out.println("Retuen Obh--"+obj);
		} catch (Exception e) {
			obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
		}
		
		return new RootResponse(obj);
	}
	
}
