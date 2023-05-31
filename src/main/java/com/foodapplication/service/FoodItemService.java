package com.foodapplication.service;

import java.util.List;

import com.foodapplication.entity.FoodItem;

public interface FoodItemService {
	public void save(FoodItem foodItem);
	public List<FoodItem> findAll();
	public List<FoodItem> findAllFilterByPrice();
	public List<FoodItem> findAllFilterByPriceDesc();
	public void deleteById(int id);
	public List<FoodItem> findByPagination(int page, int item);
}
