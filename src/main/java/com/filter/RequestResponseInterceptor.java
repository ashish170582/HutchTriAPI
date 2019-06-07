package com.filter;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Component
public class RequestResponseInterceptor extends HandlerInterceptorAdapter  {
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("AfterCompletion--djhfiudhf");
		//super.afterCompletion(request, response, handler, ex);
	}
	@Override
		public void postHandle(HttpServletRequest request,
				HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception {
			// TODO Auto-generated method stub
		System.out.println("In postHandle----------------------");
/*		String str=response.getWriter().toString();
		System.out.println("After--"+str);*/
			//super.postHandle(request, response, handler, modelAndView);
		}
	@Override
		public boolean preHandle(HttpServletRequest request,
				HttpServletResponse response, Object handler) throws Exception {
			System.out.println("Before--");
			return super.preHandle(request, response, handler);
		}

}
