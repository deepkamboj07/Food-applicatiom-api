package com.foodapplication.restController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.foodapplication.entity.*;
import com.foodapplication.exceptions.NotFoundException;
import com.foodapplication.service.FoodItemService;
import com.foodapplication.service.FoodTypeService;
import com.foodapplication.service.UserService;

@RestController
@RequestMapping("/api/a")
@CrossOrigin("*")
public class ApiControllerOfAdmin {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FoodTypeService foodTypeService;
	
	@Autowired
	private FoodItemService foodItemService;
	
	@GetMapping("/users")
	public List<User> getAllUsers()
	{
		return userService.fetchAllUser();
	}
	
	@PostMapping("/food-type")
	public ResponseEntity<MessageResponse> setFoodType(@RequestBody FoodTypes foodType)
	{
		Optional<FoodTypes> output=foodTypeService.getFoodTypeByName(foodType.getType());
		
		if(!output.isEmpty())
		{
			return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),"Food type already exists"),HttpStatus.BAD_REQUEST);
		}
		foodTypeService.setType(foodType);
		return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),"Food type added sucessfully"),HttpStatus.OK);
	}
		
	
	@PostMapping("/food-item/{type}")
	public ResponseEntity<MessageResponse> postFoodItem(
			@RequestBody FoodItem foodItem, @PathVariable String type)
	{
		Optional<FoodTypes> opt= this.foodTypeService.getFoodTypeByName(type);
		if(opt.isEmpty())
		{
			throw new NotFoundException("Food Type is Unknown!! Please Check Again");
		}
		
		FoodTypes foodType=opt.get();
		foodType.add(foodItem);
		
		this.foodItemService.save(foodItem);
		
		return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),"Food Item added sucessfully"),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/food-item/delete")
	public ResponseEntity<MessageResponse> deleteFoodItem(@RequestParam("id") int id)
	{
		
		this.foodItemService.deleteById(id);
		return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),"Deleted Suscessfull"),
				HttpStatus.OK);
	}
	
	@DeleteMapping("/food-type/delete")
	public ResponseEntity<MessageResponse> deleteFoodType(@RequestParam("id") int id)
	{
		
		this.foodTypeService.deleteById(id);
		return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),"Deleted Suscessfull"),
				HttpStatus.OK);
	}
	
	
	
}
