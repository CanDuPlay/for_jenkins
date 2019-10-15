package com.zklcsoftware.ganzhoubaobiao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zklcsoftware.basic.service.impl.BaseServiceImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TSchoolFile;
import com.zklcsoftware.ganzhoubaobiao.repository.TSchoolFileRepository;
import com.zklcsoftware.ganzhoubaobiao.service.TSchoolFileService;

@Service
@Transactional
public class TSchoolFileServiceImpl extends BaseServiceImpl<TSchoolFile, Integer> implements TSchoolFileService {

	@Autowired
	private TSchoolFileRepository tSchoolFileRepository;
	@Override
	public List<TSchoolFile> findByTProjectSchoolId(Integer tProjectSchoolId,Integer Type){
		return tSchoolFileRepository.findByProjectSchoolIdAndType(tProjectSchoolId,Type);
	}
}
