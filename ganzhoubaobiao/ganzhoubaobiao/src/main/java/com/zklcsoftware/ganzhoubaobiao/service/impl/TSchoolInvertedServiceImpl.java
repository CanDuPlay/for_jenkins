package com.zklcsoftware.ganzhoubaobiao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zklcsoftware.basic.service.impl.BaseServiceImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TSchoolInverted;
import com.zklcsoftware.ganzhoubaobiao.repository.TSchoolInvertedRepository;
import com.zklcsoftware.ganzhoubaobiao.service.TSchoolInvertedService;

@Service
@Transactional
public class TSchoolInvertedServiceImpl extends BaseServiceImpl<TSchoolInverted, Integer> implements TSchoolInvertedService {

	@Autowired
	private TSchoolInvertedRepository tSchoolInvertedRepository;
	@Override
	public List<TSchoolInverted> findByTProjectSchoolId(Integer tProjectSchoolId){
		return tSchoolInvertedRepository.findByProjectSchoolId(tProjectSchoolId);
	}
}
