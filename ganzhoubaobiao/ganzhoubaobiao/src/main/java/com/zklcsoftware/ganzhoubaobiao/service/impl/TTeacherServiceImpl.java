package com.zklcsoftware.ganzhoubaobiao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zklcsoftware.basic.service.impl.BaseServiceImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TTeacher;
import com.zklcsoftware.ganzhoubaobiao.repository.TTeacherRepository;
import com.zklcsoftware.ganzhoubaobiao.service.TTeacherService;

@Service
@Transactional
public class TTeacherServiceImpl extends BaseServiceImpl<TTeacher, Integer> implements TTeacherService {

	@Autowired
	private TTeacherRepository tTeacherRepository;
	
	@Override
	public TTeacher findByGlobalId(String leader) {
		return tTeacherRepository.findByGlobalId(leader);
	}

	@Override
	public TTeacher findByPhoneAndDelFlag(String phone, Integer delFlag) {
		return tTeacherRepository.findByPhoneAndDelFlag(phone, delFlag);
	}
	
}
