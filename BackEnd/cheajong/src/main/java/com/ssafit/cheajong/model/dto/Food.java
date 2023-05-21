package com.ssafit.cheajong.model.dto;

public class Food {
	
	private int foodNum;
	private String foodName;
	private String category;
	
	public int getFoodNum() {
		return foodNum;
	}
	public void setFoodNum(int foodNum) {
		this.foodNum = foodNum;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Food [foodNum=" + foodNum + ", foodName=" + foodName + ", category=" + category + "]";
	}
	
	

}
