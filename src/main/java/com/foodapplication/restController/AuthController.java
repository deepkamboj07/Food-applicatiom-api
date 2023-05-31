package com.foodapplication.restController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.foodapplication.entity.JwtAuthResponse;
import com.foodapplication.entity.LoginData;
import com.foodapplication.entity.MessageResponse;
import com.foodapplication.entity.Role;
import com.foodapplication.entity.User;
import com.foodapplication.security.JwtTokenHelper;
import com.foodapplication.service.RoleService;
import com.foodapplication.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired    
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private JwtTokenHelper jwtHeler;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/login")
	public JwtAuthResponse logUser(
			@RequestBody LoginData data)
	{
		String username=data.getEmail();
		String password=data.getPassword();
		
		UsernamePasswordAuthenticationToken authToken
			=new UsernamePasswordAuthenticationToken(username,password);
		this.authManager.authenticate(authToken);
		
		UserDetails userDetail= this.userDetailService.loadUserByUsername(username);
		String token= this.jwtHeler.genrateToken(userDetail);
		
		JwtAuthResponse response= new JwtAuthResponse(token);
		response.setStatus(HttpStatus.OK.value());
		return response;
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<MessageResponse> registerUser(
			@RequestBody User user)
	{
		MessageResponse msg = new MessageResponse();
		user.setId(0);
		String password=this.bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(password);
		
		Optional<User> check=this.userService.findUserByEmail(user.getEmail());
		if(!check.isEmpty())
		{
			msg.setStatus(HttpStatus.BAD_GATEWAY.value());
			msg.setMessage("Email is already registered");
			return new ResponseEntity<>(msg,HttpStatus.BAD_GATEWAY);
		}
		this.userService.saveUser(user);
		Role role= new Role();
		role.setRole("ROLE_CUSTOMER");
		user.addRole(role);
		this.roleService.addRole(role);
		msg.setStatus(HttpStatus.OK.value());
		msg.setMessage("User is registered with email :"+user.getEmail());
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
}
