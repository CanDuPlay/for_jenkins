package com.zklcsoftware.ganzhoubaobiao.service;

import com.zklcsoftware.basic.service.BaseService;
import com.zklcsoftware.ganzhoubaobiao.domain.TTeacher;

public interface TTeacherService extends BaseService<TTeacher,Integer> {

	TTeacher findByGlobalId(String leader);

	TTeacher findByPhoneAndDelFlag(String phone, Integer delFlag);
	
}
