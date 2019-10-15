package com.zklcsoftware.ganzhoubaobiao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zklcsoftware.basic.repository.BaseDao;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectStage;

/**
 * Service Interface:TProjectStage
 * @author administrator
 * @date 2019-3-18
 */
@Repository
public interface TProjectStageRepository extends BaseDao<TProjectStage,Integer>, TProjectStageRepositoryExt {

	TProjectStage findByProjectIdAndName(Integer projectId, String name);
	
	List<TProjectStage> findByProjectId(Integer projectId);
}
