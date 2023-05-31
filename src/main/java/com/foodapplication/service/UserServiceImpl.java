package com.foodapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodapplication.DAO.UserDao;
import com.foodapplication.entity.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public Optional<User> findUserByEmail(String email) {
		// TODO Auto-generated method stub
		Optional<User> tmp=userDao.findUserByEmail(email);
		return tmp;
	}

	@Override
	@Transactional
	public List<User> fetchAllUser() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}
	
}
