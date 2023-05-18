package com.ssafit.cheajong.model.dao;

import java.util.List;

import com.ssafit.cheajong.model.dto.User;

public interface UserDao {

	int insert(User user);

	User searchByUserId(String userId);

	List<User> selectAll();

	int update(User user);

	int delete(String userId);

}
