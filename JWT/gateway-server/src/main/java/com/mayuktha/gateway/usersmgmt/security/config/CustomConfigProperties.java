package com.mayuktha.gateway.usersmgmt.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "jwt")
@Setter @Getter
@Component
public class CustomConfigProperties {

	private String secret;
	
}



