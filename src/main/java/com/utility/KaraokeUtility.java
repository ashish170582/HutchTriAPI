package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.beans.Root;
import com.beans.karaokeData;


@Component
public class KaraokeUtility {
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${UPLOAD_SOLO_DIRECTORY}")
	private String UPLOAD_SOLO_DIRECTORY;
	@Value("${UPLOAD_COLLAB_DIRECTORY}")
	private String UPLOAD_COLLAB_DIRECTORY;
	@Value("${UPLOAD_TEMP_DIRECTORY}")
	private String UPLOAD_TEMP_DIRECTORY;
	
	private boolean isSuccessUploded = false;
	
	private String UPLOAD_DIRECTORY = "";
	private karaokeData karObj = null , karObj1 =null;
	
	private String name ="";
	
	public KaraokeUtility() {
		// TODO Auto-generated constructor stub
	}
	
	public Root uploadKaraokeFile(HttpServletRequest request,HttpServletResponse responsee,
			MultipartFile uploadFile) {
		String karaokeId=request.getParameter("karaokeId");
		String resourceCode=request.getParameter("resourceCode");
		String  userId=request.getParameter("userid");
		String score=request.getParameter("score");
		String recordingMode=request.getParameter("recordingMode");
		String singerType=request.getParameter("singerType");
		String recordType=request.getParameter("recordType");
		boolean isJoin=Boolean.valueOf(request.getParameter("isJoin"));
		String timstamp=request.getParameter("timestamp");
		
		String os=request.getParameter("os");
		System.out.println("uploadFile ::"+ uploadFile);
		
		System.out.println(karaokeId+resourceCode+userId+score+recordingMode+singerType+recordType+recordType+isJoin+timstamp+os);
		
	
		System.out.println("recordingMode--"+recordingMode); 
		try {

			if (recordingMode.equalsIgnoreCase("solo"))
				UPLOAD_DIRECTORY = UPLOAD_SOLO_DIRECTORY + "/" + resourceCode;
			else
				UPLOAD_DIRECTORY = UPLOAD_COLLAB_DIRECTORY + "/" + resourceCode;

			createDirector(UPLOAD_DIRECTORY);
			createDirector(UPLOAD_TEMP_DIRECTORY);

			name = uploadFile.getName();
			String ext = getExt(recordType);
			do {
				name = generateUniqueFileName(resourceCode, userId) + "." + ext;
			} while (new File(UPLOAD_DIRECTORY + "/" + name).exists());
			String filePath = UPLOAD_DIRECTORY + "/" + name;
			System.out.println("File Path--"+filePath);
		 
			 ArrayList<Object> ar = callProcedure(1, karaokeId, resourceCode, userId,
					score, recordingMode, singerType,
					recordType, isJoin, filePath, timstamp);
			 
			 
			   ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  karObj = new karaokeData(Integer.parseInt(resultMap.get("karaokeId").toString()), resultMap.get("resourceCode").toString(),
			    			  resultMap.get("filePath").toString());			    	  
			    	   isSuccessUploded = getbooleanValue(resultMap.get("isSuccessfullyUploaded").toString());
			    	    name =   resultMap.get("fileName").toString();
						

					 });
			   
			   ar=null;

			if (isSuccessUploded == false) {

				// This is Use to Choose file location to upoload
				String fileAsUpload;
					fileAsUpload = UPLOAD_TEMP_DIRECTORY + "/" + name;
				// This Function is Use for Uoload File
				   uploadFile(fileAsUpload, uploadFile);
					
					
					String keraokeID=karaokeId;
					
					if(karaokeId.equalsIgnoreCase("0")) {
						keraokeID=karObj.getKaraokeId()+"";
					}

					ar = callProcedure(3, keraokeID, resourceCode, userId,
							score, recordingMode, singerType,
							recordType, isJoin, filePath, timstamp);
					 ar.forEach(item->{
				    	  Map resultMap = (Map) item;
				    	  karObj1 = new karaokeData(Integer.parseInt(resultMap.get("karaokeId").toString()), resultMap.get("resourceCode").toString(),
				    			  resultMap.get("filePath").toString());	

						 });
					
					 ar =null;
					 
					System.out.println("VAlues---"+karObj1.toString());

					// Going To merge file after Join ...
					final String mergerFile=karObj1.getUrl() + "";					
					
						new Thread() {
							
							public void run() {
								//For copy temp location to actual location
								if(os.equalsIgnoreCase("ios") && isJoin==false ) {
									copyFileUsingStream(mergerFile, fileAsUpload, filePath,karaokeId, resourceCode, userId,
											score, recordingMode, singerType,
											recordType, isJoin, filePath, timstamp);									
								}else {
									//For Merging File temp location to actual location
									mergeFile(mergerFile, fileAsUpload, filePath,karaokeId, resourceCode, userId,
											score, recordingMode, singerType,
											recordType, isJoin, filePath, timstamp);
								}
							}
						}.start();
						
						
				ar = callProcedure(2, karaokeId, resourceCode, userId,
						score, recordingMode, singerType,
						recordType, isJoin, filePath, timstamp);				
				 ar.forEach(item->{
			    	  Map resultMap = (Map) item;			    	  
			    	  isSuccessUploded = getbooleanValue(resultMap.get("isSuccessfullyUploaded").toString());

					 });
				
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("------------"+e.toString());
		}

		Object obj = null;
		if (karObj == null || isSuccessUploded == false)
			obj = new Root(1, "failure");
		else
			obj = (Root) karObj;
		return (Root) obj;
	}
	
	
	
	public void uploadFile(String file, MultipartFile uploadfile) throws Exception {
		byte[] bytes = uploadfile.getBytes();
		Path path = Paths.get(file);
		Files.write(path, bytes);
	}

	@Async
	public void mergeFile(String IN_USER_DIRECTORY1, String IN_USER_DIRECTORY2, String OUT_DIRECTORY,
			String karaokeId,String  resourceCode,String  userId,
			String  score,String  recordingMode,String  singerType,
			String  recordType,boolean  isJoin,String  filePath,String  timstamp ) {
		
		System.out.println("Going to Merge--"+IN_USER_DIRECTORY1);
		System.out.println("Going to Merge--"+IN_USER_DIRECTORY2);
		System.out.println("Going to Merge--"+OUT_DIRECTORY);
		
		File file=new File(IN_USER_DIRECTORY1);
		File file1=new File(IN_USER_DIRECTORY2);
		
		System.out.println(file.exists());
		System.out.println(file1.exists());
		
		if(IN_USER_DIRECTORY1.contains("mp4") && IN_USER_DIRECTORY2.contains("mp4")) {
			String[] cmd=  { "/usr/local/bin/ffmpeg", "-i", IN_USER_DIRECTORY1, "-i",IN_USER_DIRECTORY2 , "-filter_complex",
				"[0:v]pad=iw*2:ih[int];[int][1:v]overlay=W/2:0[vid] ", "-map", "[vid]", "-c:v", "libx264", "-crf", "23",
				"-preset", "veryfast", "-filter_complex", "amix=inputs=2:duration=shortest:dropout_transition=2",
				OUT_DIRECTORY };
			
			runMergeCommand(cmd,karaokeId, resourceCode, userId,
					score, recordingMode, singerType,
					recordType, isJoin, filePath, timstamp);
		}
		else {
			String[] cmd={"/usr/local/bin/ffmpeg",
			        "-i",IN_USER_DIRECTORY1,
			        "-i",IN_USER_DIRECTORY2,
			        "-filter_complex","amix=inputs=2:duration=shortest:dropout_transition=2",
			        OUT_DIRECTORY};
				runMergeCommand(cmd,karaokeId, resourceCode, userId,
						score, recordingMode, singerType,
						recordType, isJoin, filePath, timstamp);
			
/*				try {
					Runtime.getRuntime().exec(cmd);
					Thread.sleep(15000);
					//System.out.println("Delete File Response---" + new File(IN_USER_DIRECTORY2).delete());
				} catch (Exception e) {
					System.out.println("Exception is Here---" + e);
				}*/

			 
		}
		
	}

	public void runMergeCommand(String[] cmd,String karaokeId,String  resourceCode,String  userId,
			String  score,String  recordingMode,String  singerType,
			String  recordType,boolean  isJoin,String  filePath,String  timstamp ) {
		
		System.out.println("Run Command ---"+Arrays.toString(cmd));
		        	try {
		      		Runtime.getRuntime().exec(cmd);
		      		callProcedure(4, karaokeId, resourceCode, userId,
							score, recordingMode, singerType,
							recordType, isJoin, filePath, timstamp);
		      		
				} catch (Exception e) {
					System.out.println("Exception is Here---" + e);
					
				}
			
	}
	
	
	@SuppressWarnings("finally")
	public  ArrayList<Object> callProcedure(int flag, String karaokeId, String resourceCode, String userId, String score,
			String recordingMode, String singerType, String recordType, boolean isJoin, String filePath,
			String timstamp) {

		 ArrayList<Object> ar = null;
		try {
			int in_isJoin=0;
			if (isJoin==true) {
				in_isJoin=1;
			} 
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserKaraoke");			  			  
			    SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("in_Flag", flag )
					.addValue("in_karaoke_id",  karaokeId )
					.addValue("in_resourceCode", resourceCode)
					.addValue("in_userId", userId)					
					.addValue("in_score", score)
					.addValue("in_recording_mode", recordingMode)
			        .addValue("in_singerType", singerType)			        
			        .addValue("in_recordType", recordType)			        
			        .addValue("in_isJoin", in_isJoin)
			        .addValue("in_File_Path", filePath)
			        .addValue("in_timestamp", timstamp)			        
			        ;			    
			    
			    Map<String, Object> rs = jdbcCall.execute(inParams);	
			    System.out.println("rs data is ::: " + rs);
			     ar = new ArrayList<Object>();
			    ar = (ArrayList) rs.get("#result-set-1");
			    System.out.println("ar data is ::: " + ar);
			
//			rs = sql.prepareCall("call UserKaraoke(" + flag + ",'" + karaokeId + "','" + resourceCode + "','" + userId
//					+ "','" + score + "','" + recordingMode + "','" + singerType + "','" + recordType + "'," + isJoin
//					+ ",'" + filePath + "','" + timstamp + "')");
		} catch (Exception e) {
			 e.printStackTrace();
			System.out.println("Exception in calling UserKaraoke---");
		} finally {
			return ar;
		}
	}

	public String getExt(String fileType) {
		if (fileType.equalsIgnoreCase("audio"))
			return "mp3";
		else
			return "mp4";

	}

	public void createDirector(String path) {
		File file = new File(path);
		if (!file.isDirectory())
			file.mkdirs();
	}

	public String generateUniqueFileName(String resourceCode, String UserID) {
		String filename = "";
		long millis = System.currentTimeMillis();
		@SuppressWarnings("deprecation")
		String datetime = new Date().toGMTString();
		datetime = datetime.replace(" ", "");
		datetime = datetime.replace(":", "");
		String rndchars = RandomStringUtils.randomAlphanumeric(10);
		rndchars = rndchars.replaceAll("[^\\p{ASCII}]", "");
		rndchars = rndchars.replaceAll("[^\\p{Alpha}]", "");
		// filename = rndchars + "_" + datetime + "_" + millis+ "_" +resourceCode + "_"
		// +UserID ;
		 filename = (rndchars).replaceAll("\\/","").replaceAll("\\\\","") + "_" + millis+ "_" +resourceCode + "_" +UserID ;
		 System.out.println("File Name--"+filename);
		return filename;
	}
	
	public static void main(String arr[]) {
/*		String file1="/var/www/html/ams/Content/101/3525/211/07/1013525211_01_001_audkrk.mp3";
		String file2="/var/www/html/ams/Content/HutchTri/karaoke/temp/GeZicEJWbR_1547620295378_1013525211_1.mp4";
		String file3="/var/www/html/ams/Content/HutchTri/karaoke/solo/1013525211/GeZicEJWbR_1547620295378_1013525211_1.mp4";
		KaraokeUtility obj=new KaraokeUtility();
		obj.mergeFile(file1, file2, file3);*/
	}
	
	
	 public void copyFileUsingStream(String IN_USER_DIRECTORY1, String IN_USER_DIRECTORY2, String OUT_DIRECTORY,
				String karaokeId,String  resourceCode,String  userId,
				String  score,String  recordingMode,String  singerType,
				String  recordType,boolean  isJoin,String  filePath,String  timstamp) {
	        InputStream is = null;
	        OutputStream os = null;
	        System.out.println("Start Copy ----"+OUT_DIRECTORY);
     	try {
            is = new FileInputStream(IN_USER_DIRECTORY2);
            os = new FileOutputStream(OUT_DIRECTORY);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
	      	callProcedure(4, karaokeId, resourceCode, userId,
						score, recordingMode, singerType,
						recordType, isJoin, filePath, timstamp);
	      		
			} catch (Exception e) {
				System.out.println("Exception is Here---" + e);
				
			}
     	 finally {
	            try {
	                is.close();
	                os.close();
	            	
	            } catch (Exception ex) {
	            }
	        }	
     	System.out.println("End Copy ---"+OUT_DIRECTORY);
	 }
	 
	 private boolean getbooleanValue(String p_Value) {
			boolean bValue = false;

			if (p_Value.equals("1") || p_Value.toLowerCase().equals("true")) {
				bValue = true;
			} 

			return bValue;
		}

	
}
