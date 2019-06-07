package com.filter;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.beans.RequestParameter;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
@Component
public class RequestFilter implements Filter {

	
	@Value("${request-logs-path}")
	private String requestLogsPath;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
        Enumeration<String> headerNames = httpRequest.getHeaderNames();

        if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                        System.out.println("Header: " + httpRequest.getHeader(headerNames.nextElement()));
                }
        }
        
		//For Enable encrypt / Decrypt  Security
		
		RequestWrapper	request1 = new RequestWrapper((HttpServletRequest) request);
		 ResponseWrapper wrappedResponse = new ResponseWrapper((HttpServletResponse) response);
		System.out.println("Body--"+request1.getBody());
		doBeforeProcessing(request1, wrappedResponse,request1.getBody());
		 chain.doFilter(request1, response);
		
		//String url = ((HttpServletRequest)request).getRequestURL().toString();
		//System.out.println("Request Url -------: " + url);
		
		//To enable encrypt Comment Below Line
	//	 chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
    private void doBeforeProcessing(RequestWrapper request, ResponseWrapper response,String body)
            throws IOException, ServletException {
/*        if (debug) {
            log("LoggingFilter:DoBeforeProcessing");
        }
*/
	// Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a filter that implements setParameter() on a request
        // wrapper could set parameters on the request before passing it on
        // to the filter chain.
	/*
         String [] valsOne = {"val1a", "val1b"};
         String [] valsTwo = {"val2a", "val2b", "val2c"};
         request.setParameter("name1", valsOne);
         request.setParameter("nameTwo", valsTwo);
         */
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        final String finalParamList;
        String paramList = "";
        int n;
        StringBuffer buf;
        String name;
        for (Enumeration en = request.getParameterNames(); en.hasMoreElements();) {
            name = (String) en.nextElement();
            String values[] = request.getParameterValues(name);
            n = values.length;
            buf = new StringBuffer();
            buf.append(name);
            buf.append("=");
            for (int i = 0; i < n; i++) {
                buf.append(values[i]);
                if (i < n - 1) {
                    buf.append(",");
                }
            }
            if (paramList.isEmpty()) {
                paramList += "dt=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "$local=" + request.getLocalAddr() + "$remote=" + request.getRemoteAddr() + "$" + buf.toString();
            } else {                
                if(!buf.toString().contains("L-USER-MSISDN:")&&!buf.toString().contains("json=")){
                    paramList += "$" + buf.toString();    
                }                
            }
            //log(buf.toString());
            
        }
     //   System.out.println("----------"+request.getServletPath());
        if ("POST".equalsIgnoreCase(request.getMethod()) && !body.isEmpty()) {
            Scanner s = null;
            try {
            //	System.out.println("Before--------------------------------");
                s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
            //    System.out.println("After--------------------------------");

            JsonFactory factory = new JsonFactory();
            ObjectMapper mapper = new ObjectMapper(factory);
            JsonNode rootNode = mapper.readTree(s.next() + "");  
            Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
            while (fieldsIterator.hasNext()) {
                Map.Entry<String,JsonNode> field = fieldsIterator.next();
                //System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
                String para=field.getKey()+"="+(field.getValue()+"").replaceAll("\"", "");
                if (paramList.isEmpty()) {
                    paramList += "dt=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "$local=" + request.getLocalAddr() + "$remote=" + request.getRemoteAddr() + "$" + para.toString();
                } else {                
                    if(!para.contains("L-USER-MSISDN:")&&!para.contains("json=")){
                        paramList += "$" + para.toString();    
                    }                
                }
            }
            s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
     //  System.out.println("Filter LOG:::"+paramList.toString());
        finalParamList = paramList;
        //System.out.println(" Parameters LIST: " + paramList);
        new Thread() {
            public void run() {
                String fileName = UUID.randomUUID().toString();
                //Log File
                try {
                	//System.out.println("Log parametere list--------"+finalParamList);
                    //FileWriter fw = new FileWriter(filterConfig.getServletContext().getInitParameter("request-logs-path") + fileName + ".log");
                	FileWriter fw = new FileWriter(requestLogsPath + fileName + ".log");
                   // System.out.println("Log parametere list--------"+finalParamList);
                    fw.write(finalParamList);
                    fw.flush();
                    fw.close();
                } catch (Exception e) {
                    System.out.println("Exception in Mziiki LoggingFilter.LOG_FILE - " + e.getMessage());
                    
                }
                //Lock File
                try {
                    //FileWriter fw = new FileWriter(filterConfig.getServletContext().getInitParameter("request-logs-path") + fileName + ".lck");
                	FileWriter fw = new FileWriter(requestLogsPath + fileName + ".lck");
                    fw.write("LOCK");
                    fw.flush(); 
                    fw.close();
                } catch (Exception e) {
                    System.out.println("Exception in Mziiki LoggingFilter.LOCK_FILE - " + e.getMessage());
                }
            }
        }.start();
    }
    
    
    
    class ResponseWrapper extends HttpServletResponseWrapper {
        public ResponseWrapper(HttpServletResponse response) {
            super(response);
        }
    }
}
