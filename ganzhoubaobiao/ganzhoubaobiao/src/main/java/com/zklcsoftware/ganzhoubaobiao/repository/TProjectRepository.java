package com.zklcsoftware.ganzhoubaobiao.repository;

import org.springframework.stereotype.Repository;
import com.zklcsoftware.basic.repository.BaseDao;
import com.zklcsoftware.ganzhoubaobiao.domain.TProject;

/**
 * Service Interface:TProject
 * @author administrator
 * @date 2019-3-18
 */
@Repository
public interface TProjectRepository extends BaseDao<TProject,Integer>, TProjectRepositoryExt {
	
}
