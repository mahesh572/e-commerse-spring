package com.mayuktha.orders.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.debug("JwtAuthenticationFilter:::in orders::::doFilterInternal::::::::::START");
		
		String userId = request.getHeader("X-User");
        String roles = request.getHeader("X-User-Roles");
        logger.debug("HeaderAuthenticationFilter:::::::doFilterInternal::in orders::::userId::::{}",userId);
        logger.debug("HeaderAuthenticationFilter:::::::doFilterInternal:::in orders:::roles::::{}",roles);
        if (userId != null) {
            List<GrantedAuthority> authorities = Arrays.stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
            
            logger.debug("HeaderAuthenticationFilter:::orders::::doFilterInternal::::authorities::::::{}",authorities);
            
            Authentication auth = new UsernamePasswordAuthenticationToken(
                userId, null, authorities);
            
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        
        System.out.println("SecurityContextHolder:******************************************************************************************************* " + SecurityContextHolder.getContext().getAuthentication());
        
        filterChain.doFilter(request, response);
        System.out.println("After doFilter:%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%ordersorders%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5 " + SecurityContextHolder.getContext().getAuthentication());
	}

}
