package com.ssafit.cheajong.model.service;

import com.ssafit.cheajong.model.dto.MailVo;

public interface MailService { 
		
		MailVo createMail(String tmpPassword, String memberEmail);//임시비밀번호 발송용 메서드   
		
		void sendMail(MailVo mailVo); 
		
		String getTmpPassword();
		
		MailVo createMailForAuthentic(String authenticNum, String memberEmail, int num);// 이메일 인증용 메서드  
		  
}
