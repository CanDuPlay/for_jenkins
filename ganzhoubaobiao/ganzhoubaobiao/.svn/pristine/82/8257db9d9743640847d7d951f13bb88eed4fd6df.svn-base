package com.zklcsoftware.ganzhoubaobiao.repository.impl;

import java.util.Map;

import com.zklcsoftware.basic.repository.BaseRepositoryExtImpl;
import com.zklcsoftware.ganzhoubaobiao.dto.TUserRoleDTO;
import com.zklcsoftware.ganzhoubaobiao.repository.TUserRoleRepositoryExt;

public class TUserRoleRepositoryImpl extends BaseRepositoryExtImpl implements TUserRoleRepositoryExt {

	@Override
	public TUserRoleDTO findUserRole(Map<String, Object> params) {
		
		return findOneObj("findUserRole", params, TUserRoleDTO.class);
	}
}
