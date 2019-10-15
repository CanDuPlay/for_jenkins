package com.zklcsoftware.ganzhoubaobiao.service;

import java.util.List;

import com.zklcsoftware.basic.service.BaseService;
import com.zklcsoftware.ganzhoubaobiao.domain.TSchoolInverted;

public interface TSchoolInvertedService extends BaseService<TSchoolInverted,Integer> {

	List<TSchoolInverted> findByTProjectSchoolId(Integer tProjectSchoolId);
}
