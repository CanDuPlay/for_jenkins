package com.zklcsoftware.ganzhoubaobiao.repository.impl;

import java.util.HashMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zklcsoftware.basic.repository.BaseRepositoryExtImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TLog;
import com.zklcsoftware.ganzhoubaobiao.repository.TLogRepositoryExt;

public class TLogRepositoryImpl extends BaseRepositoryExtImpl implements TLogRepositoryExt {
	
	@Override
    public Page<TLog> queryLog(HashMap<String, Object> params, Pageable pageable) {
        
        return findPage("queryLog", "queryLogCount", params, pageable, TLog.class);
    }
}
