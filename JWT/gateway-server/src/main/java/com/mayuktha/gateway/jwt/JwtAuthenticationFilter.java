package com.mayuktha.gateway.jwt;

import com.mayuktha.gateway.jwt.JwtUtils;
import com.mayuktha.gateway.usersmgmt.security.config.CustomConfigProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GatewayFilter {

	/*
	Verification Process Explained
	When a request arrives:

	Extract Token: From Authorization: Bearer <token> header

	Validate Structure: Check for 3 parts separated by dots

	Verify Signature: Using the secret key

	Check Expiration: Against current time

	Validate Claims: Standard and custom claims

	Authenticate User: If all checks pass
	
	*/
	
	
	@Value("${jwt.secret}")
    private String secret;
	
	
	
	JwtUtils jwtUtil=new JwtUtils();
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.debug("***************************************************************************secret34567**********:{}");
        ServerHttpRequest request = exchange.getRequest();
        
        // Skip authentication for certain paths
        if (request.getURI().getPath().contains("/auth/login") || 
            request.getURI().getPath().contains("/auth/register/")) {
            return chain.filter(exchange);
        }
        logger.debug("*************************************************************************************:request.getURI().getPath()::::{}",request.getURI().getPath());
        
        
        // Check for Authorization header
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
        }
        
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        logger.debug("authHeader.-----------------{}",authHeader);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        	logger.debug("authHeader.startsWith-----------------");
            return onError(exchange, "Invalid authorization header", HttpStatus.UNAUTHORIZED);
        }
        
        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        logger.debug("valid token.-----------------{}",jwtUtil.validateToken(token));
        
        // Add user details to request headers for downstream services
      
        if (!jwtUtil.validateToken(token)) {
            return onError(exchange, "Invalid JWT token", HttpStatus.UNAUTHORIZED);
        }
        
        
       
        Authentication authentication = jwtUtil.getAuthentication(token);
        
        ServerHttpRequest modifiedRequest = request.mutate()
                .header("X-User", username)
                .header("X-User-Roles", "ADMIN")
             //   .header("X-Authenticated-User", authentication.getName())
                .build();
		/*
       
        return chain.filter(exchange.mutate().request(modifiedRequest).build())
				.contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication))
				.then(Mono.fromRunnable(() -> {
					logger.debug("in Mono from runnabl executed after getting response from downstream services....");
                    exchange.getRequest().mutate()
                    .header("X-Authenticated-User", authentication.getName())
                    .build();
            }));
            */
            
        return chain.filter(exchange.mutate().request(modifiedRequest).build());
	}
	private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
		logger.debug("###############################onError:");
        
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        
        return response.setComplete();
    }
}
