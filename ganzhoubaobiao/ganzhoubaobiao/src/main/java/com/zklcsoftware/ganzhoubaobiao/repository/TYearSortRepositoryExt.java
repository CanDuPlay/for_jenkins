package com.zklcsoftware.ganzhoubaobiao.repository;

import java.util.List;
import java.util.Map;

import com.zklcsoftware.basic.repository.BaseRepositoryExt;
import com.zklcsoftware.ganzhoubaobiao.domain.TYearSort;

/**
 * Service Interface:TYearSort
 * @author administrator
 * @date 2019-3-28
 */
public interface TYearSortRepositoryExt extends BaseRepositoryExt {

	List<TYearSort> findByYearAndProjectIdAndTownTypeSort(Map<String, Object> params);

	List<TYearSort> findOrderByKaigonglvDesc(Map<String, Object> params);

	List<TYearSort> findOrderByAllInvestDesc(Map<String, Object> params);

	TYearSort yearSortCount(Map<String, Object> params);
	
}
