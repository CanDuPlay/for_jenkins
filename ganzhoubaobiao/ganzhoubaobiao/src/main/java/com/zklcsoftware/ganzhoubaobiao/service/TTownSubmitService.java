package com.zklcsoftware.ganzhoubaobiao.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zklcsoftware.basic.service.BaseService;
import com.zklcsoftware.ganzhoubaobiao.domain.TTownSubmit;
import com.zklcsoftware.ganzhoubaobiao.dto.TTownSubmitDTO;

public interface TTownSubmitService extends BaseService<TTownSubmit,Integer> {

	List<TTownSubmit> findByCollectIdAndIsSubmit(Integer id, Integer isSubmit);
	
	List<TTownSubmit> findByCollectId(Integer id);

	Page<TTownSubmit> viewSubmitCondition(Map<String, Object> map, Pageable pageable);

	Page<TTownSubmitDTO> querySubmitDataList(Map<String, Object> map, Pageable pageable);

	TTownSubmit findByCollectIdAndTownId(Integer id, String townId);

}
