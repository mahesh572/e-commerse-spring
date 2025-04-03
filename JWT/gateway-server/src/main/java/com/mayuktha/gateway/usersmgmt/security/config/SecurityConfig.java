package com.mayuktha.gateway.usersmgmt.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.mayuktha.gateway.jwt.JwtAuthenticationFilter;
import com.mayuktha.gateway.jwt.JwtUtils;
import com.mayuktha.gateway.usersmgmt.security.authprovider.CustomAuthenticationManager;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Autowired
	CustomAuthenticationManager customAuthenticationManager;
	
	@Bean
	public SecurityWebFilterChain  securityWebFilterChain(ServerHttpSecurity httpSecurity) throws Exception {
		
		
		httpSecurity.csrf(csrf->csrf.disable());
		//httpSecurity.cors(cors->cors.disable());
		httpSecurity.authorizeExchange(exange -> exange.pathMatchers("/public/**").permitAll()
				.anyExchange().permitAll()
				)
		// .addFilterAt(new JwtAuthenticationFilter(jwtUtil()), SecurityWebFiltersOrder.AUTHENTICATION)
		.authenticationManager(customAuthenticationManager)
		.httpBasic(h->h.disable())
		.formLogin(f->f.disable());
		
		 return httpSecurity.build();
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder(){
		//return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return new BCryptPasswordEncoder();
	}
	@Bean
    public JwtUtils jwtUtil() {
        return new JwtUtils();
    }
	
	
	@Bean
	public CorsWebFilter corsWebFilter() {
	    CorsConfiguration corsConfig = new CorsConfiguration();
	    corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
	    corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    corsConfig.setAllowedHeaders(Arrays.asList("*"));
	    corsConfig.setAllowCredentials(true);
	    corsConfig.setMaxAge(3600L);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", corsConfig);

	    return new CorsWebFilter(source);
	}
	
}


