package com.ssafit.cheajong.model.service;

import java.util.List;

import com.ssafit.cheajong.model.dto.Zzim;

public interface ZzimService {

	int addZzim(Zzim zzim);

	int deleteZzim(int zzimNum);

	List<Zzim> selectByUserId(String userId);
}
