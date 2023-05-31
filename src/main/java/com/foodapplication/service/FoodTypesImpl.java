package com.foodapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodapplication.DAO.FoodTypeDao;
import com.foodapplication.entity.FoodTypes;

@Service
public class FoodTypesImpl implements FoodTypeService{

	@Autowired
	private FoodTypeDao foodTypeDao;
	
	@Override
	@Transactional
	public void setType(FoodTypes foodType) {
		// TODO Auto-generated method stub
		foodTypeDao.save(foodType);
	}

	@Override
	@Transactional
	public Optional<FoodTypes> getFoodTypeByName(String type) {
		// TODO Auto-generated method stub
		return foodTypeDao.findFoodTypesByType(type);
	}

	@Override
	@Transactional
	public List<FoodTypes> getFoodType() {
		// TODO Auto-generated method stub
		return foodTypeDao.findAll();
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		this.foodTypeDao.deleteById(id);
	}

}
