package com.zklcsoftware.ganzhoubaobiao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.zklcsoftware.basic.repository.BaseDao;
import com.zklcsoftware.ganzhoubaobiao.domain.TYearSort;

/**
 * Service Interface:TYearSort
 * @author administrator
 * @date 2019-3-28
 */
@Repository
public interface TYearSortRepository extends BaseDao<TYearSort,Integer>, TYearSortRepositoryExt {

	List<TYearSort> removeByYearAndProjectId(String month, Integer projectId);

	List<TYearSort> findByYearAndProjectIdAndTownType(String month, Integer projectId, String town_type);

	List<TYearSort> findByYearAndProjectIdOrderByAllInvestDesc(String month, Integer projectId);

	List<TYearSort> findByYearAndProjectIdOrderByKaigonglvDesc(String month, Integer projectId);
	
}
