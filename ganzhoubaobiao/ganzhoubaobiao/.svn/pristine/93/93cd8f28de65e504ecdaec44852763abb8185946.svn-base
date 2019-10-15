package com.zklcsoftware.ganzhoubaobiao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zklcsoftware.basic.service.impl.BaseServiceImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectTownSchedule;
import com.zklcsoftware.ganzhoubaobiao.repository.TProjectTownScheduleRepository;
import com.zklcsoftware.ganzhoubaobiao.service.TProjectTownScheduleService;

@Service
@Transactional
public class TProjectTownScheduleServiceImpl extends BaseServiceImpl<TProjectTownSchedule, Integer> implements TProjectTownScheduleService {

    @Autowired
    private TProjectTownScheduleRepository tProjectTownScheduleRepository;
    
    @Override
    public TProjectTownSchedule findByTownIdAndScheduleIdAndProjectId(String townId, Integer scheduleId, Integer projectId) {
        
        return tProjectTownScheduleRepository.findByTownIdAndScheduleIdAndProjectId(townId, scheduleId, projectId);
    }

	
}
