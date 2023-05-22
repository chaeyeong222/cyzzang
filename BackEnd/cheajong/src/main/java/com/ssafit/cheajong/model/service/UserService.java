package com.ssafit.cheajong.model.service;

import java.util.List;

import com.ssafit.cheajong.model.dto.User;

public interface UserService {
	int insert(User user);

	User searchByUserId(String userId);

	List<User> selectAll();

	int update(User user);

	int delete(String userId);

	User searchByEmail(String userEmail);//디비상에 존재하는 이메일인지 확인하는 메서드
	
	int updateToNewPassword(User user);
}
