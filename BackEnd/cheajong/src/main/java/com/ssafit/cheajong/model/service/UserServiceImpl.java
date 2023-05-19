package com.ssafit.cheajong.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.cheajong.model.dao.UserDao;
import com.ssafit.cheajong.model.dto.User; 

@Service
public class UserServiceImpl implements UserService{
	
	
	private UserDao uDao;
 
	@Autowired
	public UserServiceImpl(UserDao uDao) { 
		this.uDao = uDao;
	}

	@Transactional 
	@Override
	public int insert(User user) { 
		return uDao.insert(user);
	}

	@Override
	public User searchByUserId(String userId) { 
		return uDao.searchByUserId(userId);
	}

	@Override
	public List<User> selectAll() { 
		return uDao.selectAll();
	}
	
	@Transactional
	@Override
	public int update(User user) {
		return uDao.update(user);
	}

	@Transactional
	@Override
	public int delete(String userId) { 
		return uDao.delete(userId);
	}

}
