package com.zklcsoftware.ganzhoubaobiao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zklcsoftware.basic.service.impl.BaseServiceImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectSchedule;
import com.zklcsoftware.ganzhoubaobiao.repository.TProjectScheduleRepository;
import com.zklcsoftware.ganzhoubaobiao.service.TProjectScheduleService;

@Service
@Transactional
public class TProjectScheduleServiceImpl extends BaseServiceImpl<TProjectSchedule, Integer> implements TProjectScheduleService {

	@Autowired
	private TProjectScheduleRepository tProjectScheduleRepository;
	
	@Override
	public TProjectSchedule findByTProjectStageAndName(Integer tProcedureId, String name) {
		return tProjectScheduleRepository.findByProjectStageAndName(tProcedureId, name);
	}

	@Override
	public List<TProjectSchedule> findByProjectStage(Integer id) {
		return tProjectScheduleRepository.findByProjectStage(id);
	}

	@Override
	public TProjectSchedule findByProjectStageAndProcedureId(Integer id, Integer selectValue) {
		return tProjectScheduleRepository.findByProjectStageAndProcedureId(id, selectValue);
	}

	@Override
	public List<TProjectSchedule> findByProcedureId(Integer id) {
		return tProjectScheduleRepository.findByProcedureId(id);
	}

}
