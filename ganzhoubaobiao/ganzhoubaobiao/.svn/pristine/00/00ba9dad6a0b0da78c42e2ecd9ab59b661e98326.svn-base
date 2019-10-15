package com.zklcsoftware.ganzhoubaobiao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zklcsoftware.basic.service.impl.BaseServiceImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TYearSort;
import com.zklcsoftware.ganzhoubaobiao.repository.TYearSortRepository;
import com.zklcsoftware.ganzhoubaobiao.service.TYearSortService;

@Service
@Transactional
public class TYearSortServiceImpl extends BaseServiceImpl<TYearSort, Integer> implements TYearSortService {
	
	@Autowired
	private TYearSortRepository tYearSortRepository;

	@Override
	public List<TYearSort> removeByYearAndProjectId(String month, Integer projectId) {
		return tYearSortRepository.removeByYearAndProjectId(month, projectId);
	}
	
	@Override
	public List<TYearSort> findByYearAndProjectIdAndTownType(String month, Integer projectId,String town_type) {
		return tYearSortRepository.findByYearAndProjectIdAndTownType(month, projectId,town_type);
	}	
	
	@Override
	public List<TYearSort> findByYearAndProjectIdAndTownTypeSort(Map<String, Object> params) {
		return tYearSortRepository.findByYearAndProjectIdAndTownTypeSort(params);
	}	

	@Override
	public List<TYearSort> findByYearAndProjectIdOrderByAllInvestDesc(Map<String, Object> params) {
		return tYearSortRepository.findOrderByAllInvestDesc(params);
	}

	@Override
	public List<TYearSort> findByYearAndProjectIdOrderByKaigonglvDesc(Map<String, Object> params) {
		return tYearSortRepository.findOrderByKaigonglvDesc(params);
	}
	
	@Override
	public TYearSort yearSortCount(Map<String, Object> params) {
		return tYearSortRepository.yearSortCount(params);
	}
}
