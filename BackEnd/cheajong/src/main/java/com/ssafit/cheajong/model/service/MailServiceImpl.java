package com.ssafit.cheajong.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.cheajong.model.dto.MailVo;

@Service
@Transactional 
public class MailServiceImpl implements MailService{
	 
	@Autowired
	JavaMailSender mailSender; 
	
	private static final String title = "SSAFIT_CHAEJONG 임시 비밀번호 안내 이메일입니다.";
    private static final String message = "안녕하세요. SSAFIT_CHAEJONG 임시 비밀번호 안내 메일입니다. "
            +"\n" + "회원님의 임시 비밀번호는 아래와 같습니다. 로그인 후 반드시 비밀번호를 변경해주세요!"+"\n";
    private static final String adminAddress = "chaechae9873@gmail.com";
	
	@Override
    public MailVo createMail(String tmpPassword, String memberEmail) {

        MailVo mailVo = new MailVo();
        mailVo.setTitle(title);
        mailVo.setToAddress(memberEmail);
        mailVo.setFromAddress(adminAddress);
        mailVo.setMessage(message + tmpPassword); 

        System.out.println("메일 생성 완료");
        return mailVo;
    }
	
	@Override
    public void sendMail(MailVo mailVo) {
		System.out.println("메일 보낼 준비중 ~~");
		System.out.println(mailVo.getTitle());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mailVo.getToAddress());
        mailMessage.setSubject(mailVo.getTitle());
        mailMessage.setText(mailVo.getMessage());
        mailMessage.setFrom(mailVo.getFromAddress());
        mailMessage.setReplyTo(mailVo.getFromAddress());

        
        System.out.println(mailMessage.getText());
        mailSender.send(mailMessage);

        System.out.println("메일 전송 완료");
    }


}
