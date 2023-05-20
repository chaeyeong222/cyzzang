package com.ssafit.cheajong.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafit.cheajong.model.dto.User;
import com.ssafit.cheajong.model.service.UserService;
import com.ssafit.cheajong.util.Encrypt;
import com.ssafit.cheajong.util.JwtUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/userapi")
public class UserController {

	@Autowired
	Encrypt ecp;
	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	ResourceLoader resLoader;

	@Autowired
	UserService us;

	/**
	 * 로그인
	 */
	@PostMapping("/user/login")
	public ResponseEntity<?> selectUser(@RequestBody User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String ecpPassword = ecp.getEncrypt(user.getPassword());
			User target = us.searchByUserId(user.getUserId());
			if (user != null && target.getPassword().equals(ecpPassword)) {
				result.put("access-token", jwtUtil.createToken("id", user.getUserId()));
				return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
			} else
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
	public ResponseEntity<?> insertUser(@RequestBody User user, @RequestPart(required = false) MultipartFile file) {
		try {
			// 암호화 방식 추가
			String ecpPassword = ecp.getEncrypt(user.getPassword());
			user.setPassword(ecpPassword);
			// 업로드하는 파일이 존재할 시에 경로 생성후 저장 및 db저장 유저 객체에 img명 추가
			if (!file.isEmpty()) {
				Resource res = resLoader.getResource("resources/upload");
				if (!res.getFile().exists())
					res.getFile().mkdir();
				user.setImg(System.currentTimeMillis() + "_" + file.getOriginalFilename());
				file.transferTo(new File(res.getFile(), user.getImg()));
			}
			int res = us.insert(user);
			return new ResponseEntity<Integer>(res, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 수정
	 */
	@PutMapping("/user")
	public ResponseEntity<?> update(@RequestBody User user, @RequestPart(required = false) MultipartFile file) {
		try {
			if (!file.isEmpty()) {
				Resource res = resLoader.getResource("resources/upload");
				if (!res.getFile().exists())
					res.getFile().mkdir();
				// 다른 것은 가입 시 저장과 동일, 하지만 이미지를 새로 입력시 기존 이미지를 삭제하는 양식 추가
				if (us.searchByUserId(user.getUserId()) != null) {
					String oldImg = us.searchByUserId(user.getUserId()).getImg();
					File target = new File("resources/upload" + "/" + oldImg);
					target.delete();
				}
				user.setImg(System.currentTimeMillis() + "_" + file.getOriginalFilename());
				file.transferTo(new File(res.getFile(), user.getImg()));
			}
			int res = us.update(user);
			return new ResponseEntity<Integer>(res, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
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

}
