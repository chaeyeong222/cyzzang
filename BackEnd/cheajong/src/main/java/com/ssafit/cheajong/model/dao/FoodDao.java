package com.ssafit.cheajong.model.dao;

import java.util.List;

import com.ssafit.cheajong.model.dto.Food; 

public interface FoodDao {
   
	List<Food> searchByUserId(String category); 
	 

}
