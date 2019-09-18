package com.filter;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import com.fasterxml.jackson.databind.ObjectMapper;


@ControllerAdvice
public class ActionResponseLoggerAspect implements ResponseBodyAdvice<Object> {
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter arg1,MediaType arg2, Class<? extends HttpMessageConverter<?>> arg3,
			ServerHttpRequest arg4, ServerHttpResponse arg5) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			//System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body));
			 // System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body));
			  //String normalView = mapper.writerWithView(Object.class).writeValueAsString(body);
			//  System.out.println("API Response--"+mapper.writerWithView(Object.class).writeValueAsString(body));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			return body;
			//return new AESEncriptionDecription().encrypt(mapper.writerWithView(Object.class).writeValueAsString(body))   ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public boolean supports(MethodParameter arg0,
			Class<? extends HttpMessageConverter<?>> arg1) {
		// TODO Auto-generated method stub
		return true;
	}

}
