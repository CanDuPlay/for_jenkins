package com.zklcsoftware.ganzhoubaobiao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zklcsoftware.basic.service.impl.BaseServiceImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectTown;
import com.zklcsoftware.ganzhoubaobiao.repository.TProjectTownRepository;
import com.zklcsoftware.ganzhoubaobiao.service.TProjectTownService;

@Service
@Transactional
public class TProjectTownServiceImpl extends BaseServiceImpl<TProjectTown, Integer> implements TProjectTownService {

	@Autowired
	private TProjectTownRepository tProjectTownRepository;
	
	@Override
	public List<TProjectTown> findByProjectId(Integer projectId) {
		return tProjectTownRepository.findByProjectId(projectId);
	}

	@Override
	public TProjectTown findByProjectIdAndTownId(Integer id, String townId) {
		return tProjectTownRepository.findByProjectIdAndTownId(id, townId);
	}
	
}
