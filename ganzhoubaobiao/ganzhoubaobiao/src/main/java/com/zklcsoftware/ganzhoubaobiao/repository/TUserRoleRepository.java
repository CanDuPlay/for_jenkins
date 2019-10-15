package com.zklcsoftware.ganzhoubaobiao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zklcsoftware.basic.repository.BaseDao;
import com.zklcsoftware.ganzhoubaobiao.domain.TUserRole;

/**
 * Service Interface:TUserRole
 * @author administrator
 * @date 2019-3-18
 */
@Repository
public interface TUserRoleRepository extends BaseDao<TUserRole,Integer>, TUserRoleRepositoryExt {

	List<TUserRole> findByRoleId(Integer cityBureau);
	
	TUserRole findByUserId(String userId);

	TUserRole findByUserIdAndRoleId(String userId, Integer cityBureau);

	TUserRole findByUserIdAndRoleIdAndTownId(String userId, Integer roleId, String townId);
	
}
