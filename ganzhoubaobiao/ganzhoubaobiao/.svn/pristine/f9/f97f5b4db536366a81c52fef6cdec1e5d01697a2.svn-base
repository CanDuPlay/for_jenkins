package com.zklcsoftware.ganzhoubaobiao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zklcsoftware.basic.service.impl.BaseServiceImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TTownSubmit;
import com.zklcsoftware.ganzhoubaobiao.dto.TTownSubmitDTO;
import com.zklcsoftware.ganzhoubaobiao.repository.TTownSubmitRepository;
import com.zklcsoftware.ganzhoubaobiao.service.TTownSubmitService;

@Service
@Transactional
public class TTownSubmitServiceImpl extends BaseServiceImpl<TTownSubmit, Integer> implements TTownSubmitService {

	@Autowired
	private TTownSubmitRepository tTownSubmitRepository;
	
	@Override
	public List<TTownSubmit> findByCollectIdAndIsSubmit(Integer id, Integer isSubmit) {
		return tTownSubmitRepository.findByCollectIdAndIsSubmit(id, isSubmit);
	}

	@Override
	public List<TTownSubmit> findByCollectId(Integer id) {
		return tTownSubmitRepository.findByCollectId(id);
	}

	@Override
	public Page<TTownSubmit> viewSubmitCondition(Map<String, Object> map, Pageable pageable) {
		return tTownSubmitRepository.viewSubmitCondition(map, pageable);
	}

	@Override
	public Page<TTownSubmitDTO> querySubmitDataList(Map<String, Object> map, Pageable pageable) {
		return tTownSubmitRepository.querySubmitDataList(map, pageable);
	}

	@Override
	public TTownSubmit findByCollectIdAndTownId(Integer id, String townId) {
		return tTownSubmitRepository.findByCollectIdAndTownId(id, townId);
	}
}
