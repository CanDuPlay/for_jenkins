package com.zklcsoftware.ganzhoubaobiao.service;

import java.util.List;

import com.zklcsoftware.basic.service.BaseService;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectTown;

public interface TProjectTownService extends BaseService<TProjectTown,Integer> {

	List<TProjectTown> findByProjectId(Integer projectId);

	TProjectTown findByProjectIdAndTownId(Integer id, String valueOf);

}
