package com.ssafit.cheajong.model.dao;

import com.ssafit.cheajong.model.dto.MailVo;
import com.ssafit.cheajong.model.dto.User;

public interface MailDao {
	
	MailVo createMail(String tmpPassword, String memberEmail);
	
	void sendMail(MailVo mailVo);
	
	User searchByEmail(String userEmail);
	 
	

}
