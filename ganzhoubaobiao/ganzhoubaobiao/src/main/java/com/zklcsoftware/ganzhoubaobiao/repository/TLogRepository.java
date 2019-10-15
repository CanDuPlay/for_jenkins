package com.zklcsoftware.ganzhoubaobiao.repository;

import org.springframework.stereotype.Repository;
import com.zklcsoftware.basic.repository.BaseDao;
import com.zklcsoftware.ganzhoubaobiao.domain.TLog;

/**
 * Service Interface:TLog
 * @author administrator
 * @date 2019-3-18
 */
@Repository
public interface TLogRepository extends BaseDao<TLog,Integer>, TLogRepositoryExt {
	
}
