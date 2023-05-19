package com.ssafit.cheajong.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.cheajong.model.dao.ZzimDao;
import com.ssafit.cheajong.model.dto.Zzim;

@Service
public class ZzimServiceImpl implements ZzimService {

	@Autowired
	ZzimDao zd;

	@Override
	public int addZzim(Zzim zzim) {
		return zd.addZzim(zzim);
	}

	@Override
	public int deleteZzim(int zzimNum) {
		return zd.deleteZzim(zzimNum);
	}

	@Override
	public List<Zzim> selectByUserId(String userId) {
		return zd.selectByUserId(userId);
	}

}
