package com.zklcsoftware.ganzhoubaobiao.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zklcsoftware.basic.service.BaseService;
import com.zklcsoftware.ganzhoubaobiao.domain.TProcedureType;

public interface TProcedureTypeService extends BaseService<TProcedureType,Integer> {

	Page<TProcedureType> queryProcedureList(Map<String, Object> params, Pageable pageable);

	TProcedureType findByName(String title);
	
}
