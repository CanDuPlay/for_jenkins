package com.zklcsoftware.ganzhoubaobiao.service;

import java.util.List;

import com.zklcsoftware.basic.service.BaseService;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectSchool;

public interface TProjectSchoolService extends BaseService<TProjectSchool,Integer> {

	List<TProjectSchool> findByTownId(String townId);

	List<TProjectSchool> findByProjectId(Integer projectId);

	
	
}
