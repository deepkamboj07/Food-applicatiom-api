package com.foodapplication.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapplication.entity.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {

}
