package com.ssafit.cheajong.model.dao;

import java.util.List;
 
import com.ssafit.cheajong.model.dto.User;

public interface UserDao {

	int insert(User user);

	User searchByUserId(String userId);

	List<User> selectAll();

	int update(User user);

	int delete(String userId);
	
	User searchByUserEmail(String userEmail); //디비 상에 존재하는 이메일인지 확인하기  
	
	boolean updateToNewPassword(String tmpPassword, String memberEmail); //디비상의 비밀번호를 임시비밀번호로 업데이트하기  
	 

}
