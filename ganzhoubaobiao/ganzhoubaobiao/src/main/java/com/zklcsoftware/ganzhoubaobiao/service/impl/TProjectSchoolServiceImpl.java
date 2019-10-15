package com.zklcsoftware.ganzhoubaobiao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zklcsoftware.basic.service.impl.BaseServiceImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectSchool;
import com.zklcsoftware.ganzhoubaobiao.repository.TProjectSchoolRepository;
import com.zklcsoftware.ganzhoubaobiao.service.TProjectSchoolService;

@Service
@Transactional
public class TProjectSchoolServiceImpl extends BaseServiceImpl<TProjectSchool, Integer> implements TProjectSchoolService {

	@Autowired
	private TProjectSchoolRepository tProjectSchoolRepository;
	
	@Override
	public List<TProjectSchool> findByTownId(String townId) {
		return tProjectSchoolRepository.findByTownId(townId);
	}

	@Override
	public List<TProjectSchool> findByProjectId(Integer projectId) {
		return tProjectSchoolRepository.findByProjectId(projectId);
	}
}
