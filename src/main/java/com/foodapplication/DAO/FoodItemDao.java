package com.foodapplication.DAO;

import com.foodapplication.entity.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemDao extends JpaRepository<FoodItem, Integer> {
	public List<FoodItem> findAllByOrderByPrice();
	public List<FoodItem> findAllByOrderByPriceDesc();
}
