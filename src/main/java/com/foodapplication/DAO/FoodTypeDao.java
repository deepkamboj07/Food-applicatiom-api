package com.foodapplication.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapplication.entity.FoodTypes;

public interface FoodTypeDao extends JpaRepository<FoodTypes, Integer>{
	public Optional<FoodTypes> findFoodTypesByType(String type);
}
