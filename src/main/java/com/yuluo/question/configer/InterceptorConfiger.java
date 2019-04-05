package com.yuluo.question.configer;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器
 *
 * @author: YuLuo
 * @date: 2019/4/5
 * @since: JDK 1.8
 * @version: v1.0
 */

//@Configuration
public class InterceptorConfiger implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CustomInterceptor()).addPathPatterns("/**");
	}
}
