package com.zklcsoftware.ganzhoubaobiao.repository;

import org.springframework.stereotype.Repository;
import com.zklcsoftware.basic.repository.BaseDao;
import com.zklcsoftware.ganzhoubaobiao.domain.TDataCollect;

/**
 * Service Interface:TDataCollect
 * @author administrator
 * @date 2019-3-18
 */
@Repository
public interface TDataCollectRepository extends BaseDao<TDataCollect,Integer>, TDataCollectRepositoryExt {
	
}
