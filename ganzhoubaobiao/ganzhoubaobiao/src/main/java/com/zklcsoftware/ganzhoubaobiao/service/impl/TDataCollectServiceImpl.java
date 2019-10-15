package com.zklcsoftware.ganzhoubaobiao.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zklcsoftware.basic.service.impl.BaseServiceImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TDataCollect;
import com.zklcsoftware.ganzhoubaobiao.repository.TDataCollectRepository;
import com.zklcsoftware.ganzhoubaobiao.service.TDataCollectService;

@Service
@Transactional
public class TDataCollectServiceImpl extends BaseServiceImpl<TDataCollect, Integer> implements TDataCollectService {

	@Autowired
	private TDataCollectRepository tDataCollectRepository;
	
	@Override
	public Page<TDataCollect> findDataCollectList(Map<String, Object> params, Pageable pageable) {
		return tDataCollectRepository.findDataCollectList(params, pageable);
	}

}
