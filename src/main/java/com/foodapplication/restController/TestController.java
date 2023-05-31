package com.foodapplication.restController;

import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/")
	public String getDemo()
	{
		return "Welcome to Oregano Rest Api";
	}
	
}
