package com.foodapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodapplication.DAO.FoodItemDao;
import com.foodapplication.entity.FoodItem;

@Service
public class FoodItemServiceImpl implements FoodItemService {

	@Autowired
	private FoodItemDao foodItemDao;
	
	@Override
	@Transactional
	public void save(FoodItem foodItem) {
		// TODO Auto-generated method stub
		this.foodItemDao.save(foodItem);
	}

	@Override
	@Transactional
	public List<FoodItem> findAll() {
		// TODO Auto-generated method stub
		return this.foodItemDao.findAll();
	}

	@Override
	@Transactional
	public List<FoodItem> findAllFilterByPrice() {
		// TODO Auto-generated method stub
		return this.foodItemDao.findAllByOrderByPrice();
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		this.foodItemDao.deleteById(id);
	}

	@Override
	@Transactional
	public List<FoodItem> findByPagination(int page, int size) {
		// TODO Auto-generated method stub
		
		Pageable p= PageRequest.of(page, size);
		
		Page<FoodItem> pageItem=this.foodItemDao.findAll(p);
		return pageItem.getContent();
	}

	@Override
	@Transactional
	public List<FoodItem> findAllFilterByPriceDesc() {
		// TODO Auto-generated method stub
		return this.foodItemDao.findAllByOrderByPriceDesc();
	}

}
