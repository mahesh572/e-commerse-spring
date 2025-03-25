package com.mayuktha.gateway.usersmgmt.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.mayuktha.gateway.usersmgmt.security.authprovider.CustomAuthenticationManager;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Autowired
	CustomAuthenticationManager customAuthenticationManager;
	
	@Bean
	public SecurityWebFilterChain  securityWebFilterChain(ServerHttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.authorizeExchange(exange -> exange.pathMatchers("/public/**").permitAll()
				.anyExchange().authenticated()
				)
		.authenticationManager(customAuthenticationManager)
		.httpBasic(Customizer.withDefaults())
		.formLogin(f->f.disable());
		
		 return httpSecurity.build();
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder(){
		//return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return new BCryptPasswordEncoder();
	}
	
}


