package com.ssafit.cheajong.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.cheajong.model.dao.FoodDao;
import com.ssafit.cheajong.model.dao.UserDao;
import com.ssafit.cheajong.model.dto.Food;
import com.ssafit.cheajong.model.dto.User; 

@Service
@Transactional
public class FoodServiceImpl implements FoodService{

	@Autowired
	private FoodDao fDao; 
	
	@Override
	public List<Food> searchByCategory(String category) { 
		return fDao.searchByUserId(category);
	}
	
	 
}
