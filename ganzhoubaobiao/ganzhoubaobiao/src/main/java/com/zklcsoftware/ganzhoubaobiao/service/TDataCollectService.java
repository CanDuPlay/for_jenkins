package com.zklcsoftware.ganzhoubaobiao.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zklcsoftware.basic.service.BaseService;
import com.zklcsoftware.ganzhoubaobiao.domain.TDataCollect;

public interface TDataCollectService extends BaseService<TDataCollect,Integer> {

	Page<TDataCollect> findDataCollectList(Map<String, Object> params, Pageable pageable);

	
}
