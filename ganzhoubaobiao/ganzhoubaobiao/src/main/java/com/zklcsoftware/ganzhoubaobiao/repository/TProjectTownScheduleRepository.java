package com.zklcsoftware.ganzhoubaobiao.repository;

import org.springframework.stereotype.Repository;
import com.zklcsoftware.basic.repository.BaseDao;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectTownSchedule;

/**
 * Service Interface:TProjectTownSchedule
 * @author administrator
 * @date 2019-4-1
 */
@Repository
public interface TProjectTownScheduleRepository extends BaseDao<TProjectTownSchedule,Integer>, TProjectTownScheduleRepositoryExt {

    TProjectTownSchedule findByTownIdAndScheduleIdAndProjectId(String townId, Integer scheduleId, Integer projectId);
	
}
