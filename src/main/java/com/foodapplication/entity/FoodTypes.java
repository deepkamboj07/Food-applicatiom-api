package com.foodapplication.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="food_types")
public class FoodTypes{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="image")
	private String image;
	
	@OneToMany(mappedBy="foodType",fetch=FetchType.LAZY)
	private List<FoodItem> foodItems;

	public FoodTypes()
	{
		
	}
	
	public FoodTypes(String type) {
		super();
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}	
	
	
	public List<FoodItem> getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(List<FoodItem> foodItems) {
		this.foodItems = foodItems;
	}

	public void add(FoodItem foodItem)
	{
		if(foodItems==null)
		{
			foodItems=new ArrayList<>();
		}
		foodItem.setFoodType(this);
		foodItems.add(foodItem);
	}
	
	
	
}
