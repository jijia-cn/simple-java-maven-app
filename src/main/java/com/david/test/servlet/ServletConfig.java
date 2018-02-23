package com.david.test.servlet;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

	@Bean
	@ConditionalOnMissingBean
	public FilterRegistrationBean crossOriginFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new CrossOriginControlFilter());
		registration.addUrlPatterns("/*");
		return registration;
	}
	
	@Bean
	@ConditionalOnMissingBean
	public ServletRegistrationBean testServlet() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new TestServlet());
		registration.addUrlMappings("/testServlet");
		return registration;
	}
	
	@ConditionalOnMissingBean
	public ServletListenerRegistrationBean indexListener() {
		ServletListenerRegistrationBean registration = new ServletListenerRegistrationBean(new IndexListener());
//		registration.add("/testServlet");
		return registration;
	}
}
