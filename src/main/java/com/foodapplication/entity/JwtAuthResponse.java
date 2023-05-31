package com.foodapplication.entity;

public class JwtAuthResponse {
	
	private String token;
	private int status;
	
	
	public JwtAuthResponse()
	{
		
	}

	public JwtAuthResponse(String token) {
		super();
		this.token = token;
	}

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
