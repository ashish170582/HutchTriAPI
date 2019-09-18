package com.utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.beans.Root;

import com.fasterxml.jackson.databind.ObjectMapper;
import beans.config.AESEncriptionDecription;

@Component
public class DownloadUtility {


	@Autowired
	private DataSource dataSource;
	@Autowired
	private MessageSource messageSource;
    private String TrackTitle = "";
    private String  TrackFile = "";
    private int code=0;
	public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        	byte[] valueDecodedBase64 = Base64.getDecoder().decode(request.getParameter("p"));
           // byte[] valueDecoded = Base64.getDecoder().decode(request.getParameter("p"));
        	byte[] valueDecoded = new AESEncriptionDecription().decrypt(new String(valueDecodedBase64));
            String query = new String(valueDecoded);
            String[] params = query.split("&");
            Map<String, String> map = new HashMap<>();
            for (String param : params) {
                String[] p = param.split("=");
                String name = p[0];
                if (p.length > 1) {
                    String value = p[1];
                    map.put(name, value);
/*                    System.out.println("Name-"+name);
                    System.out.println("Value-"+value);*/
                }
            }
       
            try {
            //   MySQL1 mysql = new MySQL1();
            	
                int techRefID;
                String  quality =  map.get("dwldquality");
                if(quality==null){
                    quality="1";
                }
                switch (quality){
                    case "0" :                        
                        techRefID=57;  // 96 kbps
                        break;
                    case "1" :                        
                        techRefID=5;  // 128 kpus
                        break;                    
                    case "2" :                        
                        techRefID=56;  // 64kbps
                        break;
                    default:
                        techRefID=5;
                        
                }
                
            	SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetTrackInfo");			  			  
  			    SqlParameterSource inParams = new MapSqlParameterSource()
  										.addValue("inCountryId", (String) map.get("ocid"))
  					.addValue("inResourceCode",  (String) map.get("trackid"))
  					.addValue("inAudioTechRefId", techRefID)
  					.addValue("inImageTechRefId", 126);
  			        Map<String, Object> rs = jdbcCall.execute(inParams);								      
				    ArrayList<Object> ar = new ArrayList<Object>();
				  ar = (ArrayList) rs.get("#result-set-1");
			      ar.forEach(item->{
		    	  Map resultMap = (Map) item;  			  
		    	  TrackTitle= resultMap.get("resource_title").toString();
		    	  TrackFile= resultMap.get("filePath").toString();
				 });
                
                
//                ResultSet rs = mysql.prepareCall("{CALL `GetTrackInfo`(" + (String) map.get("ocid") + ",'" + (String) map.get("trackid") + "', "+techRefID+"  ,126)}");
//                if (rs != null) {
//                    while (rs.next()) {
//                        TrackTitle = rs.getString("resource_title");
//                        TrackFile = rs.getString("filePath");
//                    }
//                }
			    
			      
			      
			  	 jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserOfflineValidate");			  			  
  			     inParams = new MapSqlParameterSource()
  					.addValue("inflag",1)
  					.addValue("inocid",  (String) map.get("ocid"))
  					.addValue("inos", (String) map.get("os"))
  					.addValue("indevid", (String) map.get("devid"))
  					.addValue("inuserid", (String) map.get("userid"))
  					.addValue("intrackid", (String) map.get("trackid"))
  					.addValue("intechrefid", techRefID)
  					.addValue("inaudioid", 126)
  					.addValue("intoken",  (String) map.get("token"))
  					;
  			         rs = jdbcCall.execute(inParams);								      
				    ar = new ArrayList<Object>();
				  ar = (ArrayList) rs.get("#result-set-1");
			      ar.forEach(item->{
		    	  Map resultMap = (Map) item;  			  
		    	  code=Integer.parseInt( resultMap.get("code").toString());
		    	  
				 });
			      
//                rs = mysql.prepareCall("{CALL `UserOfflineValidate`(1," + (String) map.get("ocid") + ",'" + (String) map.get("os") + "','" + (String) map.get("devid") +
//                		"','" + (String) map.get("userid") + "','" + (String) map.get("trackid") + "', "+techRefID+"  ,126,'" + (String) map.get("token") + "')}");
//               
//                if (rs != null) {
//                    while (rs.next()) {
//                    		code=rs.getInt("code");
//                    }
//                }

                System.out.println("REsponse code--"+code);
            //    mysql.close();
            } catch (Exception e) {
                System.out.println("Exception in DownloadTrackServlet.processRequest(HttpServletRequest request, HttpServletResponse response) Internal - " + e.getMessage());
            }
            //Check URL Time Validation
            int downloadaresponse = 0;
			if(code==1)
            	downloadaresponse=this.fileDownloading(request, response, TrackTitle, TrackFile);
            	if(downloadaresponse==1){
            		
            		   
            		SimpleJdbcCall  jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserOfflineValidate");			  			  
            		SqlParameterSource  inParams = new MapSqlParameterSource()
     					.addValue("inflag",2)
     					.addValue("inocid",  (String) map.get("ocid"))
     					.addValue("inos", (String) map.get("os"))
     					.addValue("indevid", (String) map.get("devid"))
     					.addValue("inuserid", (String) map.get("userid"))
     					.addValue("intrackid", (String) map.get("trackid"))
     					.addValue("intechrefid", 64)
     					.addValue("inaudioid", 126)
     					.addValue("intoken",  (String) map.get("token"))
     					;
            		  Map<String, Object>     rs = jdbcCall.execute(inParams);								      
            		  
   		    	  
            		
            		
//            		MySQL1 mysql=new MySQL1();
//                     mysql.prepareCall("{CALL `UserOfflineValidate`(2," + (String) map.get("ocid") + ",'" + (String) map.get("os") + "','" + (String) map.get("devid") +
//                    		"','" + (String) map.get("userid") + "','" + (String) map.get("trackid") + "', "+64+"  ,126,'" + (String) map.get("token") + "')}");
//                     mysql.close();
            	}
            
            else{
            	Root obj = new Root(code, messageSource.getMessage("113", null,new Locale(map.get("lang"))));
            	PrintWriter out=response.getWriter(); 
                response.setContentType("application/json");
                out.print("{\"root\":" + toJson(obj) + "}");
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            System.out.println("Exception in DownloadTrackServlet.processRequest(HttpServletRequest request, HttpServletResponse response) External - " + e.getMessage());
        }
    }
	
	@SuppressWarnings("finally")
	@Async
	public int fileDownloading(HttpServletRequest request,HttpServletResponse response, String fileName, String downloadFile) throws Exception {
		
        ServletOutputStream stream = null;
        BufferedInputStream buf = null;
        int rescode=0;
        try {
            stream = response.getOutputStream();
            File mp3 = new File(downloadFile);
            //set response headers
            response.setContentType("audio/mpeg");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName.trim() + ".mp3");
            response.setContentLength((int) mp3.length());
            FileInputStream input_1 = new FileInputStream(mp3);
            buf = new BufferedInputStream(input_1);
            int readBytes = 0;
            //read from the file; write to the ServletOutputStream
            while ((readBytes = buf.read()) != -1) {
                stream.write(readBytes);
            }
            rescode=1;
        } catch (Exception e) {
        	rescode=0;
            e.printStackTrace();
            System.out.println("Exception in DownloadTrackServlet.fileDownloading(HttpServletRequest request, HttpServletResponse response, String fileName, String downloadFile) - " + e.getMessage());
        } finally {
            if (stream != null) {
            	stream.close();
            }
            if (buf != null) {
            	buf.close();
            }
            return rescode;
        }
	}
    public String toJson(Object object) {
        String result = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(object);
            return result;
        } catch (Exception e) {
        	e.printStackTrace();
            
        }
        return null;
    }
}
