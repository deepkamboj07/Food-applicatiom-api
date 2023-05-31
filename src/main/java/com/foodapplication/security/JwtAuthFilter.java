package com.foodapplication.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.foodapplication.exceptions.NotFoundException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenHelper jwtHelper;
	
	//thus class authenticate user with username if it is in database
	@Autowired
	private UserDetailsService userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String responseToken=request.getHeader("Authorization");
		System.out.println("\n\n>>>>>>>///jgjgjg>"+responseToken);
		String username=null;
		String token=null;
		
		if(responseToken==null || !responseToken.startsWith("Bearer"))
		{
			filterChain.doFilter(request, response);
			return;
		}
		
		token=responseToken.substring(7);
		
		username=this.jwtHelper.getUsernameFromToken(token);
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetail=this.userDetailService.loadUserByUsername(username);
			if(this.jwtHelper.validateToken(token, userDetail))
			{
				
				UsernamePasswordAuthenticationToken authToken=
									new UsernamePasswordAuthenticationToken(
											userDetail
											,null
											,userDetail.getAuthorities());
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
			else 
			{
				throw new NotFoundException("Email is not registered!!");
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
