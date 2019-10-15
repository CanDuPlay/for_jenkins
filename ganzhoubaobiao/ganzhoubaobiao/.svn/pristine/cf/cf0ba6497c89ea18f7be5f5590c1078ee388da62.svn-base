package com.zklcsoftware.ganzhoubaobiao.service;

import java.util.List;

import com.zklcsoftware.basic.service.BaseService;
import com.zklcsoftware.ganzhoubaobiao.domain.TDictionary;

public interface TDictionaryService extends BaseService<TDictionary,String> {

	/**
	 * 根据pcode查询字典值
	 * @param pcode
	 * @return
	 */
	List<TDictionary> findByPcode(String pcode);

	/**
	 * 根据区县类别查询区县
	 * @param townType
	 * @return
	 */
	List<TDictionary> findByTownType(String townType);

	TDictionary findByCode(String code);

}
