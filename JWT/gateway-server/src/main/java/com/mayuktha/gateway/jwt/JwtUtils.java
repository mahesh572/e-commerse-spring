package com.mayuktha.gateway.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	
	// JWT validation and verification 
	// Validation -  does token has specific claims, or other payload entities
	// Verification - if the token is tampered or not
	
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	public static final String JWT_SECRET_KEY = "JWT_SECRET";
    public static final String JWT_SECRET_DEFAULT_VALUE = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";  
    
    SecretKey secretKey = Keys.hmacShaKeyFor(JWT_SECRET_DEFAULT_VALUE.getBytes(StandardCharsets.UTF_8));

	private final  SecretKey key = Jwts.SIG.HS384.key().build();
	
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(secretKey)
                .build().parseSignedClaims(token).getPayload();
    }
    
    /*
    private void getttt(String token) {
    	
    	Claims claims = Jwts.parser().verifyWith(secretKey)
                .build().parseSignedClaims(token).getPayload();
       String username = String.valueOf(claims.get("username"));
       String authorities = String.valueOf(claims.get("authorities"));
    }
    
    */
    
    
    
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    public Boolean validateToken(String token) {
    	Claims claims = Jwts.parser().verifyWith(secretKey)
    			.requireIssuer("Mayuktha")
    			// .requireSubject("JWT Token")
                .build().parseSignedClaims(token).getPayload();
    	logger.debug("claims:::::::::::::::::::::::::::{}",claims);
      // String username = String.valueOf(claims.get("username"));
      // String authorities = String.valueOf(claims.get("authorities"));
       
    	return !isTokenExpired(token);
	}
    
    
    private SecretKey getSigningKey() {
    	
       // return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    	return key;
    }
    /*

    public String generateToken(String username){
        return Jwts.builder()
        		.issuer("MAHESH")
        		.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 1))
                .subject(username)
              //  .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(key,Jwts.SIG.HS384)
                .compact();
    }
    
    */
    
    public Authentication getAuthentication(String token) {
        Claims claims = extractAllClaims(token);
       // String username = claims.getSubject();
        String username = (String) claims.get("username");
        Collection<? extends GrantedAuthority> authorities = extractAuthorities(claims);
        
        return new UsernamePasswordAuthenticationToken(
            username, null, authorities);
    }
    private Collection<? extends GrantedAuthority> extractAuthorities(Claims claims) {
        List<String> roles = List.of((String)claims.get("authorities"));
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .collect(Collectors.toList());
    }
}

