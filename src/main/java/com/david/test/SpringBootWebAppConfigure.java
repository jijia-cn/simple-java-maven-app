package com.david.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.david.test.interceptor.TestWebInterceptor;

@Configuration
public class SpringBootWebAppConfigure extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
//		registry.addInterceptor(new TestWebInterceptor()); 			//拦截所有
		registry.addInterceptor(new TestWebInterceptor()).addPathPatterns("/hello");
	}
	
}
