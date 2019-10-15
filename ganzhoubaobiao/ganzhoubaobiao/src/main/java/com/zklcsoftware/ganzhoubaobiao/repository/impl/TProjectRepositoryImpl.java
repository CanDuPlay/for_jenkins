package com.zklcsoftware.ganzhoubaobiao.repository.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zklcsoftware.basic.repository.BaseRepositoryExtImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TTeacher;
import com.zklcsoftware.ganzhoubaobiao.dto.DataDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.TProStageSchedDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.TProjectSchoolDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.TProjectVueDTO;
import com.zklcsoftware.ganzhoubaobiao.repository.TProjectRepositoryExt;

public class TProjectRepositoryImpl extends BaseRepositoryExtImpl implements TProjectRepositoryExt {

    @Override
    public Page<TProjectVueDTO> findByParams(HashMap<String, Object> params, Pageable pageable) {
        
        return findPage("findProjectByParams", "findProjectByParamsCount", params, pageable, TProjectVueDTO.class);
    }

    @Override
    public List<DataDTO> findProjectYears() {
        HashMap<String, Object> params = new HashMap<>();
        
        return findListObj("findProjectYears", params, DataDTO.class);
    }

    @Override
    public TProjectVueDTO findTotalProjectDetail(HashMap<String, Object> params) {
        
        return findOneObj("findTotalProjectDetail", params, TProjectVueDTO.class);
    }

    @Override
    public Page<TProjectSchoolDTO> findProjectSchoolList(HashMap<String, Object> params, Pageable pageable) {
        
        return findPage("findProjectSchoolList","findProjectSchoolListCount", params, pageable, TProjectSchoolDTO.class);
    }

    @Override
    public List<TProStageSchedDTO> findProjectSchedule(HashMap<String, Object> params) {
        
        return findListObj("findProjectSchedule",params,TProStageSchedDTO.class);
    }

	@Override
	public List<TTeacher> findByTownId(Integer townId) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("townId", townId);
		
		return findListObj("findByTownId", params, TTeacher.class);
	}

	
}
