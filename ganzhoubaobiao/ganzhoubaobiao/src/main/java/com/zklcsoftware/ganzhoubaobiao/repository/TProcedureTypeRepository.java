package com.zklcsoftware.ganzhoubaobiao.repository;

import org.springframework.stereotype.Repository;

import com.zklcsoftware.basic.repository.BaseDao;
import com.zklcsoftware.ganzhoubaobiao.domain.TProcedureType;

/**
 * Service Interface:TProcedureType
 * @author administrator
 * @date 2019-3-18
 */
@Repository
public interface TProcedureTypeRepository extends BaseDao<TProcedureType,Integer>, TProcedureTypeRepositoryExt {

	TProcedureType findByName(String title);

}
