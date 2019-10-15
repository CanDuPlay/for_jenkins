package com.zklcsoftware.ganzhoubaobiao.repository.impl;

import java.util.List;
import java.util.Map;

import com.zklcsoftware.basic.repository.BaseRepositoryExtImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TYearSort;
import com.zklcsoftware.ganzhoubaobiao.repository.TYearSortRepositoryExt;

public class TYearSortRepositoryImpl extends BaseRepositoryExtImpl implements TYearSortRepositoryExt {
	@Override
	public List<TYearSort> findByYearAndProjectIdAndTownTypeSort(Map<String, Object> params) {
		
		return findListObj("findByYearAndProjectIdAndTownTypeSort", params, TYearSort.class);
	}
	@Override
	public List<TYearSort> findOrderByAllInvestDesc(Map<String, Object> params) {
		
		return findListObj("findOrderByAllInvestDesc", params, TYearSort.class);
	}
	@Override
	public List<TYearSort> findOrderByKaigonglvDesc(Map<String, Object> params) {
		
		return findListObj("findOrderByKaigonglvDesc", params, TYearSort.class);
	}
	@Override
	public TYearSort yearSortCount(Map<String, Object> params) {
		
		return findOneObj("yearSortCount", params, TYearSort.class);
	}
}
