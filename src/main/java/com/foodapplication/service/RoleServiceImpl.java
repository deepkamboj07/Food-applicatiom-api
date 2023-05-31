package com.foodapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodapplication.DAO.RoleDao;
import com.foodapplication.entity.Role;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	@Override
	@Transactional
	public void addRole(Role role) {
		roleDao.save(role);
	}

}
