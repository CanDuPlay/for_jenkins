package com.zklcsoftware.ganzhoubaobiao.service;

import java.util.List;
import java.util.Map;

import com.zklcsoftware.basic.service.BaseService;
import com.zklcsoftware.ganzhoubaobiao.domain.TYearSort;

public interface TYearSortService extends BaseService<TYearSort,Integer> {

	List<TYearSort> removeByYearAndProjectId(String month,Integer projectId);

	List<TYearSort> findByYearAndProjectIdAndTownType(String month, Integer projectId, String town_type);

	List<TYearSort> findByYearAndProjectIdAndTownTypeSort(Map<String, Object> params);

	List<TYearSort> findByYearAndProjectIdOrderByAllInvestDesc(Map<String, Object> params);

	List<TYearSort> findByYearAndProjectIdOrderByKaigonglvDesc(Map<String, Object> params);

	TYearSort yearSortCount(Map<String, Object> params);
}
