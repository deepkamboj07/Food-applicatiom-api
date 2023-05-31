package com.foodapplication.service;

import java.util.List;
import java.util.Optional;

import com.foodapplication.entity.FoodTypes;

public interface FoodTypeService {
	public void setType(FoodTypes foodType);
	public Optional<FoodTypes> getFoodTypeByName(String type);
	public List<FoodTypes> getFoodType();
	public void deleteById(int id);
}
