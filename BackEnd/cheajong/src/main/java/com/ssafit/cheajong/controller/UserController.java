package com.ssafit.cheajong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.cheajong.model.dto.User;
import com.ssafit.cheajong.model.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/userapi")
public class UserController {

	@Autowired
	UserService us;

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
	@PostMapping("/user")
	@ApiOperation(value = "새로운 user를 등록한다.", response = User.class)
	public ResponseEntity<?> insertUser(@RequestBody User user) {

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
			if (user == null)
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

}
