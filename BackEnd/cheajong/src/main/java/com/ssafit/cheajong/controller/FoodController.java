package com.ssafit.cheajong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.cheajong.model.dto.Food;
import com.ssafit.cheajong.model.service.FoodService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/foodapi")
@CrossOrigin("*")
public class FoodController {

	@Autowired
	FoodService fs;

	/**
	 * 음식목록을 반환
	 */
	@GetMapping("/food")
	@ApiOperation(value = "{category}에 해당하는 음식목록을 반환한다.")
	public ResponseEntity<?> getFoodList(@PathVariable String category) {
		List<Food> foodList1 = fs.searchByCategory("기본채소"); // veg1
		List<Food> foodList2 = fs.searchByCategory("탄수화물"); // carb
		List<Food> foodList3 = fs.searchByCategory("보충채소"); // veg2
		List<Food> foodList4 = fs.searchByCategory("단백질");	// prot
		List<Food> foodList5 = fs.searchByCategory("간식");	// snack
		List<Food> foodList6 = fs.searchByCategory("과일");	//fruit

		Map<String, String> result = new HashMap<>();
		result.put("veg1", foodList1.get(new Random().nextInt(foodList1.size())).getFoodName());
		result.put("carb", foodList2.get(new Random().nextInt(foodList2.size())).getFoodName());
		result.put("veg2", foodList3.get(new Random().nextInt(foodList3.size())).getFoodName());
		result.put("prot", foodList4.get(new Random().nextInt(foodList4.size())).getFoodName());
		result.put("snack", foodList5.get(new Random().nextInt(foodList5.size())).getFoodName());
		result.put("fruit", foodList6.get(new Random().nextInt(foodList6.size())).getFoodName());

		return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
	}

}
