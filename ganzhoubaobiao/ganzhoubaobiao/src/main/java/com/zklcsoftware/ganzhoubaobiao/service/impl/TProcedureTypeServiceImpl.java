package com.zklcsoftware.ganzhoubaobiao.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zklcsoftware.basic.service.impl.BaseServiceImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TProcedureType;
import com.zklcsoftware.ganzhoubaobiao.repository.TProcedureTypeRepository;
import com.zklcsoftware.ganzhoubaobiao.service.TProcedureTypeService;

@Service
@Transactional
public class TProcedureTypeServiceImpl extends BaseServiceImpl<TProcedureType, Integer> implements TProcedureTypeService {

	@Autowired
	private TProcedureTypeRepository tProcedureTypeRepository;
	
	@Override
	public Page<TProcedureType> queryProcedureList(Map<String, Object> params, Pageable pageable) {
		return tProcedureTypeRepository.queryProcedureList(params, pageable);
	}

	@Override
	public TProcedureType findByName(String title) {
		return tProcedureTypeRepository.findByName(title);
	}
	
}
