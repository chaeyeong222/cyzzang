package com.ssafit.cheajong.model.dto;

public class User {
	private int userNum;
	private String password;
	private String userId;
	private String nickName;
	private String emailAdress;
	private String img;
	private double height;
	private double weight;

	public User() {
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmailAdress() {
		return emailAdress;
	}
	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "User [userNum=" + userNum + ", password=" + password + ", userId=" + userId + ", nickName=" + nickName
				+ ", emailAdress=" + emailAdress + ", img=" + img + ", height=" + height + ", weight=" + weight + "]";
	}


}
