package com.services.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.app.beans.RootResponse;
import com.beans.RequestParameter;
import com.beans.Root;
import com.database.DataBaseProcedures;
import com.services.DownloadServices;
import com.utility.DownloadUtility;

@Service
public class DownloadServiceImpl implements DownloadServices {

	@Value("${offline-logs-path}")
	private String offLineLogPath;
	@Autowired
	DataBaseProcedures dbProcedures;
	
	@Autowired
	DownloadUtility downloadUtility;
	
	@Autowired
	private MessageSource messageSource;
	private Root obj;

	
	
   //58) SAVE_TRACK_OFFLINE_REQUEST 
	@Override
	public RootResponse saveTrackOfflineRequest(RequestParameter reqParam) {
        try {
            obj = dbProcedures.getDownloadUrl(reqParam,messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 58: - " + e.getMessage());
            obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}

	   //59) SAVE_TRACK_OFFLINE_REQUEST 
		@Override
		public RootResponse successfullySaveTrackOffline(RequestParameter reqParam) {
            try {
                new Thread() {
                    public void run() {
                    	dbProcedures.successfulOfflineDownload(reqParam);
                    }
                }.start();
                obj = new Root(0, messageSource.getMessage("0", null,new Locale(reqParam.getLanguageCode())));
            } catch (Exception e) {
                System.out.println("Exception in HutchTriBeatzMainServlet.CASE 59: - " + e.getMessage());
                obj = new Root(0, messageSource.getMessage("0", null,new Locale(reqParam.getLanguageCode())));
            }
			return new RootResponse(obj);
		}
		
		//60) GET_USER_OFFLINE_INFORMATION  
		@Override
		public RootResponse getUserOfflineInformation(RequestParameter reqParam) {
            try {
                obj = dbProcedures.getUserOfflineInformation(reqParam,  messageSource.getMessage("215", null,new Locale(reqParam.getLanguageCode())),messageSource.getMessage("216", null,new Locale(reqParam.getLanguageCode())));
                if (obj == null) {
                	obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
                }
            } catch (Exception e) {
                System.out.println("Exception in HutchTriBeatzMainServlet.CASE 60: - " + e.getMessage());
                obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
            }
			return new RootResponse(obj);
		}
		
		//60) offLineStreamingLogs  
		@Override
		public RootResponse offLineStreamingLogs(RequestParameter reqParam) {
            try {
                final String json = reqParam.getJson();
                new Thread() {
                    public void run() {
                        String fileName = UUID.randomUUID().toString();
                        //Log File
                        try {
                            FileWriter fw = new FileWriter(offLineLogPath + fileName + ".log");
                            fw.write(json);
                            fw.flush();
                            fw.close();
                        } catch (Exception e) {
                            System.out.println("Exception in HutchTriBeatzMainServlet.OFFLINE_STREAMING LOGS: - " + e.getMessage());
                        }
                        //Lock File
                        try {
                            FileWriter fw = new FileWriter(offLineLogPath + fileName + ".lck");
                            fw.write("LOCK");
                            fw.flush();
                            fw.close();
                        } catch (Exception e) {
                            System.out.println("Exception in HutchTriBeatzMainServlet.(OFFLINE_STREAMING LOGS): - " + e.getMessage());
                        }
                    }
                }.start();
                obj = new Root(0, messageSource.getMessage("0", null,new Locale(reqParam.getLanguageCode())));

            } catch (Exception e) {
                System.out.println("Exception in HutchTriBeatzMainServlet.case 77: - " + e.getMessage());
                obj = new Root(0, messageSource.getMessage("0", null,new Locale(reqParam.getLanguageCode())));
            }
			return new RootResponse(obj);
		}

		@Override
		public void downloadTrack(HttpServletRequest request, HttpServletResponse response) {
			try {
				downloadUtility.processRequest(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Exception in HutchTriBeatzMainServlet. Download Track: - " + e.getMessage());
			}
			
		}
		
		//101) Play Done  
		@Override
		public RootResponse playDone(RequestParameter reqParam) {
			obj = new Root(301, messageSource.getMessage("301", null,new Locale(reqParam.getLanguageCode())));
			return new RootResponse(obj);
		}
		
}
