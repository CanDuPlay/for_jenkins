package com.zklcsoftware.ganzhoubaobiao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zklcsoftware.basic.repository.BaseDao;
import com.zklcsoftware.ganzhoubaobiao.domain.TDictionary;

/**
 * Service Interface:TDictionary
 * @author administrator
 * @date 2019-3-18
 */
@Repository
public interface TDictionaryRepository extends BaseDao<TDictionary,String>, TDictionaryRepositoryExt {

	/**
	 * 根据程序流程类型id查询程序流程
	 * @param procedureId
	 * @return
	 */
	List<TDictionary> findByPcode(String pcode);
	
	/**
	 * 根据程序流程类型id查询程序流程
	 * @param procedureId
	 * @return
	 */
	TDictionary findByCode(String code);
	
	/**
	 * 根据区县类别查询区县
	 * @param townType
	 * @return
	 */
	List<TDictionary> findByTownType(String townType);
	
}
