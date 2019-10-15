package com.zklcsoftware.ganzhoubaobiao.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zklcsoftware.basic.repository.BaseRepositoryExt;
import com.zklcsoftware.ganzhoubaobiao.domain.TTeacher;
import com.zklcsoftware.ganzhoubaobiao.dto.DataDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.TProStageSchedDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.TProjectSchoolDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.TProjectVueDTO;

/**
 * Service Interface:TProject
 * @author administrator
 * @date 2019-3-18
 */
public interface TProjectRepositoryExt extends BaseRepositoryExt {
	
    /**
     * 
    * @author lipanlong
    * @Title: findByParams  
    * @Description: 查询教科室项目列表  
     */
    Page<TProjectVueDTO> findByParams(HashMap<String, Object> params, Pageable pageable);
    
    /**
     * 
    * @author lipanlong
    * @Title: findProjectYears  
    * @Description: 查询项目年份 
     */
    List<DataDTO> findProjectYears();
    
    /**
     * 
    * @author lipanlong
    * @Title: findTotalProjectDetail  
    * @Description: 查询项目下学校的汇总信息  
     */
    TProjectVueDTO findTotalProjectDetail(HashMap<String, Object> params);
    
    /**
     * 
    * @author lipanlong
    * @Title: findProjectSchoolList  
    * @Description: 查询区县项目学校列表  
     */
    Page<TProjectSchoolDTO> findProjectSchoolList(HashMap<String, Object> params, Pageable pageable);
    
    /**
     * 
    * @author lipanlong
    * @Title: findProjectSchedule  
    * @Description: 查询项目下阶段和进度 
     */
    List<TProStageSchedDTO> findProjectSchedule(HashMap<String, Object> params);
    
    /**
     * 
    * @author lipanlong
    * @Title: findByTownId  
    * @Description: 查询区县负责人
     */
    List<TTeacher> findByTownId(Integer townId);
    
}
