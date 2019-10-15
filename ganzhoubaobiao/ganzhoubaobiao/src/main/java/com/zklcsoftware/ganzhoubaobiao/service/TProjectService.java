package com.zklcsoftware.ganzhoubaobiao.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zklcsoftware.basic.service.BaseService;
import com.zklcsoftware.ganzhoubaobiao.domain.TProject;
import com.zklcsoftware.ganzhoubaobiao.domain.TTeacher;
import com.zklcsoftware.ganzhoubaobiao.dto.DataDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.TProStageSchedDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.TProjectSchoolDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.TProjectVueDTO;

public interface TProjectService extends BaseService<TProject,Integer> {

    /**
     * 
    * @author lipanlong
    * @Title: findByParams  
    * @Description: 查询市科室项目列表 
    * @param params
    * @param pageable 
    * @param @return    参数  
    * @return Page<TProject>    返回类型  
    * @throws
     */
    Page<TProjectVueDTO> findByParams(HashMap<String, Object> params, Pageable pageable);

    /**
     * 
    * @author lipanlong
    * @Title: findProjectYears  
    * @Description: 查询项目年份 
    * @param @return    参数  
    * @return List<DataDTO>    返回类型  
    * @throws
     */
    List<DataDTO> findProjectYears();

    /**
     * 
    * @author lipanlong
    * @Title: findTotalProjectDetail  
    * @Description: 查询项目下学校信息汇总  
    * @param @param params
    * @param @return    参数  
    * @return TProjectVueDTO    返回类型  
    * @throws
     */
    TProjectVueDTO findTotalProjectDetail(HashMap<String, Object> params);

    /**
    * @author lipanlong
    * @Title: findProjectSchoolList  
    * @Description: 查询区县项目下学校列表  
    * @param @param params
    * @param pageable
    * @param @return    参数  
    * @return Page<TProjectSchoolDTO>    返回类型  
    * @throws
     */
    Page<TProjectSchoolDTO> findProjectSchoolList(HashMap<String, Object> params, Pageable pageable);

    /**
     * 
    * @author lipanlong
    * @Title: findProjectSchedule  
    * @Description: 查询项目阶段和进度 
    * @param @param params
    * @param @return    参数  
    * @return TProStageSchedDTO    返回类型  
    * @throws
     */
    List<TProStageSchedDTO> findProjectSchedule(HashMap<String, Object> params);

	void updateProjectStatus();

	void updateOneProjectStatus(Integer Id);

	/**
	 * 
	 * @param townId
	 * @return
	 */
	List<TTeacher> findByTownId(Integer townId);

	
}
