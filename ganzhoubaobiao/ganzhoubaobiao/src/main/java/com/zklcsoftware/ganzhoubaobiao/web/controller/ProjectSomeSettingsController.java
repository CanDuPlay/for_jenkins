package com.zklcsoftware.ganzhoubaobiao.web.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.zklcsoftware.basic.model.PageWrapper;
import com.zklcsoftware.common.dto.OperaResult;
import com.zklcsoftware.common.web.ExtBaseController;
import com.zklcsoftware.common.web.controller.MyException;
import com.zklcsoftware.common.web.util.ConstantUtil;
import com.zklcsoftware.common.web.util.DataCollectionSumExportUtil;
import com.zklcsoftware.common.web.util.DownUtil;
import com.zklcsoftware.common.web.util.HttpClients;
import com.zklcsoftware.common.web.util.LogUtil;
import com.zklcsoftware.common.web.util.ReadExcel;
import com.zklcsoftware.common.web.util.SXSSFWorkBookUtil;
import com.zklcsoftware.ganzhoubaobiao.domain.MessagePushRange;
import com.zklcsoftware.ganzhoubaobiao.domain.TDataCollect;
import com.zklcsoftware.ganzhoubaobiao.domain.TDictionary;
import com.zklcsoftware.ganzhoubaobiao.domain.TProcedure;
import com.zklcsoftware.ganzhoubaobiao.domain.TProcedureType;
import com.zklcsoftware.ganzhoubaobiao.domain.TProject;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectSchedule;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectSchool;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectStage;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectTown;
import com.zklcsoftware.ganzhoubaobiao.domain.TRole;
import com.zklcsoftware.ganzhoubaobiao.domain.TTeacher;
import com.zklcsoftware.ganzhoubaobiao.domain.TTownSubmit;
import com.zklcsoftware.ganzhoubaobiao.domain.TUserRole;
import com.zklcsoftware.ganzhoubaobiao.dto.PreviewBodyDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.PreviewTableDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.PreviewTdDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.TProcedureVueDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.TProjectCreateDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.TProjectTownDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.TTownSubmitDTO;
import com.zklcsoftware.ganzhoubaobiao.service.TDataCollectService;
import com.zklcsoftware.ganzhoubaobiao.service.TDictionaryService;
import com.zklcsoftware.ganzhoubaobiao.service.TLogService;
import com.zklcsoftware.ganzhoubaobiao.service.TProcedureService;
import com.zklcsoftware.ganzhoubaobiao.service.TProcedureTypeService;
import com.zklcsoftware.ganzhoubaobiao.service.TProjectScheduleService;
import com.zklcsoftware.ganzhoubaobiao.service.TProjectSchoolService;
import com.zklcsoftware.ganzhoubaobiao.service.TProjectService;
import com.zklcsoftware.ganzhoubaobiao.service.TProjectStageService;
import com.zklcsoftware.ganzhoubaobiao.service.TProjectTownService;
import com.zklcsoftware.ganzhoubaobiao.service.TRoleService;
import com.zklcsoftware.ganzhoubaobiao.service.TTeacherService;
import com.zklcsoftware.ganzhoubaobiao.service.TTownSubmitService;
import com.zklcsoftware.ganzhoubaobiao.service.TUserRoleService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;

/**
 * 项目设置控制层
 * @author duzhihui 2019年3月18日14:00:00
 *
 */
@Slf4j
@Controller
@RequestMapping(path={"","/api"})
public class ProjectSomeSettingsController extends ExtBaseController{

	@Value("${clientpart.apiPath}")
    private String webDeskUrl; 
	
	@Value("${sys.messageSecret}")
	private String clientSecret; 
	
	@Value("${uploadfiledir.uploadPhotoFile}")
	public String uploadPhotoFile;// 上传文件路径
	
	@Value("${uploadfiledir.uploadFilePath}")
	public String uploadFilePath;// 上传文件地址
	
	@Autowired
	private TLogService tLogService;
	
	@Autowired
	private TProjectService tProjectService;
	
	@Autowired
	private TProjectStageService tProjectStageService;
	
	@Autowired
	private TProjectSchoolService tProjectSchoolService;

	@Autowired
	private TProjectTownService tProjectTownService;
	
	@Autowired
	private TProjectScheduleService tProjectScheduleService;
	
	@Autowired
	private TDataCollectService tDataCollectService;
	
	@Autowired
	private TTownSubmitService tTownSubmitService;
	
	@Autowired
	private TRoleService tRoleService;
	
	@Autowired
	private TUserRoleService tUserRoleService;
	
	@Autowired
	private TTeacherService tTeacherService;
	
	@Autowired
	private TProcedureService tProcedureService;
	
	@Autowired
	private TDictionaryService tDictionaryService;
	
	@Autowired
	private TProcedureTypeService tProcedureTypeService;
	
	@ApiOperation(value = "市局创建项目", notes = "市局创建项目")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "project", value = "项目对象", required = false, dataType = "Object", paramType = "query"),
	@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/createProject", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult createProject(OperaResult result, String project) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			TProject tProject = gson.fromJson(project, TProject.class);
			TProject newProject = null;
			List<TProjectSchedule> tProjectScheduleList = new ArrayList<>();
			int i = 0;
			if(null == tProject) {
				result.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
				result.setResultDesc("操作失败，请联系管理员！");
				return result;
			}
			
			if(null == tProject.getId()) { //新增操作
				List<TProjectTownDTO> list = tProject.getTownData();
				List<TProjectTown> tProjectTownList = new ArrayList<>();
				newProject = tProjectService.save(tProject);
				tProjectService.updateOneProjectStatus(newProject.getId());
				if(null != list && list.size() > 0) {
					for (TProjectTownDTO dto : list) {
						TProjectTown tProjectTown = new TProjectTown();
						tProjectTown.setTownId(dto.getCode());
						tProjectTown.setProjectId(newProject.getId());
						tProjectTown.setContacts(dto.getLingdao());
						tProjectTown.setAccountabilityUnit(dto.getDanwei());
						tProjectTownList.add(tProjectTown);
					}
					tProjectTownService.save(tProjectTownList);
				}
				
				List<TProjectCreateDTO> projectData = tProject.getProjectData();
				TProjectStage newProjectStage1 = null;
				int sort = 1;
				for (TProjectCreateDTO tProjectCreateDTO : projectData) {
					if(!tProjectCreateDTO.isAdd()) {
						TProjectStage tProjectStage = tProjectStageService.findByProjectIdAndName(newProject.getId(), tProjectCreateDTO.getName());
						if(null == tProjectStage) {
							TProjectStage newProjectStage = new TProjectStage();
							newProjectStage.setProjectId(newProject.getId());
							newProjectStage.setName(tProjectCreateDTO.getName());
							if(!"1".equals(tProjectCreateDTO.getIsDao())) {
								newProjectStage.setIsDaopai(0);
							}else{
								newProjectStage.setIsDaopai(1);
							}
							newProjectStage.setSort(sort);
							sort++;
							newProjectStage1 = tProjectStageService.save(newProjectStage);
						}
						TProjectSchedule newProjectSchedule = new TProjectSchedule();
						newProjectSchedule.setName(tProjectCreateDTO.getPlan());
						newProjectSchedule.setProjectStage(newProjectStage1.getId());
						newProjectSchedule.setProcedureId(!StringUtils.isEmpty(tProjectCreateDTO.getSelectValue()) ? Integer.valueOf(tProjectCreateDTO.getSelectValue()) : null);
						newProjectSchedule.setSortNo(++i);
						tProjectScheduleService.save(newProjectSchedule);
					}
				}
				LogUtil.addLog(0, getUserGuid(), getUName(), "创建项目："+ newProject.getName());
			} else { //更新
				List<TProjectTownDTO> list = tProject.getTownData();
				List<TProjectTown> tProjectTownList = new ArrayList<>();
				newProject = tProjectService.save(tProject);
				tProjectService.updateOneProjectStatus(newProject.getId());
				if(null != list && list.size() > 0) {
					for (TProjectTownDTO dto : list) {
						TProjectTown newProjectTown = tProjectTownService.findByProjectIdAndTownId(newProject.getId(), dto.getCode());
						if(null == newProjectTown) {
							TProjectTown tProjectTown = new TProjectTown();
							tProjectTown.setTownId(dto.getCode());
							tProjectTown.setProjectId(newProject.getId());
							tProjectTown.setContacts(dto.getLingdao());
							tProjectTown.setAccountabilityUnit(dto.getDanwei());
							tProjectTownList.add(tProjectTown);
						}else{
							newProjectTown.setContacts(dto.getLingdao());
							newProjectTown.setAccountabilityUnit(dto.getDanwei());
							tProjectTownList.add(newProjectTown);
						}
					}
					tProjectTownService.save(tProjectTownList);
				}
				
				List<TProjectCreateDTO> projectData = tProject.getProjectData();
				TProjectStage newProjectStage1 = null;
				Integer sort = 0;
				String stId = "";
				for (TProjectCreateDTO tProjectCreateDTO : projectData) {
					if(!tProjectCreateDTO.isAdd()) {
						TProjectStage tProjectStage = tProjectStageService.findById(Integer.valueOf(tProjectCreateDTO.getId()));
						if(!stId.equals(tProjectCreateDTO.getId())){
							sort++;
						}
						if(null == tProjectStage) {
							TProjectStage newProjectStage = new TProjectStage();
							newProjectStage.setProjectId(newProject.getId());
							newProjectStage.setName(tProjectCreateDTO.getName());
							if(!"1".equals(tProjectCreateDTO.getIsDao())) {
								newProjectStage.setIsDaopai(0);
							}else{
								newProjectStage.setIsDaopai(1);
							}
							newProjectStage.setSort(sort);
							newProjectStage1 = tProjectStageService.save(newProjectStage);
						}else{
							tProjectStage.setName(tProjectCreateDTO.getName());
							if(!"1".equals(tProjectCreateDTO.getIsDao())) {
								tProjectStage.setIsDaopai(0);
							}else{
								tProjectStage.setIsDaopai(1);
							}
							tProjectStage.setSort(sort);
							newProjectStage1 = tProjectStageService.save(tProjectStage);
						}
						stId = tProjectCreateDTO.getId();
//						TProjectSchedule projectSchedule = tProjectScheduleService.findByTProjectStageAndName(newProjectStage1.getId(), tProjectCreateDTO.getPlan());
//						if(null == projectSchedule) { // 修改时进度名称修改的
//							projectSchedule = new TProjectSchedule();
//							projectSchedule.setName(tProjectCreateDTO.getPlan());
//							projectSchedule.setProjectStage(newProjectStage1.getId());
//							projectSchedule.setProcedureId(!StringUtils.isEmpty(tProjectCreateDTO.getSelectValue()) ? Integer.valueOf(tProjectCreateDTO.getSelectValue()) : null);
//							projectSchedule.setSortNo(++i);
//							tProjectScheduleService.save(projectSchedule);
//						}
						List<TProjectSchedule> findByProjectStage = tProjectScheduleService.findByProjectStage(newProjectStage1.getId());
						for (TProjectSchedule tProjectSchedule : findByProjectStage) {
								tProjectScheduleService.delete(tProjectSchedule);
						}
						TProjectSchedule newProjectSchedule = new TProjectSchedule();
						newProjectSchedule.setName(tProjectCreateDTO.getPlan());
						newProjectSchedule.setProjectStage(newProjectStage1.getId());
						newProjectSchedule.setProcedureId(!StringUtils.isEmpty(tProjectCreateDTO.getSelectValue()) ? Integer.valueOf(tProjectCreateDTO.getSelectValue()) : null);
						newProjectSchedule.setSortNo(++i);
						tProjectScheduleList.add(newProjectSchedule);
					}
				}
				tProjectScheduleService.save(tProjectScheduleList);
				LogUtil.addLog(1, getUserGuid(), getUName(), "修改项目："+ newProject.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
			result.setResultDesc("操作失败，请联系管理员！");
			return result;
		}
		
	    return result;
	}
	
	@ApiOperation(value = "市局创建项目", notes = "市局创建项目")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "项目id", required = false, dataType = "Integer", paramType = "query"),
	@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/queryProjectById", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult queryProjectById(OperaResult result, Integer id) {
		List<TProjectSchool> list = tProjectSchoolService.findByProjectId(id);
		TProject tProject = tProjectService.findById(id);
		List<TProjectStage> newList = new ArrayList<>();
		List<TProjectStage> tProjectStageList = tProjectStageService.findByProjectId(id);
		List<Integer> procedureIds = new ArrayList<>();
		for (TProjectStage tProjectStage : tProjectStageList) {
			List<TProjectSchedule> tProjectScheduleList  = tProjectScheduleService.findByProjectStage(tProjectStage.getId());
			for (TProjectSchedule tProjectSchedule : tProjectScheduleList) {
				if(null != tProjectSchedule) {
					TProjectStage projectStage = new TProjectStage();
					projectStage.setId(tProjectStage.getId());
					projectStage.setProjectId(tProjectStage.getProjectId());
					projectStage.setName(tProjectStage.getName());
					projectStage.setIsDaopai(tProjectStage.getIsDaopai());
					projectStage.setPlan(tProjectSchedule.getName());
					projectStage.setSelectValue(tProjectSchedule.getProcedureId());
					procedureIds.add(tProjectSchedule.getProcedureId());
					projectStage.setFlowIds(procedureIds);
					newList.add(projectStage);
				}
			}
		}
		result.getContent().put("editFlag", list.size());
		result.getContent().put("tProject", tProject);
		result.getContent().put("tProjectStageList", newList);
		return result;
	}
	
	@ApiOperation(value = "市局创建项目-增加阶段", notes = "市局创建项目-增加阶段")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "tProjectStage", value = "阶段对象", required = false, dataType = "Object", paramType = "query"),
	@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/createProjectStage", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult createProjectStage(OperaResult result, TProjectStage tProjectStage) {
		// 默认都是新建项目 然后建阶段 
		if(null == tProjectStage.getProjectId() || StringUtils.isEmpty(tProjectStage.getName())) {
			result.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
			result.setResultDesc("操作失败，请联系管理员！");
			return result;
		}
		TProjectStage projectStage = tProjectStageService.findByProjectIdAndName(tProjectStage.getProjectId(), tProjectStage.getName());
		if(null == tProjectStage.getId()) { //新增项目阶段
			if(null != projectStage) {
				result.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
				result.setResultDesc("阶段名重复！");
				return result;
			}
		} else { //更新项目阶段
			if(projectStage.getId() != tProjectStage.getId()) {
				if(null != projectStage) {
					result.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
					result.setResultDesc("阶段名重复！");
					return result;
				}
			}
		}
		tProjectStageService.save(tProjectStage);
	    return result;
	}
	
	@ApiOperation(value = "市局创建项目-增加进度", notes = "市局创建项目-增加进度")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "tProjectSchedule", value = "进度对象", required = false, dataType = "Object", paramType = "query"),
	@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/createProjectSchedule", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult createProjectSchedule(OperaResult result, TProjectSchedule tProjectSchedule) {
		if(null == tProjectSchedule.getProjectStage() || StringUtils.isEmpty(tProjectSchedule.getName())) {
			result.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
			result.setResultDesc("操作失败，请联系管理员！");
			return result;
		}
		TProjectSchedule projectSchedule = tProjectScheduleService.findByTProjectStageAndName(tProjectSchedule.getProcedureId(), tProjectSchedule.getName());
		if(null == tProjectSchedule.getId()) { //新增进度
			if(null != projectSchedule) {
				result.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
				result.setResultDesc("进度名重复！");
				return result;
			}
		} else { //更新进度
			if(projectSchedule.getId() != tProjectSchedule.getId()) {
				if(null != projectSchedule) {
					result.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
					result.setResultDesc("进度名重复！");
					return result;
				}
			}
		}
		tProjectScheduleService.save(tProjectSchedule);
	    return result;
	}
	
	@ApiOperation(value = "市局创建项目-删除进度", notes = "市局创建项目-删除进度")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "进度表主键id", required = false, dataType = "Integer", paramType = "query"),
	@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/deleteProjectSchedule", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult deleteProjectSchedule(OperaResult result, Integer id) {
		tProjectScheduleService.delete(id);
	    return result;
	}
	
	@ApiOperation(value = "市局创建项目-排序进度", notes = "市局创建项目-排序进度")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ids", value = "进度表主键ids", required = false, dataType = "String", paramType = "query"),
	@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/sortProjectSchedule", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult sortProjectSchedule(OperaResult result, String ids) {
		List<String> list = Arrays.asList(ids.split(","));
		List<TProjectSchedule> projectScheduleList = new ArrayList<>();
		int i = 1;
		for (String id : list) {
			TProjectSchedule tProjectSchedule = tProjectScheduleService.findById(Integer.valueOf(id));
			tProjectSchedule.setSortNo(i++);
			projectScheduleList.add(tProjectSchedule);
		}
		tProjectScheduleService.save(projectScheduleList);
	    return result;
	}
	
	@ApiOperation(value = "市局创建项目-进度选择程序流程", notes = "市局创建项目-进度选择程序流程")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "scheduleId", value = "进度表主键id", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "procedureId", value = "程序流程主键id", required = false, dataType = "Integer", paramType = "query"),
	@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/saveProjectScheduleProcedure", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult saveProjectScheduleProcedure(OperaResult result, Integer scheduleId, Integer procedureId) {
		TProjectSchedule tProjectSchedule = tProjectScheduleService.findById(scheduleId);
		tProjectSchedule.setProcedureId(procedureId);
		tProjectScheduleService.save(tProjectSchedule);
	    return result;
	}
	
	@ApiOperation(value = "查询区县", notes = "查询区县范围")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/web/queryTownInfo", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult queryTownInfo(OperaResult result, Integer projectId) {
		List<TDictionary> tDictionaryList = tDictionaryService.findByPcode(ConstantUtil.PCODE_100);
		if(null != projectId) {
			List<TProjectTown> tProjectTownList = tProjectTownService.findByProjectId(projectId);
			result.getContent().put("tProjectTownList", tProjectTownList);
		}else{
			result.getContent().put("tProjectTownList", new ArrayList<TProjectTown>());
		}
		result.getContent().put("tDictionaryList", tDictionaryList);
		return result;
	}
	
	@ApiOperation(value = "查询程序流程类型列表", notes = "查询程序流程类型列表")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/web/findProcedureList", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult findProcedureList(OperaResult result) {
		List<TProcedureType> tProcedureTypeList = tProcedureTypeService.findAll();
		result.getContent().put("tProcedureTypeList", tProcedureTypeList);
		return result;
	}
	
	@ApiOperation(value = "查询程序流程类型列表-分页", notes = "查询程序流程类型列表-分页")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "程序流程类型名称", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/web/queryProcedureList", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult queryProcedureList(OperaResult result, String name, @PageableDefault Pageable pageable) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", name);
		Page<TProcedureType> page = tProcedureTypeService.queryProcedureList(params, pageable);
		result.getContent().put("page", new PageWrapper<TProcedureType>(page));
		return result;
	}
	
	@ApiOperation(value = "删除程序流程类型", notes = "删除程序流程类型")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "程序流程类型主键id", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/web/deleteProcedureById", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult deleteProcedureById(OperaResult result, Integer id) {
		try {
			List<TProcedure> procedureList = tProcedureService.findByTProcedureTypeId(id);
			for (TProcedure tProcedure : procedureList) {
				List<TProjectSchedule> list = tProjectScheduleService.findByProcedureId(tProcedure.getId());
				if(!CollectionUtils.isEmpty(list)) {
					result.setResultCode(ConstantUtil.OPT_RESULT_CODE_WARNING);
					result.setResultDesc("操作失败，该程序流程已使用！");
					return result;
				}
			}
			
			tProcedureTypeService.delete(id);
			for (TProcedure tProcedure : procedureList) {
				tProcedureService.delete(tProcedure);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
			result.setResultDesc("操作失败，请联系管理员！");
		}
		return result;
	}
	
	@ApiOperation(value = "创建程序流程", notes = "创建程序流程")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "procedure", value = "程序流程对象", required = false, dataType = "Object", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/web/createProcedure", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult createProcedure(OperaResult result, String procedure, String name, Integer id) {
		TProcedureType tProcedureType = tProcedureTypeService.findByName(name);
		if(null == id && null != tProcedureType) {
			result.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
			result.setResultDesc("项目类型标题已存在，请重新输入！");
			return result;
		}else if(null != tProcedureType && tProcedureType.getId() != id){
			result.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
			result.setResultDesc("项目类型标题已存在，请重新输入！");
			return result;
		}
		List<TProcedure> tProcedureList = new ArrayList<>();
		TProcedureType newProcedureType = new TProcedureType();
		if(null != id) {
			newProcedureType = tProcedureTypeService.findById(id);
		}
		newProcedureType.setName(name);
		newProcedureType.setCreateTime(new Date());
		TProcedureType saveProcedureType = tProcedureTypeService.save(newProcedureType);
		List<TProcedure> tProcedureTypeList = tProcedureService.findByTProcedureTypeId(saveProcedureType.getId());
		for (TProcedure tProcedure : tProcedureTypeList) {
			tProcedureService.delete(tProcedure);
		}
		List<TProcedureVueDTO> procedureVueList = new Gson().fromJson(procedure, new TypeToken<ArrayList<TProcedureVueDTO>>(){}.getType());
		TProcedure parentProcedure = null;
		for (TProcedureVueDTO tProcedureVueDTO : procedureVueList) {
			if(tProcedureVueDTO.isHasChild()) {
				TProcedure tProcedure = new TProcedure();
				tProcedure.setName(tProcedureVueDTO.getProcedure());
				tProcedure.setAliasName(tProcedureVueDTO.getChildProcedure());
				tProcedure.setDepartment(tProcedureVueDTO.getName());
				tProcedure.setMaterial(tProcedureVueDTO.getMaterial());
				tProcedure.setTimeLimit(tProcedureVueDTO.getTimeLimit());
				tProcedure.setCost(tProcedureVueDTO.getCost());
				tProcedure.setRemark(tProcedureVueDTO.getRemarks());
				tProcedure.setSort(tProcedureVueDTO.getSortFlag());
				tProcedure.setPid(null);
				tProcedure.setTProcedureTypeId(saveProcedureType.getId());
				parentProcedure = tProcedureService.save(tProcedure);
			}else if(tProcedureVueDTO.isChild()){
				TProcedure tProcedure1 = new TProcedure();
				tProcedure1.setName(tProcedureVueDTO.getProcedure());
				tProcedure1.setAliasName(tProcedureVueDTO.getChildProcedure());
				tProcedure1.setDepartment(tProcedureVueDTO.getName());
				tProcedure1.setMaterial(tProcedureVueDTO.getMaterial());
				tProcedure1.setTimeLimit(tProcedureVueDTO.getTimeLimit());
				tProcedure1.setCost(tProcedureVueDTO.getCost());
				tProcedure1.setRemark(tProcedureVueDTO.getRemarks());
				tProcedure1.setSort(tProcedureVueDTO.getSortFlag());
				tProcedure1.setPid(parentProcedure.getId());
				tProcedure1.setTProcedureTypeId(saveProcedureType.getId());
				tProcedureService.save(tProcedure1);
			}else{ //单个程序流程情况
				TProcedure tProcedure2 = new TProcedure();
				tProcedure2.setName(tProcedureVueDTO.getProcedure());
				tProcedure2.setAliasName(tProcedureVueDTO.getChildProcedure());
				tProcedure2.setDepartment(tProcedureVueDTO.getName());
				tProcedure2.setMaterial(tProcedureVueDTO.getMaterial());
				tProcedure2.setTimeLimit(tProcedureVueDTO.getTimeLimit());
				tProcedure2.setCost(tProcedureVueDTO.getCost());
				tProcedure2.setRemark(tProcedureVueDTO.getRemarks());
				tProcedure2.setSort(tProcedureVueDTO.getSortFlag());
				tProcedure2.setPid(null);
				tProcedure2.setTProcedureTypeId(saveProcedureType.getId());
				tProcedureList.add(tProcedure2);
			}
			tProcedureService.save(tProcedureList);
		}
		return result;
	}
	
	@ApiOperation(value = "查询程序流程", notes = "查询程序流程")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/queryAllProcedureById", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult queryAllProcedureById(OperaResult result, Integer id) {
		TProcedureType tProcedureType = tProcedureTypeService.findById(id);
		List<TProcedure> procedureList = tProcedureService.findByTProcedureTypeIdOrderByIdAsc(id);
		result.getContent().put("name", tProcedureType.getName()); //程序流程名称
		result.getContent().put("procedureList", procedureList);
		return result;
	}
	
	@ApiOperation(value = "创建程序流程", notes = "创建程序流程")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "id", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/web/exportProcedure", method = {RequestMethod.GET})
	@ResponseBody
	public void exportProcedure(OperaResult result, Integer id, HttpServletRequest request, HttpServletResponse response) {
		TProcedureType tProcedureType = tProcedureTypeService.findById(id);
		List<TProcedure> procedureList = tProcedureService.findByTProcedureTypeIdOrderByIdAsc(id);
		String [] headers = {"办理程序", "办理部门", "需提交的材料", "办理的工作时限（工作日）", "报建费用（每平方米）", "备注"};
		SXSSFWorkBookUtil.exportProcedureExcelInfo(tProcedureType.getName(), headers, procedureList, request, response);
	}
	
	@ApiOperation(value = "帐号权限管理查询所有权限", notes = "帐号权限管理查询所有权限")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/queryAllAuthority", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult queryAllAuthority(OperaResult result) {
		List<TRole> roleList = tRoleService.findAll();
		Collections.sort(roleList, new Comparator<TRole>(){
			/**
             * int compare(TRole p1, TRole p2) 返回一个基本类型的整型，
             * 返回负数表示：p1小于p2，
             * 返回0 表示：p1和p2相等，
             * 返回正数表示：p1大于p2
             */
			@Override
			public int compare(TRole o1, TRole o2) {
				if(o1.getId() > o2.getId()) {
					return 1;
				}
				if(o1.getId() < o2.getId()) {
					return -1;
				}
				return 0;
			}
			
		});
		result.getContent().put("roleList", roleList);
		return result;
	}
	
	@ApiOperation(value = "帐号权限管理查询用户", notes = "帐号权限管理查询用户")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/queryAllTeacher", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult queryAllTeacher(OperaResult result) {
		List<TTeacher> tTeacherList = tTeacherService.findAll();
		result.getContent().put("tTeacherList", tTeacherList);
		return result;
	}
	
	@ApiOperation(value = "帐号权限管理查询所属区县", notes = "帐号权限管理查询所属区县")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/queryAllTown", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult queryAllTown(OperaResult result) {
		List<TDictionary> townList = tDictionaryService.findByPcode(ConstantUtil.PCODE_100);
		result.getContent().put("townList", townList);
		return result;
	}
	
	@ApiOperation(value = "帐号权限管理", notes = "帐号权限管理")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "tUserRole", value = "用户权限对象", required = false, dataType = "Object", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/saveAccountAuthority", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult saveAccountAuthority(OperaResult result, Integer id, String name, Integer roleName, String townName) {
		TUserRole findUserRole = null;
		if(roleName == ConstantUtil.CITY_BUREAU) {
			findUserRole = tUserRoleService.findByUserIdAndRoleId(name, roleName);
		}else{
			findUserRole = tUserRoleService.findByUserIdAndRoleIdAndTownId(name, roleName, townName);
		}
		if(null != id && null != findUserRole && id != findUserRole.getId()) {
			result.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
			result.setResultDesc("账号权限重复，请重新选择！");
			return result;
		}
		if(null == id && null != findUserRole) {
			result.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
			result.setResultDesc("账号权限重复，请重新选择！");
			return result;
		}
		TUserRole tUserRole = null;
		if(null != id) {
			tUserRole = tUserRoleService.findById(id);
			tUserRole.setUserId(name);
			tUserRole.setRoleId(roleName);
			tUserRole.setTownId(townName);
			tUserRole.setCreateTime(new Date());

			TTeacher tTeacher = tTeacherService.findByGlobalId(name);
			TRole role = tRoleService.findById(roleName);
			LogUtil.addLog(1, getUserGuid(),getUName(), "修改角色为："+role.getName() + "(" + tTeacher.getName() + ")");
		}else{
			tUserRole = new TUserRole();
			tUserRole.setUserId(name);
			tUserRole.setRoleId(roleName);
			tUserRole.setTownId(townName);
			tUserRole.setCreateTime(new Date());

			TTeacher tTeacher = tTeacherService.findByGlobalId(name);
			TRole role = tRoleService.findById(roleName);
			LogUtil.addLog(0, getUserGuid(),getUName(), "添加"+role.getName() + "角色" + "(" + tTeacher.getName() + ")");
		}
		tUserRoleService.save(tUserRole);
		return result;
	}
	
	@ApiOperation(value = "帐号权限列表", notes = "帐号权限列表")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/queryAccountAuthorityList", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult queryAccountAuthorityList(OperaResult result, @And({}) Specification<TUserRole> spec, @PageableDefault Pageable pageable) {
		pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize());
		Page<TUserRole> page = tUserRoleService.findPage(spec, pageable);
		result.getContent().put("page", new PageWrapper<TUserRole>(page));
		for (TUserRole tUserRole : page.getContent()) {
//			if(null != tUserRole.getTownId()) {
				TTeacher tTeacher = tTeacherService.findByGlobalId(tUserRole.getUserId());
				if(null == tTeacher) {
					tUserRoleService.delete(tUserRole);
					result.setResultCode(ConstantUtil.OPT_RESULT_CODE_WARNING);
					return result;
				}else{
					tUserRole.setUserName(tTeacher.getName());
				}
				if(StringUtils.isNotBlank(tUserRole.getTownId())){
					TDictionary tDictionary = tDictionaryService.findById(tUserRole.getTownId());
					tUserRole.setTownName(tDictionary.getName());
				}				
				TRole tRole = tRoleService.findById(tUserRole.getRoleId());
				tUserRole.setRoleName(tRole.getName());
//			}
		}
		return result;
	}
	
	@ApiOperation(value = "查询帐号权限管理", notes = "查询帐号权限管理")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "用户权限id", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/queryAccountAuthorityById", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult queryAccountAuthorityById(OperaResult result, Integer id) {
		TUserRole tUserRole = tUserRoleService.findById(id);
		result.getContent().put("tUserRole", tUserRole);
		return result;
	}
	
	@ApiOperation(value = "删除帐号权限管理", notes = "删除帐号权限管理")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "用户权限id", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/deleteAccountAuthority", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult deleteAccountAuthority(OperaResult result, Integer id) {
		TUserRole tUserRole = tUserRoleService.findById(id);
		TTeacher tTeacher = tTeacherService.findByGlobalId(tUserRole.getUserId());
		TRole role = tRoleService.findById(tUserRole.getRoleId());
		LogUtil.addLog(2, getUserGuid(),getUName(), "删除"+role.getName() + "角色" + "(" + tTeacher.getName() + ")");
		
		tUserRoleService.delete(id);
		return result;
	}
	
	@ApiOperation(value = "审核权限设置", notes = "审核权限设置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "用户权限id", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "ifCheck", value = "是否需要审核", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/auditAuthoritySet", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult auditAuthoritySet(OperaResult result, Integer id, Integer ifCheck) {
		TRole tRole = tRoleService.findById(id);
		tRole.setIfCheck(ifCheck);
		tRoleService.save(tRole);
		return result;
	}
	/////*************************数据收集功能***************************************/
	
	@ApiOperation(value = "数据收集查询列表", notes = "数据收集查询列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "title", value = "标题", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "orderBy", value = "排序", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/queryDataCollectList", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult queryDataCollectList(OperaResult result, String title, String orderBy, @PageableDefault Pageable pageable) {
		Map<String, Object> params = new HashMap<>();
		params.put("title", title);
		params.put("orderBy", 0);
		Page<TDataCollect> page = tDataCollectService.findDataCollectList(params, pageable);
		for (TDataCollect tDataCollect : page.getContent()) {
			// 查询已提交的区县
			List<TTownSubmit> tTownSubmitList = tTownSubmitService.findByCollectIdAndIsSubmit(tDataCollect.getId(), ConstantUtil.IS_SUBMIT_1);
			// 查询总的区县
			List<TTownSubmit> sumSubmitList = tTownSubmitService.findByCollectId(tDataCollect.getId());
			tDataCollect.setReferNumber(CollectionUtils.isEmpty(tTownSubmitList) ? 0 : tTownSubmitList.size());
			tDataCollect.setSumNumber(CollectionUtils.isEmpty(sumSubmitList) ? 0 : sumSubmitList.size());
		}
		result.getContent().put("page", new PageWrapper<TDataCollect>(page));
		return result;
	}
	
	@ApiOperation(value = "数据收集-根据id查询", notes = "数据收集-根据id查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "id", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/findDataCollectById", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult findDataCollectById(OperaResult result, Integer id) {
		TDataCollect tDataCollect = tDataCollectService.findById(id);
		result.getContent().put("tDataCollect", tDataCollect);
		return result;
	}
	
	@ApiOperation(value = "数据收集-删除", notes = "数据收集-删除")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "id", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/deleteDataCollect", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult deleteDataCollect(OperaResult result, Integer id) {
		tDataCollectService.delete(id);
		return result;
	}
	
	@ApiOperation(value = "数据收集-新增/修改", notes = "数据收集-新增/修改")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "tDataCollect", value = "tDataCollect", required = false, dataType = "Object", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/saveDataCollect", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult saveDataCollect(OperaResult result, TDataCollect tDataCollect) {
		tDataCollect.setCreateTime(new Date());
		TDataCollect newDataCollect = tDataCollectService.save(tDataCollect);
		
		List<TTownSubmit> townSubmitList = new ArrayList<>();
		//给所有区县接收人发
		List<TTownSubmit> tTownSubmitList = tTownSubmitService.findByCollectId(newDataCollect.getId());
		if(CollectionUtils.isEmpty(tTownSubmitList)) {
			List<TDictionary> townList = tDictionaryService.findByPcode(ConstantUtil.PCODE_100);
			for (TDictionary town : townList) {
				TTownSubmit tTownSubmit = new TTownSubmit();
				tTownSubmit.setIsSubmit(ConstantUtil.IS_SUBMIT_0);
				tTownSubmit.setTownId(town.getCode());
				tTownSubmit.setCollectId(newDataCollect.getId());
				townSubmitList.add(tTownSubmit);
			}
			tTownSubmitService.save(townSubmitList);
		}else{
			if(tDataCollect.getDelFlag()) { //市校建办删除过文件
				for (TTownSubmit tTownSubmit : tTownSubmitList) {
					tTownSubmit.setIsSubmit(ConstantUtil.IS_SUBMIT_0);
					tTownSubmit.setFileUrl(null);
					townSubmitList.add(tTownSubmit);
				}
				tTownSubmitService.save(townSubmitList);
			}
		}
		return result;
	}
	
	@ApiOperation(value = "数据收集-查看提交情况", notes = "数据收集-查看提交情况")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "params", value = "params", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "status", value = "提交状态", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "orderBy", value = "提交日期排序", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/viewSubmitCondition", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult viewSubmitCondition(OperaResult result, Integer id, String params, Integer status, Integer orderBy, @PageableDefault Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("params", params);
		if(null != status && status == 2) {
			status = null;
		}
		map.put("status", status);
		map.put("orderBy", orderBy);
		Page<TTownSubmit> page = tTownSubmitService.viewSubmitCondition(map, pageable);
		result.getContent().put("page", new PageWrapper<TTownSubmit>(page));
		return result;
	}
	
	@ApiOperation(value = "数据收集-导出汇总数据", notes = "数据收集-导出汇总数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "id", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/exportSummaryData", method = {RequestMethod.GET})
	@ResponseBody
	public void exportSummaryData(Integer id, HttpServletResponse response, HttpServletRequest request) throws MyException {
		TDataCollect tDataCollect = tDataCollectService.findById(id);
		String filePath = uploadPhotoFile + File.separator + tDataCollect.getFileUrl();
		Workbook wb = DataCollectionSumExportUtil.dealDataFileUrl(filePath);
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(1);
		int totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		Map<Integer, Object> map = new HashMap<>();
		for(int c = 0; c < totalCells; c++){
			Cell cell = row.getCell(c);
			if(null != cell) {
				map.put(c, DataCollectionSumExportUtil.formatNumber(String.valueOf(cell.getNumericCellValue())));
			}else{
				map.put(c, "");
			}
		}
		//查询已提交的区县数据
		List<TTownSubmit> submitList = tTownSubmitService.findByCollectIdAndIsSubmit(id, ConstantUtil.IS_SUBMIT_1);
		for (TTownSubmit tTownSubmit : submitList) {
			TDictionary findByCode = tDictionaryService.findByCode(tTownSubmit.getTownId());
			tTownSubmit.setTownName(findByCode.getName());
		}
		DataCollectionSumExportUtil.exportDataCollectSummaryData(map, uploadPhotoFile, submitList, response, request);
	}
	
	
	@ApiOperation(value = "区县校建办数据提交-查询列表", notes = "区县校建办数据提交-查询列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "title", value = "标题", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "orderByRelease", value = "发布日期排序", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "orderBy", value = "提交日期排序", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/querySubmitDataList", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult querySubmitDataList(OperaResult result, String title, Integer orderByRelease, Integer orderBy, @PageableDefault Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		map.put("title", title);
		map.put("orderByRelease", 0); //发布日期排序
		map.put("orderBy", orderBy); //提交日期排序
		map.put("userId", getUserGuid());
		Page<TTownSubmitDTO> page = tTownSubmitService.querySubmitDataList(map, pageable);
		result.getContent().put("page", new PageWrapper<TTownSubmitDTO>(page));
		return result;
	}
	
	@ApiOperation(value = "数据收集-预览", notes = "数据收集-预览")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "id", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/viewDataCollect", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult viewDataCollect(OperaResult result, Integer id, String showFirst) {
		TUserRole tUserRole = tUserRoleService.findByUserId(getUserGuid());
		String townId = tUserRole.getTownId();
		TTownSubmit tTownSubmit = null;
		String fileUrl = null;
		
		TDataCollect tDataCollect = tDataCollectService.findById(id);
		fileUrl = tDataCollect.getFileUrl();
		// 提交过显示提交过的数据 否则显示市局上传的文件
		if(!StringUtils.isEmpty(townId)) {
			tTownSubmit = tTownSubmitService.findByCollectIdAndTownId(id, townId);
			if(!StringUtils.isEmpty(tTownSubmit.getFileUrl())) {
				fileUrl = tTownSubmit.getFileUrl();
			}
		}
		if(!StringUtils.isEmpty(fileUrl)) {
			ReadExcel readExcel = new ReadExcel();
			PreviewTableDTO excelInfo = new PreviewTableDTO();
			try {
				excelInfo = readExcel.getExcelInfo(uploadPhotoFile + fileUrl, showFirst);
			} catch (MyException e) {
				e.printStackTrace();
			}
			result.getContent().put("excelInfo", excelInfo);
			result.getContent().put("totalCells", excelInfo.getSumColums());
		}
		result.getContent().put("tDataCollect", tDataCollect);
		return result;
	}
	
	@ApiOperation(value = "数据收集-查看详情", notes = "数据收集-查看详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "id", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "fileUrl", value = "fileUrl", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/viewDataCollectAdmin", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult viewDataCollectAdmin(OperaResult result, Integer id, String fileUrl, String showFirst) {
		TDataCollect tDataCollect = tDataCollectService.findById(id);
		if(!StringUtils.isEmpty(fileUrl)) {
			ReadExcel readExcel = new ReadExcel();
			PreviewTableDTO excelInfo = new PreviewTableDTO();
			try {
				// showFirst = 0 不显示表头
				excelInfo = readExcel.getExcelInfo(uploadPhotoFile + fileUrl, showFirst);
			} catch (MyException e) {
				e.printStackTrace();
			}
			result.getContent().put("excelInfo", excelInfo);
			result.getContent().put("totalCells", excelInfo.getSumColums());
		}
		result.getContent().put("tDataCollect", tDataCollect);
		return result;
	}
	
	@ApiOperation(value = "数据收集-保存提交数据", notes = "数据收集-保存提交数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "header", value = "header", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "body", value = "body", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/saveDataCollectData", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult saveDataCollectData(OperaResult result, Integer id, String header, String body, HttpServletRequest request, HttpServletResponse response) {
		List<PreviewTdDTO> headerList = new Gson().fromJson(header, new TypeToken<ArrayList<PreviewTdDTO>>(){}.getType());
		List<PreviewBodyDTO> bodyList = new Gson().fromJson(body, new TypeToken<List<PreviewBodyDTO>>(){}.getType());
		SXSSFWorkBookUtil.exportExcelInfo(id ,headerList, uploadPhotoFile, getUserGuid(), bodyList, response, request);
		return result;
	}
	
	@ApiOperation(value = "数据收集-下载", notes = "数据收集-下载")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/downDataCollectData", method = {RequestMethod.GET})
	@ResponseBody
	public void downDataCollectData(OperaResult result, Integer id, String fileUrl, HttpServletRequest request, HttpServletResponse response) {
		String path = uploadPhotoFile + fileUrl;
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = format.format(date);
		fileName = getUName() + "提交数据" + fileName + ".xls";
        DownUtil.downloadCollect( request, response, fileName, path);
	}
	
	@ApiOperation(value = "数据收集-提醒提交", notes = "数据收集-提醒提交")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/remindSubmit", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult remindSubmit(OperaResult result, Integer id, String phone, HttpServletRequest request, HttpServletResponse response) {
		if(StringUtils.isEmpty(phone)) {
			result.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
			result.setResultDesc("该用户没有手机信息，发送失败！");
			return result;
		}
		try {
			// 1 只推送 2推送不成功发短信 3-推送兼短信
			// 4-只短信（系统内部人员）5-邮箱 6-短信（发送给系统外部人员）
			Integer messagePushType = null;
			TDataCollect tDataCollect = tDataCollectService.findById(id);
			if(tDataCollect.getRemindWay().contains("1") && !tDataCollect.getRemindWay().contains("2")) {
				messagePushType = 1;
			}else if(tDataCollect.getRemindWay().contains("1") && tDataCollect.getRemindWay().contains("2")) {
				messagePushType = ConstantUtil.MESSAGE_PUSH_TYPE_3;
			}else{
				messagePushType = 4;
			}
			String content = tDataCollect.getRemindContent();
			String messageUrl = webDeskUrl + "/notify";// 调用平台短信接口地址
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("isOpenShotUrl", "0");
			map.put("secretkey", clientSecret);
			map.put("messageTitle", "数据提交提醒");
			map.put("messageContent", content);
			
			
			TTeacher tTeacher = tTeacherService.findByPhoneAndDelFlag(phone, ConstantUtil.IF_CHECK_1);
			List<String> list = new ArrayList<String>();
			if(null != tTeacher) {
				list.add(tTeacher.getGlobalId());
			}
			MessagePushRange messagePushRange = new MessagePushRange();
			messagePushRange.setUsers(list);
			map.put("messagePushRangeJson", new Gson().toJson(messagePushRange));
			map.put("messagePushType", messagePushType);
			String message = HttpClients.post(messageUrl, map);
			Gson gson = new Gson();
			OperaResult fromJson = gson.fromJson(message, OperaResult.class);
			String townId = null;
			if(null != tTeacher) {
				TUserRole tUserRole = tUserRoleService.findByUserId(tTeacher.getGlobalId());
				townId = tUserRole.getTownId();
			}
			if(null != townId) {
				TTownSubmit tTownSubmit = tTownSubmitService.findByCollectIdAndTownId(id, townId);
				tTownSubmit.setRemindCount(null == tTownSubmit.getRemindCount() ? 1 : tTownSubmit.getRemindCount() + 1);
				tTownSubmitService.save(tTownSubmit);
			}
			if("0".equals(fromJson.getResultCode())){
				log.info("发送短信成功{%s}",phone);
			}else{
				log.error("发送短信失败{%s}",phone);
			}
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@ApiOperation(value = "市局科室上传方法", notes = "市局科室上传方法")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "access_token") })
	@RequestMapping(value = "/admin/commonUpload", method = { RequestMethod.POST })
	@ResponseBody
	public OperaResult commonUpload(OperaResult result, MultipartFile file) throws IOException {

		String root = uploadPhotoFile;
		String filename = file.getOriginalFilename();
		ArrayList<String> types = new ArrayList<String>();// 设置上传文件类型
		types.add("XLS");
		types.add("XLSX");
		String fileType = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
		if (!types.contains(fileType.toUpperCase())) {
			result.getContent().put("fileInfo", "nottype");
			return result;
		}
		String tempname = new Date().getTime() + "." + fileType;
		// 基于myFile创建一个文件输入流
		InputStream is = file.getInputStream();
		boolean isExcel2003 = true; 
        if(ReadExcel.isExcel2007(filename)){
            isExcel2003 = false;  
        }
        boolean excelInfo = checkExcelInfo(file.getInputStream(), isExcel2003);
        if(!excelInfo) {
        	result.getContent().put("fileInfo", "noTemplate");
			return result;
        }
		if (is.available() > 1 * 1024 * (30 * 1024)) {
			if (is != null)
				is.close();
			result.getContent().put("fileInfo", "toobig");
			return result;
		}
		/*
		 * BufferedImage sourceImg =ImageIO.read(file.getInputStream());
		 * if(sourceImg.getWidth() < 512){ result.getContent().put("fileInfo",
		 * "nosize"); return result; }
		 */
		File toFile = new File(root, tempname);
		if (!toFile.getParentFile().exists()) {
			toFile.getParentFile().mkdirs();
		}
		// 创建一个输出流
		OutputStream os = new FileOutputStream(toFile);
		// 设置缓存
		byte[] buffer = new byte[1024];
		int length = 0;
		while ((length = is.read(buffer)) != -1) {
			os.write(buffer, 0, length);
		}
		// 关闭输入流
		is.close();
		os.close();
		result.getContent().put("fileName", filename);
		result.getContent().put("fileInfo", tempname);
		return result;
	}
	
	private boolean checkExcelInfo(InputStream is, boolean isExcel2003) {
		try{
    	   byte[] byteArray = org.apache.commons.io.IOUtils.toByteArray(is);
           ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
           /** 根据版本选择创建Workbook的方式 */
           Workbook wb = null;
           //当excel是2003时
           if(isExcel2003){
               wb = new HSSFWorkbook(byteArrayInputStream); 
           }
           else{//当excel是2007时
               wb = new XSSFWorkbook(byteArrayInputStream); 
           }
           //读取Excel里面客户的信息
           //得到第一个shell  
           Sheet sheet = wb.getSheetAt(0);
           //得到Excel的行数
           int totalRows = sheet.getPhysicalNumberOfRows();
           if(totalRows < 1) {
        	   return false;
//        	   throw new MyException("模板不符合要求，请重新上传！");
           }
           Row row = sheet.getRow(2);
           int totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
           for(int c = 0; c < totalCells; c++){
        	   Cell cell = row.getCell(c);
        	   if(!StringUtils.isEmpty(cell.toString())) {
        		   return false;
        	   }
           }
         }catch (IOException e)  {  
           e.printStackTrace();  
         }
		return true;
	}
	
	/////*************************管理员功能***************************************/
	
	@ApiOperation(value = "查询科室用户树", notes = "查询科室用户树")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/queryAccountTree", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult queryAccountTree(OperaResult result) {
		
		return result;
	}
	
	@ApiOperation(value = "查询市局科室账号列表", notes = "查询市局科室账号列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/queryAccountList", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult queryAccountList(OperaResult result) {
		List<TUserRole> userRoleList = tUserRoleService.findByRoleId(ConstantUtil.CITY_BUREAU);
		for (TUserRole tUserRole : userRoleList) {
			TTeacher tTeacher = tTeacherService.findByGlobalId(tUserRole.getUserId());
			if(null == tTeacher) {
				tUserRoleService.delete(tUserRole);
				result.setResultCode(ConstantUtil.OPT_RESULT_CODE_WARNING);
				return result;
			}else{
				tUserRole.setUserName(tTeacher.getName());
			}
		}
		result.getContent().put("userRoleList", userRoleList);
		return result;
	}
	
	@ApiOperation(value = "市局科室账号分配", notes = "市局科室账号分配")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/accountAssignment", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult accountAssignment(OperaResult result, String userId) {
		TUserRole findUserRole = tUserRoleService.findByUserId(userId);
		if(null != findUserRole) {
			result.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
			result.setResultDesc("账号权限重复，请重新选择！");
			return result;
		}
		TUserRole tUserRole = new TUserRole();
		tUserRole.setUserId(userId);
		tUserRole.setRoleId(ConstantUtil.CITY_BUREAU);
		tUserRole.setCreateTime(new Date());
		tUserRoleService.save(tUserRole);
		TTeacher tTeacher = tTeacherService.findByGlobalId(userId);
		LogUtil.addLog(0, getUserGuid(),getUName(), "添加市级科室角色" + "(" + tTeacher.getName() + ")");
		return result;
	}
	
	@ApiOperation(value = "删除市局科室账号分配", notes = "删除市局科室账号分配")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "用户权限表主键id", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/admin/deleteAccountAssignment", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult deleteAccountAssignment(OperaResult result, Integer id) {
		try {
			TUserRole tUserRole = tUserRoleService.findById(id);
			String userId = tUserRole.getUserId();
			TTeacher tTeacher = tTeacherService.findByGlobalId(userId);
			tUserRoleService.delete(id);
			LogUtil.addLog(2, getUserGuid(), getUName(),"删除市级科室角色" + "(" + tTeacher.getName() + ")");
		} catch (Exception e) {
			log.equals("delete shijukeshi crash");
			e.printStackTrace();
		}
		return result;
	}
	
}
