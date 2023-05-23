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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafit.cheajong.model.dto.MailVo;
import com.ssafit.cheajong.model.dto.User;
import com.ssafit.cheajong.model.service.MailService;
import com.ssafit.cheajong.model.service.UserService;
import com.ssafit.cheajong.util.Encrypt;
import com.ssafit.cheajong.util.JwtUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/userapi")
@CrossOrigin("*")
public class UserController {

	@Autowired
	Encrypt ecp; // db 암호화를 위한 코드

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	ResourceLoader resLoader;

	@Autowired
	UserService us;

	@Autowired
	MailService ms;

	/**
	 * 로그인
	 */
	@PostMapping("/login")
	public ResponseEntity<?> selectUser(@RequestBody User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// 암호화된 비밀번호 비교를 통해서 로그인 가능여부 판단
			String ecpPassword = ecp.getEncrypt(user.getPassword());
			User target = us.searchByUserId(user.getUserId());
			// 아이디와 비밀번호 모두 일치할 경우 토큰 생성해서 반환
			if (user != null && target.getPassword().equals(ecpPassword)) {
				Map<String, Object> returnUser = new HashMap<>();
				returnUser.put("userId", target.getUserId());
				returnUser.put("nickName", target.getNickName());
				returnUser.put("height", target.getHeight());
				returnUser.put("weight", target.getWeight());
				String token = jwtUtil.createToken(returnUser);
				result.put("access-token", token);
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
	@PostMapping("/user")
	@ApiOperation(value = "새로운 user를 등록한다.", response = User.class)
	public ResponseEntity<?> insertUser(@RequestBody User user) {
		try {
			// 암호화 방식 추가
			String ecpPassword = ecp.getEncrypt(user.getPassword());
			user.setPassword(ecpPassword);
			// 업로드하는 파일이 존재할 시에 경로 생성후 저장 및 db저장 유저 객체에 img명 추가
//			if (!file.isEmpty()) {
//				Resource res = resLoader.getResource("resources/upload");
//				if (!res.getFile().exists())
//					res.getFile().mkdir();
//				user.setImg(System.currentTimeMillis() + "_" + file.getOriginalFilename());
//				file.transferTo(new File(res.getFile(), user.getImg()));
//			}
			int res = us.insert(user);
			System.out.println(res);
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
	@ApiOperation(value = "{userId}에 해당하는 user가 이미 존재 하는지 확인한다  .")
	public ResponseEntity<?> duplicateCheck(@PathVariable String userId) {
		try {
			User user = us.searchByUserId(userId);
			System.out.println(user);
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

	/**
	 * 이메일 보내기
	 */

	@Transactional
	@PutMapping("/email")
	@ApiOperation(value = "임시 비밀번호 생성해서 db 수정 후, 이메일 보내기기능    .")
	public ResponseEntity<?> sendEmail(@RequestParam("memberEmail") String memberEmail) {
		System.out.println("memberEmail " + memberEmail);

		/** 임시 비밀번호 생성 **/
		String tmpPassword = ms.getTmpPassword();
		String ecpPassword = ecp.getEncrypt(ms.getTmpPassword());
		System.out.println("임시 비밀번호 " + tmpPassword);
		System.out.println("암호화  비밀번호 " + ecpPassword);

		User user = us.searchByEmail(memberEmail);

		/** 임시 비밀번호 저장 **/
		user.setPassword(ecpPassword);
		us.updateToNewPassword(user);

		// 이메일 보내기
		MailVo mail = ms.createMail(tmpPassword, memberEmail);
		ms.sendMail(mail);

		System.out.println("임시 비밀번호 전송 완료");

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * 가입된 이메일 있는 지 체크
	 */
	@GetMapping("/email/{emailAdress}")
	@ApiOperation(value = "{emailAdress}에 해당하는 이메일이 이미 존재 하는지 확인한다  .")
	public ResponseEntity<?> emailCheck(@PathVariable String emailAdress) {
		try {
			User emailCheck = us.searchByEmail(emailAdress);
			System.out.println("emailAdress " + emailAdress);
			String authenticNum = "";
			if (emailCheck == null) {
				authenticNum = ms.getTmpPassword(); // 인증번호 생성
				System.out.println("인증번호  " + authenticNum);
				// 이메일 보내기
				MailVo mail = ms.createMailForAuthentic(authenticNum, emailAdress, 1);
				ms.sendMail(mail);
			}
			return new ResponseEntity<String>(authenticNum, HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

}
