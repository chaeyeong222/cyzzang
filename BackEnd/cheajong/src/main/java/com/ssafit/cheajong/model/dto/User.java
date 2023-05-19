package com.ssafit.cheajong.model.dto;

public class User {
	private int userNum;
	private String password;
	private String userId;
	private String nickName;
	private String emailAddress;
	private String img;

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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "User [userNum=" + userNum + ", password=" + password + ", userId=" + userId + ", nickName=" + nickName
				+ ", emailAddress=" + emailAddress + ", img=" + img + "]";
	}

}
