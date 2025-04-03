package com.mayuktha.products.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    
		http.securityContext(securityContext -> securityContext.requireExplicitSave(true))        // Prevent clearing SecurityContext
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
		http.csrf(csrf->csrf.disable());
		 http.authorizeHttpRequests(auth->auth.requestMatchers("/product/get-by-product-id/**").permitAll());
		 http.authorizeHttpRequests(auth->auth.requestMatchers("/product/search/**").permitAll());
	//	http.authorizeHttpRequests(auth->auth.requestMatchers("/user-mgmt/v1/users/**").hasAuthority("ADMIN"));
		http.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
		
	    http.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	    http.addFilterBefore(new JwtAuthenticationFilter(), AnonymousAuthenticationFilter.class); // 🔥 Ensures SecurityContext is set before AnonymousFilter
	   // http.addFilterAfter(new SecurityContextHolderFilter(), UsernamePasswordAuthenticationFilter.class);
	    return http.build();
	}
}
