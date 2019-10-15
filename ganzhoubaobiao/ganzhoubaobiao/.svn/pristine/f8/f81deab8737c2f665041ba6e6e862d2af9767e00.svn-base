package com.zklcsoftware.ganzhoubaobiao.repository.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zklcsoftware.basic.repository.BaseRepositoryExtImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TProcedureType;
import com.zklcsoftware.ganzhoubaobiao.repository.TProcedureTypeRepositoryExt;

public class TProcedureTypeRepositoryImpl extends BaseRepositoryExtImpl implements TProcedureTypeRepositoryExt {

	@Override
	public Page<TProcedureType> queryProcedureList(Map<String, Object> params, Pageable pageable) {
		return findPage("queryProcedureList", "queryProcedureListCount", params, pageable, TProcedureType.class);
	}
	
}
