package com.foodapplication.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.foodapplication.entity.User;
import com.foodapplication.exceptions.NotFoundException;
import com.foodapplication.service.*;

@Component
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user=userService.findUserByEmail(username);
		if(user.isEmpty() || user.get()==null)
		{
			throw new NotFoundException("User Not Found");
		}
		return user.get();
	}

}
