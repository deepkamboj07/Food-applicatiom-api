package com.foodapplication.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@JsonIgnore
	@Column(name="password")
	private String password;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Role> roles;
	
	@OneToMany(mappedBy="user", cascade= {CascadeType.DETACH,CascadeType.MERGE,
						 CascadeType.PERSIST, CascadeType.REFRESH})
	private List<UserDetail> userDetails;
	
	public User() {
		
	}
	
	public User(int id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<UserDetail> getUserDetail() {
		return userDetails;
	}

	public void setUserDetail(List<UserDetail> userDetails) {
		this.userDetails = userDetails;
	}

	public void addRole(Role role)
	{
		if(roles==null)
		{
			roles=new ArrayList<>();
		}	
		role.setUser(this);
		roles.add(role);
	}
	
	public void addUserDetail(UserDetail userDetail)
	{
		if(userDetails==null)
		{
			userDetails=new ArrayList<>();
		}	
		userDetail.setUser(this);
		userDetails.add(userDetail);
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<SimpleGrantedAuthority> roleList= new ArrayList<>();
		for(Role role:this.roles)
		{
			roleList.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return roleList;
	}


	@Override
	@JsonIgnore
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}


	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	@JsonIgnore
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
