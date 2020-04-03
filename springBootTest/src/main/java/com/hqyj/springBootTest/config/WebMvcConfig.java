package com.hqyj.springBootTest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hqyj.springBootTest.filter.ParameterFilter;
import com.hqyj.springBootTest.interceptor.UriInterceptor;

@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Bean
	public FilterRegistrationBean<ParameterFilter> filter(){
		FilterRegistrationBean<ParameterFilter> bean = new FilterRegistrationBean<ParameterFilter>();
		bean.setFilter(new ParameterFilter());
		return bean;
	}
	
	@Autowired
	private UriInterceptor uriInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(uriInterceptor).addPathPatterns("/**");
	}
}
