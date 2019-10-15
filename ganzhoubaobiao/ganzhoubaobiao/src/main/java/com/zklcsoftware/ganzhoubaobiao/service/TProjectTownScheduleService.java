package com.zklcsoftware.ganzhoubaobiao.service;

import com.zklcsoftware.basic.service.BaseService;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectTownSchedule;

public interface TProjectTownScheduleService extends BaseService<TProjectTownSchedule,Integer> {

    /**
     * 
    * @author lipanlong
    * @Title: findByTownIdAndScheduleIdAndProjectId  
    * @Description: 查询区县项目进度时间 
    * @param @param townId
    * @param @param scheduleId
    * @param @param projectId
    * @param @return    参数  
    * @return TProjectTownSchedule    返回类型  
    * @throws
     */
    TProjectTownSchedule findByTownIdAndScheduleIdAndProjectId(String townId, Integer scheduleId, Integer projectId);

	
}
