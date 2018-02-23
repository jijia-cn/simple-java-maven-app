package com.david.test.jboss;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class ServerBootstrapConfig {
	
	
	@Bean
	@ConditionalOnMissingBean
	public TestServerBootstrap testServerBootstrap(){
		return new TestServerBootstrap();
	}
}
