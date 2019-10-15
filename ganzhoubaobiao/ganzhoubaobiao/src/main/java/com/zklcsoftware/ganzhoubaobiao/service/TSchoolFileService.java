package com.zklcsoftware.ganzhoubaobiao.service;

import java.util.List;

import com.zklcsoftware.basic.service.BaseService;
import com.zklcsoftware.ganzhoubaobiao.domain.TSchoolFile;

public interface TSchoolFileService extends BaseService<TSchoolFile,Integer> {

	List<TSchoolFile> findByTProjectSchoolId(Integer tProjectSchoolId,Integer Type);

	
}
