package com.foodapplication.service;
import java.util.List;
import java.util.Optional;

import com.foodapplication.entity.*;
public interface UserService {
	public Optional<User> findUserByEmail(String email);
	public List<User> fetchAllUser();
	public void saveUser(User user);
}
