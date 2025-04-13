package com.mayuktha.gateway;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import com.mayuktha.gateway.jwt.JwtAuthenticationFilter;


@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties
public class GatewayAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayAppApplication.class, args);
	}
	
	@Bean
	public RouteLocator mayukthRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		JwtAuthenticationFilter jwtfilter = new JwtAuthenticationFilter();
		return routeLocatorBuilder.routes()
				
				.route("user-mgmt",p -> p.path("/auth/login/**").and().method(HttpMethod.POST)
						
						.uri("lb://USER-MGMT")
						
						)
				.route("user-mgmt",p -> p.path("/auth/register/**").and().method(HttpMethod.POST)
						
						.uri("lb://USER-MGMT")
						
						)
				.route("user-mgmt",p -> p.path("/users/**")
						.filters( f -> f.filter(jwtfilter)
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()
										)
								
								)
						.uri("lb://USER-MGMT")
						
						)				
				
						.route("products",p -> p.path("/category/**","/product/**").and().method(HttpMethod.GET,HttpMethod.POST,HttpMethod.PUT,HttpMethod.DELETE)
															
								.filters( f -> f.filter(jwtfilter)
										.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()
												)
										)
								
								.uri("lb://PRODUCTS")
								
								)
						.route("orders",p -> p.path("/orders/**").and().method(HttpMethod.GET,HttpMethod.POST,HttpMethod.PUT,HttpMethod.DELETE)
								
								.filters( f -> f.filter(jwtfilter)
										.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()
												)
										)
								
								.uri("lb://ORDERS")
								
								)
						
						.build();
					
// ORDERS

	}
	
	
	/*
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
	    return builder.routes()
	        .route("product-service", r -> r.path("/api/products/**")
	            .filters(f -> f
	                .requestRateLimiter(config -> config
	                    .setRateLimiter(redisRateLimiter())
	                    .setKeyResolver(userKeyResolver()))
	                .filter(jwtAuthenticationFilter.apply(new JwtAuthenticationFilter.Config("USER")))
	                .rewritePath("/api/products/(?<segment>.*)", "/${segment}")
	                .circuitBreaker(config -> config
	                    .setName("productService")
	                    .setFallbackUri("forward:/fallback/product")))
	            .uri("lb://product-service"))
	        .build();
	}
	*/
}


// http:localhost:8080/mayuktha/users/user-mgmt/v1/users/   --> http:localhost:8080/user-mgmt/v1/users/