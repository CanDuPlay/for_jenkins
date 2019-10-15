package com.zklcsoftware.ganzhoubaobiao.repository;

import org.springframework.stereotype.Repository;

import com.zklcsoftware.basic.repository.BaseDao;
import com.zklcsoftware.ganzhoubaobiao.domain.TTeacher;

/**
 * Service Interface:TTeacher
 * @author administrator
 * @date 2018-12-6
 */
@Repository
public interface TTeacherRepository extends BaseDao<TTeacher,Integer>, TTeacherRepositoryExt {

	TTeacher findByGlobalId(String leader);

	TTeacher findByPhoneAndDelFlag(String phone, Integer delFlag);
	
}
