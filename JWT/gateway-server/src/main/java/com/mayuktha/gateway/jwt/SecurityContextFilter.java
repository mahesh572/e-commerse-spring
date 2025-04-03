package com.mayuktha.gateway.jwt;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;


import reactor.core.publisher.Mono;


public class SecurityContextFilter implements GlobalFilter,Ordered{

	
	private static final Logger logger = LoggerFactory.getLogger(SecurityContextFilter.class);
	
	@Override
	public int getOrder() {
		return -1;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		logger.debug("SecurityContextFilter::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::filter:::::START::::");
		/*
		SecurityContext securityContext = SecurityContextHolder.getContext();
		
		SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority("USER");
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                "mahesh1@gmail.com", null,List.of(simpleGrantedAuthority));
		securityContext.setAuthentication(authenticationToken);
		
		exchange.getAttributes().put("SECURITY_CONTEXT", securityContext);
		logger.debug("SecurityContextFilter::::::::::filter:::::START::::");
		return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authenticationToken));
		
		*/
		 ServerHttpRequest request = exchange.getRequest();
		
		 ServerHttpRequest modifiedRequest = request.mutate()
	                .header("X-User", "mahesh1@gmail.com")
	                .header("X-User-Roles", "ADMIN")
	             //   .header("X-Authenticated-User", authentication.getName())
	                .build();
		 
		 return chain.filter(exchange.mutate().request(modifiedRequest).build());
	}

}
