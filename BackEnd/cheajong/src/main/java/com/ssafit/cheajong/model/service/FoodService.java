package com.ssafit.cheajong.model.service;

import java.util.List;

import com.ssafit.cheajong.model.dto.Food;

public interface FoodService {
	List<Food> searchByCategory(String category);

}
