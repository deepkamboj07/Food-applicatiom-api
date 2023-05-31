package com.foodapplication.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {
	
	public static final long JWT_TOKEN_VALIDITY =1000* 60 *24;
	
	private String secret="mysecret";
	
	public String getUsernameFromToken(String token)
	{
		String username=null;
		try {
			username=getClaimForToken(token, Claims::getSubject);
		}catch(Exception ex)
		{
			System.out.println(">>>>>>>>>>>>>exception");
		}
		return username;
	}
	
	public Date getExpirationDateFromToken(String token)
	{
		return getClaimForToken(token, Claims::getExpiration);
	}
	
	private <T> T getClaimForToken(String token, Function<Claims,T> claimsResolver) {
		final Claims claim= getAllClaimsFromToken(token);
		return claimsResolver.apply(claim);
	}

	private Claims getAllClaimsFromToken(String token) {
		
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token)
	{
		final Date expire= getExpirationDateFromToken(token);
		return expire.before(new Date());
	}

	public String genrateToken(UserDetails userDetails)
	{
		Map<String,Object> claims= new HashMap<>();
		return doGenrateToken(claims, userDetails.getUsername());
	}

	private String doGenrateToken(Map<String, Object> claims, String username) {
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetail)
	{
		final String username=getUsernameFromToken(token);
		return (username.equals(userDetail.getUsername()) && !isTokenExpired(token));
	}

}
