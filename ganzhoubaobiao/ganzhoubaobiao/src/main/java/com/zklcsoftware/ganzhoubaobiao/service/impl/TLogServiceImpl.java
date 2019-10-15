package com.zklcsoftware.ganzhoubaobiao.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zklcsoftware.basic.service.impl.BaseServiceImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TLog;
import com.zklcsoftware.ganzhoubaobiao.dto.TProjectVueDTO;
import com.zklcsoftware.ganzhoubaobiao.repository.TLogRepository;
import com.zklcsoftware.ganzhoubaobiao.service.TLogService;

@Service
@Transactional
public class TLogServiceImpl extends BaseServiceImpl<TLog, Integer> implements TLogService {
	@Autowired
    private TLogRepository tLogRepository;
    
    @Override
    public Page<TLog> queryLog(HashMap<String, Object> params, Pageable pageable) {
        
        return tLogRepository.queryLog(params, pageable);
    }
	
}
