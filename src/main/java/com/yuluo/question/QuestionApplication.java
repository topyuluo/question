package com.yuluo.question;

import com.yuluo.question.configer.CustomFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
public class QuestionApplication {
	
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
		filterFilterRegistrationBean.setFilter(new CustomFilter());
		filterFilterRegistrationBean.setOrder(Integer.MAX_VALUE);
		filterFilterRegistrationBean.addUrlPatterns("/*");
		return filterFilterRegistrationBean;
	}
	public static void main(String[] args) {
		SpringApplication.run(QuestionApplication.class, args);
	}

}
