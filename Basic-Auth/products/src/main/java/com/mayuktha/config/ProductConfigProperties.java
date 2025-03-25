package com.mayuktha.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@ConfigurationProperties(prefix = "user")
@Setter @Getter
@Component
public class ProductConfigProperties {
	
	private String message;
	private List<String> onCallSupport;
	private Map<String,String> contactDetails;
	

}