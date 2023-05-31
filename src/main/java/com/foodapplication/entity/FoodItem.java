package com.foodapplication.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="food_items")
@JsonIgnoreProperties(ignoreUnknown=true)
public class FoodItem {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="image")
	private String image;
	
	@Column(name="price")
	private int price;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
						CascadeType.REFRESH,CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="food_type")
	private FoodTypes foodType;
		
	public FoodItem()
	{
		
	}

	public FoodItem(String name, String description, String image, int price) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getFoodType() {
		return foodType.getType();
	}

	public void setFoodType(FoodTypes foodType) {
		this.foodType = foodType;
	}
	
}
