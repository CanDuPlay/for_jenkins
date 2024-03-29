package com.zklcsoftware.ganzhoubaobiao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zklcsoftware.basic.repository.BaseDao;
import com.zklcsoftware.ganzhoubaobiao.domain.TProcedure;

/**
 * Service Interface:TProcedure
 * @author administrator
 * @date 2019-3-18
 */
@Repository
public interface TProcedureRepository extends BaseDao<TProcedure,Integer>, TProcedureRepositoryExt {

	/**
	 * 根据程序流程类型id查询程序流程
	 * @param procedureId
	 * @return
	 */
	List<TProcedure> findByTProcedureTypeId(Integer procedureId);

	/**
	 * 根据程序流程类型id查询程序流程(id正序排列)
	 * @param id
	 * @return
	 */
	List<TProcedure> findByTProcedureTypeIdOrderByIdAsc(Integer id);
	
}
