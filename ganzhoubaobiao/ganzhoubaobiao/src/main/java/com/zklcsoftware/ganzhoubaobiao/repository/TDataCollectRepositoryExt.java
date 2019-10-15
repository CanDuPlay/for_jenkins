package com.zklcsoftware.ganzhoubaobiao.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zklcsoftware.basic.repository.BaseRepositoryExt;
import com.zklcsoftware.ganzhoubaobiao.domain.TDataCollect;

/**
 * Service Interface:TDataCollect
 * @author administrator
 * @date 2019-3-18
 */
public interface TDataCollectRepositoryExt extends BaseRepositoryExt {
	
	Page<TDataCollect> findDataCollectList(Map<String, Object> params, Pageable pageable);
}
