package com.zklcsoftware.ganzhoubaobiao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zklcsoftware.basic.repository.BaseDao;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectTown;

/**
 * Service Interface:TProjectTown
 * @author administrator
 * @date 2019-3-18
 */
@Repository
public interface TProjectTownRepository extends BaseDao<TProjectTown,Integer>, TProjectTownRepositoryExt {

	List<TProjectTown> findByProjectId(Integer projectId);

	TProjectTown findByProjectIdAndTownId(Integer id, String townId);
	
}
