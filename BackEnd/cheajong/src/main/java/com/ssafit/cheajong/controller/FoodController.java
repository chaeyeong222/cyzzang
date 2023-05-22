package com.ssafit.cheajong.controller;
 
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController; 

import com.ssafit.cheajong.model.dto.Food; 
import com.ssafit.cheajong.model.service.FoodService; 

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/foodapi")
public class FoodController {
 
	@Autowired
	FoodService fs;
	
	/**
	 *  음식목록을 반환  
	 */
	@GetMapping("/food/{category}")
	@ApiOperation(value = "{category}에 해당하는 음식목록을 반환한다    ." )
	public ResponseEntity<?> getFoodList(@PathVariable String category) { 
		List<Food> food = fs.searchByCategory(category);
		if (food != null)
			return new ResponseEntity<List<Food>>(food, HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
  

}
