package com.ssafit.cheajong.model.service;

import com.ssafit.cheajong.model.dto.MailVo;

public interface MailService { 
		
		MailVo createMail(String tmpPassword, String memberEmail);
		
		void sendMail(MailVo mailVo); 


}
