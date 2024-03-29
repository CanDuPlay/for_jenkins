package com.zklcsoftware.ganzhoubaobiao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zklcsoftware.basic.service.impl.BaseServiceImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TUserRole;
import com.zklcsoftware.ganzhoubaobiao.dto.TUserRoleDTO;
import com.zklcsoftware.ganzhoubaobiao.repository.TUserRoleRepository;
import com.zklcsoftware.ganzhoubaobiao.service.TUserRoleService;

@Service
@Transactional
public class TUserRoleServiceImpl extends BaseServiceImpl<TUserRole, Integer> implements TUserRoleService {

	@Autowired
	private TUserRoleRepository tUserRoleRepository;
	
	@Override
	public List<TUserRole> findByRoleId(Integer cityBureau) {
		return tUserRoleRepository.findByRoleId(cityBureau);
	}
	
	@Override
	public TUserRole findByUserId(String userId) {
		return tUserRoleRepository.findByUserId(userId);
	}
	
	@Override
	public TUserRoleDTO findUserRole(Map<String, Object> params) {
		return tUserRoleRepository.findUserRole(params);
	}

	@Override
	public TUserRole findByUserIdAndRoleId(String userId, Integer cityBureau) {
		return tUserRoleRepository.findByUserIdAndRoleId(userId, cityBureau);
	}

	@Override
	public TUserRole findByUserIdAndRoleIdAndTownId(String userId, Integer roleId, String townId) {
		return tUserRoleRepository.findByUserIdAndRoleIdAndTownId(userId, roleId, townId);
	}

}
