package com.ssafit.cheajong.model.dao;

import com.ssafit.cheajong.model.dto.MailVo;

public interface MailDao {

	MailVo createMail(String tmpPassword, String memberEmail);
	
	void sendMail(MailVo mailVo);

}
