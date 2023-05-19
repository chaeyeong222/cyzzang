package com.ssafit.cheajong.model.dao;

import java.util.List;

import com.ssafit.cheajong.model.dto.Zzim;

public interface ZzimDao {
	int addZzim(Zzim zzim);

	int deleteZzim(int zzimNum);

	List<Zzim> selectByUserId(String userId);
}
