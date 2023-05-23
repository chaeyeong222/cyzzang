package com.ssafit.cheajong.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.cheajong.model.dao.UserDao;
import com.ssafit.cheajong.model.dto.User; 

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao uDao;

	 
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


	@Override
	public int update(User user) {
		System.out.println("서비스스ㅡ");
		return uDao.update(user);
	}
 
	@Override
	public int delete(String userId) { 
		return uDao.delete(userId);
	}
	 
	@Override
	public User searchByEmail(String userEmail) { 
		return uDao.searchByUserEmail(userEmail);
	}
	

    /** 임시로 생성된 비밀번호를 디비로 전송하여 업데이트하는 메서드 **/ 
    @Override
    public int updateToNewPassword( User user ) {
        return uDao.updateToNewPassword( user );
    }
	

}
