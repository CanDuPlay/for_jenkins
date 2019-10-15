package com.zklcsoftware.ganzhoubaobiao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.zklcsoftware.basic.repository.BaseDao;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectSchool;

/**
 * Service Interface:TProjectSchool
 * @author administrator
 * @date 2019-3-18
 */
@Repository
public interface TProjectSchoolRepository extends BaseDao<TProjectSchool,Integer>, TProjectSchoolRepositoryExt {

	List<TProjectSchool> findByTownId(String townId);

	List<TProjectSchool> findByProjectId(Integer projectId);
	
}
