package com.ssafit.cheajong.model.dto;

public class MailVo {
	private String toAddress; // 받는 이메일 주소
    private String title; // 이메일 제목
    private String message; // 이메일 내용
    private String fromAddress; // 보내는 이메일 주소
    
	public String getToAddress() {
		return toAddress;
	}
	public String getTitle() {
		return title;
	}
	public String getMessage() {
		return message;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	} 
	
	
    
}
