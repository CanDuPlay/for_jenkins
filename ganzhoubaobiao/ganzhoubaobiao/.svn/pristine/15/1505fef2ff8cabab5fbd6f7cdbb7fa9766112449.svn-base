package com.zklcsoftware.ganzhoubaobiao.repository.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zklcsoftware.basic.repository.BaseRepositoryExtImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TTownSubmit;
import com.zklcsoftware.ganzhoubaobiao.dto.TTownSubmitDTO;
import com.zklcsoftware.ganzhoubaobiao.repository.TTownSubmitRepositoryExt;

public class TTownSubmitRepositoryImpl extends BaseRepositoryExtImpl implements TTownSubmitRepositoryExt {

	@Override
	public Page<TTownSubmit> viewSubmitCondition(Map<String, Object> map, Pageable pageable) {
		return findPage("viewSubmitCondition", "viewSubmitConditionCount", map, pageable, TTownSubmit.class);
	}

	@Override
	public Page<TTownSubmitDTO> querySubmitDataList(Map<String, Object> map, Pageable pageable) {
		return findPage("querySubmitDataList", "querySubmitDataListCount", map, pageable, TTownSubmitDTO.class);
	}
	
}
