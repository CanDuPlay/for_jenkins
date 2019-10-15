package com.zklcsoftware.ganzhoubaobiao.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zklcsoftware.basic.repository.BaseRepositoryExt;
import com.zklcsoftware.ganzhoubaobiao.domain.TTownSubmit;
import com.zklcsoftware.ganzhoubaobiao.dto.TTownSubmitDTO;

/**
 * Service Interface:TTownSubmit
 * @author administrator
 * @date 2019-3-18
 */
public interface TTownSubmitRepositoryExt extends BaseRepositoryExt {
	
	Page<TTownSubmit> viewSubmitCondition(Map<String, Object> map, Pageable pageable);
	
	Page<TTownSubmitDTO> querySubmitDataList(Map<String, Object> map, Pageable pageable);
}
