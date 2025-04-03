package com.mayuktha.usersmgmt.jwt;

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
		logger.debug("HeaderAuthenticationFilter:::::::doFilterInternal:::usermgmt:::::::START");
		
		String userId = request.getHeader("X-User");
        String roles = request.getHeader("X-User-Roles");
        logger.debug("HeaderAuthenticationFilter:::::::doFilterInternal:::usermgmt:::userId::::{}",userId);
        logger.debug("HeaderAuthenticationFilter:::::::doFilterInternal:::usermgmt:::roles::::{}",roles);
        if (userId != null) {
            List<GrantedAuthority> authorities = Arrays.stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
            
            logger.debug("HeaderAuthenticationFilter:::::::doFilterInternal::::authorities::::::{}",authorities);
            
            Authentication auth = new UsernamePasswordAuthenticationToken(
                userId, null, authorities);
            
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        
        System.out.println("SecurityContextHolder:******************************************************************************************************* " + SecurityContextHolder.getContext().getAuthentication());
        
        filterChain.doFilter(request, response);
        System.out.println("After doFilter:%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5 " + SecurityContextHolder.getContext().getAuthentication());
	}

}
