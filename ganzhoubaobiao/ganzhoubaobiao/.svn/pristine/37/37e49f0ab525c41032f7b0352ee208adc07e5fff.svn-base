package com.zklcsoftware.ganzhoubaobiao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zklcsoftware.basic.service.impl.BaseServiceImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectStage;
import com.zklcsoftware.ganzhoubaobiao.repository.TProjectStageRepository;
import com.zklcsoftware.ganzhoubaobiao.service.TProjectStageService;

@Service
@Transactional
public class TProjectStageServiceImpl extends BaseServiceImpl<TProjectStage, Integer> implements TProjectStageService {

	@Autowired
	private TProjectStageRepository tProjectStageRepository;
	
	@Override
	public TProjectStage findByProjectIdAndName(Integer projectId, String name) {
		return tProjectStageRepository.findByProjectIdAndName(projectId, name);
	}
	
	@Override
	public List<TProjectStage> findByProjectId(Integer projectId) {
		return tProjectStageRepository.findByProjectId(projectId);
	}
	
}
