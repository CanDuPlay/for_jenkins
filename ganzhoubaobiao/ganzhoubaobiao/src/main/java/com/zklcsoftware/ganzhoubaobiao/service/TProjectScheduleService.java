package com.zklcsoftware.ganzhoubaobiao.service;

import java.util.List;

import com.zklcsoftware.basic.service.BaseService;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectSchedule;

public interface TProjectScheduleService extends BaseService<TProjectSchedule,Integer> {

	/**
	 * 根据项目阶段id和进度名称查询项目进度
	 * @param tProcedureId
	 * @param name
	 * @return
	 */
	TProjectSchedule findByTProjectStageAndName(Integer tProcedureId, String name);

	List<TProjectSchedule> findByProjectStage(Integer id);

	TProjectSchedule findByProjectStageAndProcedureId(Integer id, Integer selectValue);

	List<TProjectSchedule> findByProcedureId(Integer id);
	
}
