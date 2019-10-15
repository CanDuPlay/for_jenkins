package com.zklcsoftware.ganzhoubaobiao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zklcsoftware.basic.service.impl.BaseServiceImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TDictionary;
import com.zklcsoftware.ganzhoubaobiao.repository.TDictionaryRepository;
import com.zklcsoftware.ganzhoubaobiao.service.TDictionaryService;

@Service
@Transactional
public class TDictionaryServiceImpl extends BaseServiceImpl<TDictionary, String> implements TDictionaryService {

	@Autowired
	private TDictionaryRepository tDictionaryRepository;
	
	@Override
	public List<TDictionary> findByPcode(String pcode) {
		return tDictionaryRepository.findByPcode(pcode);
	}
	
	@Override
	public TDictionary findByCode(String code) {
		return tDictionaryRepository.findByCode(code);
	}
	
	@Override
	public List<TDictionary> findByTownType(String townType) {
		return tDictionaryRepository.findByTownType(townType);
	}

}
