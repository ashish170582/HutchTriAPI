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
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.beans.Root;
import com.beans.karaokeData;
import com.database.MySQL;
import com.mysql.fabric.xmlrpc.base.Array;
@Component
public class KaraokeUtility {
	
	@Value("${UPLOAD_SOLO_DIRECTORY}")
	private String UPLOAD_SOLO_DIRECTORY;
	@Value("${UPLOAD_COLLAB_DIRECTORY}")
	private String UPLOAD_COLLAB_DIRECTORY;
	@Value("${UPLOAD_TEMP_DIRECTORY}")
	private String UPLOAD_TEMP_DIRECTORY;
	
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
		Boolean isJoin=Boolean.valueOf(request.getParameter("isJoin"));
		String timstamp=request.getParameter("timestamp");
		
		String os=request.getParameter("os");
		System.out.println("uploadFile ::"+ uploadFile);
		
		System.out.println(karaokeId+resourceCode+userId+score+recordingMode+singerType+recordType+recordType+isJoin+timstamp+os);
		
		Boolean isSuccessUploded = false;
		
		String UPLOAD_DIRECTORY = "";
		karaokeData karObj = null;
		System.out.println("recordingMode--"+recordingMode); 
		try {

			if (recordingMode.equalsIgnoreCase("solo"))
				UPLOAD_DIRECTORY = UPLOAD_SOLO_DIRECTORY + "/" + resourceCode;
			else
				UPLOAD_DIRECTORY = UPLOAD_COLLAB_DIRECTORY + "/" + resourceCode;

			createDirector(UPLOAD_DIRECTORY);
			createDirector(UPLOAD_TEMP_DIRECTORY);

			String name = uploadFile.getName();
			String ext = getExt(recordType);
			do {
				name = generateUniqueFileName(resourceCode, userId) + "." + ext;
			} while (new File(UPLOAD_DIRECTORY + "/" + name).exists());
			String filePath = UPLOAD_DIRECTORY + "/" + name;
			System.out.println("File Path--"+filePath);
			MySQL sql = new MySQL();
			ResultSet rs = callProcedure(1, karaokeId, resourceCode, userId,
					score, recordingMode, singerType,
					recordType, isJoin, filePath, timstamp, sql);

			while (rs.next()) {
				karObj = new karaokeData(rs.getInt("karaokeId"), rs.getString("resourceCode"),
						rs.getString("filePath"));
				isSuccessUploded = rs.getBoolean("isSuccessfullyUploaded");
				name = rs.getString("fileName");
			}

			if (isSuccessUploded == false) {

				// This is Use to Choose file location to upoload
				String fileAsUpload;
					fileAsUpload = UPLOAD_TEMP_DIRECTORY + "/" + name;
				// This Function is Use for Uoload File
				   uploadFile(fileAsUpload, uploadFile);
					karaokeData karObj1 = null;
					
					String keraokeID=karaokeId;
					
					if(karaokeId.equalsIgnoreCase("0")) {
						keraokeID=karObj.getKaraokeId()+"";
					}

					rs = callProcedure(3, keraokeID, resourceCode, userId,
							score, recordingMode, singerType,
							recordType, isJoin, filePath, timstamp, sql);
					while (rs.next()) {
						karObj1 = new karaokeData(rs.getInt("karaokeId"), rs.getString("resourceCode"),
								rs.getString("filePath"));
					}
					
					System.out.println("VAlues---"+karObj1.toString());

					// Going To merge file after Join ...
					final String mergerFile=karObj1.getUrl() + "";
					
					
						new Thread() {
							
							public void run() {
								//For copy temp location to actual location
								if(os.equalsIgnoreCase("ios") && isJoin.equals(false) ) {
									copyFileUsingStream(mergerFile, fileAsUpload, filePath,karaokeId, resourceCode, userId,
											score, recordingMode, singerType,
											recordType, isJoin, filePath, timstamp, sql);									
								}else {
									//For Merging File temp location to actual location
									mergeFile(mergerFile, fileAsUpload, filePath,karaokeId, resourceCode, userId,
											score, recordingMode, singerType,
											recordType, isJoin, filePath, timstamp, sql);
								}
							}
						}.start();
						
						
				rs = callProcedure(2, karaokeId, resourceCode, userId,
						score, recordingMode, singerType,
						recordType, isJoin, filePath, timstamp, sql);
				while (rs.next())
					isSuccessUploded = rs.getBoolean("isSuccessfullyUploaded");
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
			String  recordType,Boolean  isJoin,String  filePath,String  timstamp,MySQL   sql) {
		
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
					recordType, isJoin, filePath, timstamp, sql);
		}
		else {
			String[] cmd={"/usr/local/bin/ffmpeg",
			        "-i",IN_USER_DIRECTORY1,
			        "-i",IN_USER_DIRECTORY2,
			        "-filter_complex","amix=inputs=2:duration=shortest:dropout_transition=2",
			        OUT_DIRECTORY};
				runMergeCommand(cmd,karaokeId, resourceCode, userId,
						score, recordingMode, singerType,
						recordType, isJoin, filePath, timstamp, sql);
			
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
			String  recordType,Boolean  isJoin,String  filePath,String  timstamp,MySQL   sql) {
		
		System.out.println("Run Command ---"+Arrays.toString(cmd));
		        	try {
		      		Runtime.getRuntime().exec(cmd);
		      		callProcedure(4, karaokeId, resourceCode, userId,
							score, recordingMode, singerType,
							recordType, isJoin, filePath, timstamp, sql);
		      		sql.close();
				} catch (Exception e) {
					System.out.println("Exception is Here---" + e);
					sql.close();
				}
			
	}
	
	
	@SuppressWarnings("finally")
	public ResultSet callProcedure(int flag, String karaokeId, String resourceCode, String userId, String score,
			String recordingMode, String singerType, String recordType, Boolean isJoin, String filePath,
			String timstamp, MySQL sql) {

		ResultSet rs = null;
		try {
			rs = sql.prepareCall("call UserKaraoke(" + flag + ",'" + karaokeId + "','" + resourceCode + "','" + userId
					+ "','" + score + "','" + recordingMode + "','" + singerType + "','" + recordType + "'," + isJoin
					+ ",'" + filePath + "','" + timstamp + "')");
		} catch (Exception e) {
			System.out.println("Exception in calling UserKaraoke---");
		} finally {
			return rs;
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
				String  recordType,Boolean  isJoin,String  filePath,String  timstamp,MySQL   sql) {
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
						recordType, isJoin, filePath, timstamp, sql);
	      		sql.close();
			} catch (Exception e) {
				System.out.println("Exception is Here---" + e);
				
			}
     	 finally {
	            try {
	                is.close();
	                os.close();
	            	sql.close();
	            } catch (Exception ex) {
	            }
	        }	
     	System.out.println("End Copy ---"+OUT_DIRECTORY);
	 }
	
}
