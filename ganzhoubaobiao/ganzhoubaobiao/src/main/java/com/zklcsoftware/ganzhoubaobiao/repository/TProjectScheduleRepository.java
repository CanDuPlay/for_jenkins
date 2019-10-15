package com.zklcsoftware.ganzhoubaobiao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zklcsoftware.basic.repository.BaseDao;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectSchedule;

/**
 * Service Interface:TProjectSchedule
 * @author administrator
 * @date 2019-3-18
 */
@Repository
public interface TProjectScheduleRepository extends BaseDao<TProjectSchedule,Integer>, TProjectScheduleRepositoryExt {

	/**
	 * 根据项目阶段id和进度名称查询项目进度
	 * @param tProcedureId
	 * @param name
	 * @return
	 */
	TProjectSchedule findByProjectStageAndName(Integer tProcedureId, String name);

	List<TProjectSchedule> findByProjectStage(Integer id);

	TProjectSchedule findByProjectStageAndProcedureId(Integer id, Integer selectValue);

	List<TProjectSchedule> findByProcedureId(Integer id);
	
}
