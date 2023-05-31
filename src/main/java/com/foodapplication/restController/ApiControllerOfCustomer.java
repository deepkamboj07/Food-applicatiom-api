package com.foodapplication.restController;

import com.foodapplication.entity.*;
import com.foodapplication.exceptions.NotFoundException;
import com.foodapplication.exceptions.UnauthorizedException;
import com.foodapplication.security.JwtTokenHelper;
import com.foodapplication.service.FoodItemService;
import com.foodapplication.service.FoodTypeService;
import com.foodapplication.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/c")
@CrossOrigin("*")
public class ApiControllerOfCustomer {

	@Autowired
	private JwtTokenHelper jwtHelper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FoodTypeService foodTypeService;
	
	@Autowired
	private FoodItemService foodItemService;
	
	@GetMapping("/user")
	public ResponseEntity<User> getUserDetail(
			@RequestHeader (name="Authorization") String tokenHeader,
			@RequestParam("email") String email)	{
		String token=tokenHeader.substring(7);
		String username=this.jwtHelper.getUsernameFromToken(token);
		
		if(!username.equals(email))
		{
			throw new UnauthorizedException("Access Denied!! You are not authorized to access detail of : "+email);
		}
		Optional<User> opt = this.userService.findUserByEmail(email);
		
		if(opt.isEmpty())
		{
			throw new NotFoundException("User not found");
		}
		return new ResponseEntity<>(opt.get(),HttpStatus.OK);
	}
	
	@GetMapping("/food-item")
	public List<FoodItem> getFoodItem()
	{
		return this.foodItemService.findAll();
	}
	
	@GetMapping("/food-type")
	public List<FoodTypes> getFoodTypes()
	{
		return foodTypeService.getFoodType();
	}
	
	@GetMapping("/food-item/sort")
	public List<FoodItem> getFoodItemFiltered(@RequestParam("filter") String filter)
	{
		if(filter.equals("price-lowToHigh"))
		{
			return this.foodItemService.findAllFilterByPrice();
		}
		else if(filter.equals("price-highToLow"))
		{
			return this.foodItemService.findAllFilterByPriceDesc();
		}
		else
		{
			throw new NotFoundException("Filter not valid");
		}
	}
	
	@GetMapping("/food-item/page")
	public List<FoodItem> getFoodItemInPage(@RequestParam("page") int page)
	{
		return this.foodItemService.findByPagination(page,6);
	}
}
