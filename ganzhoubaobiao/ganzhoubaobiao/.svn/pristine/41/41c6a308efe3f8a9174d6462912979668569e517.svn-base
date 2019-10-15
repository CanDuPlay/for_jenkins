package com.zklcsoftware.ganzhoubaobiao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zklcsoftware.basic.repository.BaseDao;
import com.zklcsoftware.ganzhoubaobiao.domain.TTownSubmit;

/**
 * Service Interface:TTownSubmit
 * @author administrator
 * @date 2019-3-18
 */
@Repository
public interface TTownSubmitRepository extends BaseDao<TTownSubmit,Integer>, TTownSubmitRepositoryExt {

	List<TTownSubmit> findByCollectIdAndIsSubmit(Integer id, Integer isSubmit);
	
	List<TTownSubmit> findByCollectId(Integer id);

	TTownSubmit findByCollectIdAndTownId(Integer id, String townId);
	
}
