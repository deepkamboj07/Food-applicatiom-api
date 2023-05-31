package com.foodapplication.entity;

import javax.persistence.*;

@Entity
@Table(name="user_detail")
public class UserDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="full_address")
	private String address;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,
						 CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private User user;
	
	
	public UserDetail() {
		
	}
	
	public UserDetail(String address, String phoneNumber) {
		super();
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
