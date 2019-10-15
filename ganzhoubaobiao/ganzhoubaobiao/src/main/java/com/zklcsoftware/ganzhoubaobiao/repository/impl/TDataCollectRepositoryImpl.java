package com.zklcsoftware.ganzhoubaobiao.repository.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zklcsoftware.basic.repository.BaseRepositoryExtImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TDataCollect;
import com.zklcsoftware.ganzhoubaobiao.repository.TDataCollectRepositoryExt;

public class TDataCollectRepositoryImpl extends BaseRepositoryExtImpl implements TDataCollectRepositoryExt {

	@Override
	public Page<TDataCollect> findDataCollectList(Map<String, Object> params, Pageable pageable) {
		return findPage("findDataCollectList", "findDataCollectListCount", params, pageable, TDataCollect.class);
	}
	
}
