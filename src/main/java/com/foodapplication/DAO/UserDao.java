package com.foodapplication.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapplication.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

	Optional<User> findUserByEmail(String email);
}
