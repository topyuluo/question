package com.yuluo.question.configer;

import ch.qos.logback.core.util.ContentTypeUtil;
import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.ws.api.pipe.ContentType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 自定义拦截器
 *
 * @author: YuLuo
 * @date: 2019/4/5
 * @since: JDK 1.8
 * @version: v1.0
 */

public class CustomInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("prehandler");
		JSONObject content = getContent(request);
		System.out.println(content);
		request.setAttribute("_request_param_json", content);
		request.setAttribute(HandlerMapping.MATRIX_VARIABLES_ATTRIBUTE , content);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("Custom Interceptor PostHandle: " );
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("Custom Interceptor afterCompletion: ");
	}

	private JSONObject getContent(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			char[] charBuffer = new char[128];
			int read = -1;
			while ((read = bufferedReader.read(charBuffer)) > 0) {
				sb.append(charBuffer, 0, read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return JSONObject.parseObject(sb.toString());
	}
}
