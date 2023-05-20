package com.ssafit.cheajong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.cheajong.model.dto.MailVo;
import com.ssafit.cheajong.model.dto.User;
import com.ssafit.cheajong.model.service.MailService;
import com.ssafit.cheajong.model.service.MailServiceImpl;
import com.ssafit.cheajong.model.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/userapi")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserService us;
	 
	@Autowired
	MailService ms;
	 
	/**
	 * 로그인
	 */
	@PostMapping("/user/{userId}")
	@ApiOperation(value = "{userId}에 해당하는 user를 반환한다.", response = User.class)
	public ResponseEntity<?> selectUser(@PathVariable String userId) {
		try {
			User user = us.searchByUserId(userId);
			if (user != null)
				return new ResponseEntity<User>(user, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	/**
	 * 회원가입
	 */
	@PostMapping("/user/regist")
	@ApiOperation(value = "새로운 user를 등록한다.", response = User.class)
	public ResponseEntity<?> insertUser(@RequestBody User user) {
		System.out.println("생");
		try {
			int res = us.insert(user);
			return new ResponseEntity<Integer>(res, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 수정
	 */
	@PutMapping("/user")
	public ResponseEntity<Void> update(@RequestBody User user) {
		us.update(user);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * 회원탈퇴
	 */
	@DeleteMapping("/user/{userId}")
	@ApiOperation(value = "{userId}에 해당하는 user를 삭제한다.", response = Integer.class)
	public ResponseEntity<?> delete(@PathVariable String userId) {
		try {
			int result = us.delete(userId);
			return new ResponseEntity<Integer>(result, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	/**
	 * 회원 중복 확인
	 */
	@GetMapping("/user/{userId}")
	@ApiOperation(value = "{userId}에 해당하는 user가 이미 존재 하는지 확인한다  .", response = Boolean.class)
	public ResponseEntity<?> duplicateCheck(@PathVariable String userId) {
		try {
			User user = us.searchByUserId(userId);
			if (user != null)
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			else
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	/**
	 * 회원 전체 조회
	 */

	@GetMapping("/user")
	@ApiOperation(value = "회원 전체 조회      .")
	public ResponseEntity<?> selectAll() {
		try {
			List<User> users = us.selectAll();
			if (users != null)
				return new ResponseEntity<List<User>>(users, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	/**
	 * 이메일 보내기     
	 */

	@PostMapping("/email")
	@ApiOperation(value = "이메일 보내기기능    .")
	public ResponseEntity<?> sendEmail(@RequestParam("memberEmail") String memberEmail) {
		System.out.println("memberEmail " + memberEmail);
		/** 임시 비밀번호 생성 **/
//      String tmpPassword = us.getTmpPassword();
		String tmpPassword ="임시 비번   ";

      /** 임시 비밀번호 저장 **/
//      us.updatePassword(tmpPassword, memberEmail);
	//	MailVo mail = us.createMail(tmpPassword, memberEmail);
	//	us.sendMail(mail);
//		MailService ms = new MailServiceImpl();
		MailVo mail = ms.createMail(tmpPassword ,memberEmail);
        ms.sendMail(mail);

        System.out.println("임시 비밀번호 전송 완료");
		
	 
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	 
//	@PostMapping("/sendPwd")
//    public String sendPwdEmail(@RequestParam("memberEmail") String memberEmail) {
//
//        log.info("sendPwdEmail 진입");
//        log.info("이메일 : "+ memberEmail);
//
//        /** 임시 비밀번호 생성 **/
//        String tmpPassword = memberService.getTmpPassword();
//
//        /** 임시 비밀번호 저장 **/
//        memberService.updatePassword(tmpPassword, memberEmail);
//
//        /** 메일 생성 & 전송 **/
//        MailVo mail = mailService.createMail(tmpPassword, memberEmail);
//        mailService.sendMail(mail);
//
//        log.info("임시 비밀번호 전송 완료");
//
//        return "member/member-login";
//    }
	


}
