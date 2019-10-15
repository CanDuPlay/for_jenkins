package com.zklcsoftware.ganzhoubaobiao.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.zklcsoftware.basic.util.DateUtil;
import com.zklcsoftware.common.dto.OperaResult;
import com.zklcsoftware.common.web.ExtBaseController;
import com.zklcsoftware.common.web.util.ConstantUtil;
import com.zklcsoftware.common.web.util.HttpClients;
import com.zklcsoftware.common.web.util.LogUtil;
import com.zklcsoftware.ganzhoubaobiao.domain.MessagePushRange;
import com.zklcsoftware.ganzhoubaobiao.domain.TProcedure;
import com.zklcsoftware.ganzhoubaobiao.domain.TProject;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectSchedule;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectSchool;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectTownSchedule;
import com.zklcsoftware.ganzhoubaobiao.domain.TSchoolFile;
import com.zklcsoftware.ganzhoubaobiao.domain.TSchoolSchedule;
import com.zklcsoftware.ganzhoubaobiao.domain.TTeacher;
import com.zklcsoftware.ganzhoubaobiao.dto.DataDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.TProStageSchedDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.TProjectSchoolDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.TProjectVueDTO;
import com.zklcsoftware.ganzhoubaobiao.service.TProcedureService;
import com.zklcsoftware.ganzhoubaobiao.service.TProjectScheduleService;
import com.zklcsoftware.ganzhoubaobiao.service.TProjectSchoolService;
import com.zklcsoftware.ganzhoubaobiao.service.TProjectService;
import com.zklcsoftware.ganzhoubaobiao.service.TProjectTownScheduleService;
import com.zklcsoftware.ganzhoubaobiao.service.TSchoolFileService;
import com.zklcsoftware.ganzhoubaobiao.service.TSchoolScheduleService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 
* @ClassName: TProjectController  
* @Description: 项目详情  
* @author lipanlong  
* @date 2019年3月20日  
*
 */
@Slf4j
@Controller
@RequestMapping(path={"","/api"})
public class TProjectController extends ExtBaseController {

    @Autowired
    private TProjectService tProjectService;
    @Autowired
    private TProjectTownScheduleService tProjectTownScheduleService;
    @Autowired
    private TSchoolScheduleService tSchoolScheduleService;
    @Autowired
    private TSchoolFileService tSchoolFileService;
    @Autowired
    private TProjectSchoolService tProjectSchoolService;
    @Autowired
    private TProjectScheduleService tProjectScheduleService;
    @Autowired
    private TProcedureService tProcedureService;
    
    @Value("${clientpart.apiPath}")
	private String apiPath; // api服务器路径: 云平台接口
	
	@Value("${sys.messageSecret}")
	private String clientSecret;
    
    /**
     * 
    * @author lipanlong
    * @Title: deleteColumn  
    * @Description: TODO(这里用一句话描述这个方法的作用)  
    * @param @param result
    * @param @return    参数  
    * @return OperaResult    返回类型  
    * @throws
     */
    @ApiOperation(value = "市局科室查询项目列表", notes = "市局科室查询项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/queryProjectList", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult queryProjectList(OperaResult result,@PageableDefault Pageable pageable,
            String year, String status, String projectName, String townId ) {
        log.info("查询市局科室查询项目列表");
        HashMap<String, Object> params = new HashMap<>();
        params.put("year", year);
        params.put("status", status);
        params.put("projectName", projectName);
        params.put("townId", townId);
        
        Page<TProjectVueDTO> page = tProjectService.findByParams(params, pageable);
        List<TProjectVueDTO> content = page.getContent();
        for (int i = 0; i < content.size(); i++) {
            TProjectVueDTO tProjectVueDTO = content.get(i);
            if(tProjectVueDTO.getStatus()== null || tProjectVueDTO.getStatus()==4){
            	tProjectVueDTO.setSwitchValue(false);
            }else{
            	tProjectVueDTO.setSwitchValue(true);
            }
        }
        
        result.getContent().put("page", page);
        
        return result;
    }
    
    @ApiOperation(value = "更新项目申报截止时间", notes = "更新项目申报截止时间")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/changeProjectTime", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult changeProjectTime(OperaResult result, Integer projectId, Long finishTime) {
        log.info("更新项目申报截止时间");
        
        TProject project = tProjectService.findById(projectId);
        project.setFinishTime(new Date(finishTime));
        tProjectService.save(project);
        
        return result;
    }
    
    @ApiOperation(value = "更新项目封存状态", notes = "更新项目封存状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/changeProjectStatus", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult changeProjectStatus(OperaResult result, Integer projectId, Integer status) {
        log.info("更新项目封存状态");
        
        TProject project = tProjectService.findById(projectId);
        if(status==4){
            project.setStatus(status);
        }else{
    		Date startTime = project.getStartTime();
    		Date endTime = project.getEndTime();
    		Date nowTime = new Date();
        	if(project.getStatus() == null){
        		project.setStatus(1);
        	}
        	if(project.getStatus() == 4){
    			if(startTime.getTime()>nowTime.getTime()){
    				project.setStatus(1);
    			}else if(nowTime.getTime()<=endTime.getTime()){
    				project.setStatus(2);
    			}else{
    				project.setStatus(3);
    			}
    		}
        }
        tProjectService.save(project);
        
        return result;
    }
    
    @ApiOperation(value = "查询区县项目下学校列表", notes = "查询区县项目下学校列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/querySchoolList", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult querySchoolList(OperaResult result,@PageableDefault Pageable pageable, 
            Integer projectId, String townId, String year, String schoolName, Integer stage, String shenbao ) {
        log.info("查询区县项目下学校列表");
        
        HashMap<String, Object> params = new HashMap<>();
        params.put("projectId", projectId);
        if(townId==null||townId.equals("")||townId.equals("null")){
            params.put("townId", "");
        }else{
            params.put("townId", townId);
        }
        params.put("schoolName", schoolName);
        if(stage==null||stage.equals("")){
            params.put("stage", "");
        }else{
            params.put("stage", stage);
        }
        
        if(shenbao.equals("")|| shenbao==null){
            params.put("shenbao", "");
        }else{
            params.put("shenbao", shenbao);
        }
        if(year.equals("")||year==null){
            String yearMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
            params.put("year", yearMonth);
        }else if(year.length() == 7 ){
        	params.put("year", year);
        }else{
            long lt = new Long(year);
            String yearMonth = new SimpleDateFormat("yyyy-MM").format(new Date(lt));
            params.put("year", yearMonth);
        }
        
        Page<TProjectSchoolDTO> page = tProjectService.findProjectSchoolList(params,pageable);
        
        for(TProjectSchoolDTO item:page){
        	//查文件
        	List<TSchoolFile> flist = tSchoolFileService.findByTProjectSchoolId(Integer.valueOf(item.getSchoolId()),1);
        	item.setFileList(flist);
        }
        
        result.getContent().put("page", page);
        
        return result;
    }
    
    @ApiOperation(value = "查询项目总体情况", notes = "查询项目总体情况")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/queryTownProject", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult queryTownProject(OperaResult result, Integer projectId, String townId, String schoolId) throws Exception {
        log.info("查询项目总体情况");
        
        HashMap<String, Object> params = new HashMap<>();
        params.put("projectId", projectId);
        if(townId==null||townId.equals("")||townId.equals("null")){
            params.put("townId", "");
        }else{
            params.put("townId", townId);
        }
        
        if(schoolId==null||schoolId.equals("")||schoolId.equals("null")){
            params.put("schoolId", "");
        }else{
            params.put("schoolId", schoolId);
        }
        
        TProjectVueDTO project = tProjectService.findTotalProjectDetail(params);
        
        if(project.getFinishTime()==null){
        	project.setFinshStatus(true);
        }else{
        	Integer endDay = 0;
			String format = DateUtil.toDateStr(project.getFinishTime(), 0);
        	Integer day = Integer.valueOf(format.substring(8, 10));
        	String min = format.substring(11,16);
        	
        	String monthEnd = DateUtil.getMonthEnd(new Date());
        	//获取单月最后一天
        	Integer dayEnd = Integer.valueOf(monthEnd.substring(8));
        	if(day>dayEnd){
        		endDay  = dayEnd;
        	}else{
        		endDay = day;
        	}
        	String monthDay = monthEnd.substring(0, 8)+endDay+" "+min+":00";
        	Date monthDayTime = DateUtil.parseDate(monthDay, 0);
        	
        	long time = monthDayTime.getTime();
            long time2 = new Date().getTime();
            if(time>time2){
            	project.setFinshStatus(true);
            }else{
            	project.setFinshStatus(false);
            }
        }
        
        result.getContent().put("project", project);
        
        return result;
    }
    
    @ApiOperation(value = "学校申报进度", notes = "学校申报进度")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/saveSchoolSchedule", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult saveSchoolSchedule(OperaResult result,Integer id, Integer scheduleId, Integer schoolId, Float invest,
            String reward, String fileStr ) {
        log.info("学校申报进度");
        TSchoolSchedule tSchoolSchedule = null;
        if(id==null){
            tSchoolSchedule = new TSchoolSchedule();
        }else{
            tSchoolSchedule = tSchoolScheduleService.findById(id);
        }
        
        tSchoolSchedule.setInvest(invest);
        tSchoolSchedule.setScheduleId(scheduleId);
        tSchoolSchedule.setSchoolId(schoolId);
        tSchoolSchedule.setReward(reward);
        tSchoolSchedule.setFinishTime(new Date());
        tSchoolSchedule.setStatus(1);
        TSchoolSchedule save = tSchoolScheduleService.save(tSchoolSchedule);
        
        //更新学校最新进度
        TProjectSchool tProjectSchool = tProjectSchoolService.findById(schoolId);
        tProjectSchool.setNowSchedule(scheduleId);
        tProjectSchoolService.save(tProjectSchool);
        
        LogUtil.addLog(0, getUserGuid(),getUName(), "添加"+tProjectSchool.getSchoolName()+"的项目进度");
        
        List<TSchoolFile> fileList = tSchoolFileService.findByTProjectSchoolId(save.getId(), 2);
        for (int i = 0; i < fileList.size(); i++) {
            tSchoolFileService.delete(fileList.get(i));
        }
        
        //更新学校文件
        if(StringUtils.isNotEmpty(fileStr)){
            try {
                JSONArray jsonArray = JSONArray.fromObject(fileStr);
                for (int i = 0; i < jsonArray.size(); i++) {
                    TSchoolFile sf = new TSchoolFile();
                    JSONObject myjObject = jsonArray.getJSONObject(i);
                    String filename = myjObject.getString("fileName");    
                    String fileurl = myjObject.getString("fileUrl");   
                    sf.setFileName(filename);
                    sf.setFileUrl(fileurl);
                    sf.setProjectSchoolId(save.getId());
                    sf.setType(2);
                    tSchoolFileService.save(sf);
                }
            } catch (JSONException  e) {
                log.info(e.getMessage());
            }
        }
        
        return result;
    }
    
    
    @ApiOperation(value = "查询学校申报进度", notes = "查询学校申报进度")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/findSchoolSchedule", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult findSchoolSchedule(OperaResult result,Integer id ) {
        log.info("查询学校申报进度");
        
        TSchoolSchedule tSchoolSchedule = tSchoolScheduleService.findById(id);
        
        List<TSchoolFile> fileList = tSchoolFileService.findByTProjectSchoolId(tSchoolSchedule.getId(), 2);
        
        result.getContent().put("tSchoolSchedule", tSchoolSchedule);
        result.getContent().put("fileList", fileList);
        return result;
    }
    
    
    @ApiOperation(value = "区县保存项目时间", notes = "区县保存项目时间")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/saveTownProjectTime", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult saveTownProjectTime(OperaResult result, String projectTime, String townId) {
        log.info("区县保存项目时间");
        
        JSONArray jsonArray = JSONArray.fromObject(projectTime);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            Integer scheduleId = object.getInt("scheduleId");
            Integer projectId = object.getInt("projectId");

            TProjectTownSchedule townSchedule = tProjectTownScheduleService.findByTownIdAndScheduleIdAndProjectId(townId,scheduleId,projectId);
            if(townSchedule == null){
                townSchedule = new TProjectTownSchedule();
                townSchedule.setTownId(townId);
                townSchedule.setScheduleId(scheduleId);
                townSchedule.setProjectId(projectId);
            }
            
            if(object.getString("dateTime")!=null&&!object.getString("dateTime").equals("null")){
            	JSONArray array = object.getJSONArray("dateTime");
                if(array!=null){
                    String startTime = (String)array.get(0);
                    String endTime = (String)array.get(1);
                    townSchedule.setStartTime(DateUtil.parseDate(startTime, 3));
                    townSchedule.setFinishTime(DateUtil.parseDate(endTime, 3));
                }
            }else{
            	String startTime = object.getString("startTime");
            	String finishTime = object.getString("finishTime");
            	townSchedule.setStartTime(DateUtil.parseDate(startTime, 3));
                townSchedule.setFinishTime(DateUtil.parseDate(finishTime, 3));
            }
            
            tProjectTownScheduleService.save(townSchedule);
        }
        
        return result;
    }
    
    
    @ApiOperation(value = "查询项目下的阶段和进度", notes = "查询项目下的阶段和进度")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/queryProjectSchedule", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult queryProjectSchedule(OperaResult result, String projectId, String townId, String schoolId) {
        log.info("查询项目下的阶段和进度");
        HashMap<String, Object> params = new HashMap<>();
        params.put("projectId", projectId);
        params.put("townId", townId);
        params.put("schoolId", schoolId);
        
        List<TProStageSchedDTO> list = tProjectService.findProjectSchedule(params);
        for (int i = 0; i < list.size(); i++) {
            TProStageSchedDTO tProStageSchedDTO = list.get(i);
            if(tProStageSchedDTO.getStartTime()!=null){
                String[] date = new String[2];
                date[0] = DateUtil.toDateStr(tProStageSchedDTO.getStartTime(), 3);
                date[1] = DateUtil.toDateStr(tProStageSchedDTO.getFinishTime(), 3);
                tProStageSchedDTO.setDateTime(date);
            }
        }
        result.getContent().put("list", list);
        
        return result;
    }
    
    @SuppressWarnings("null")
    @ApiOperation(value = "查询项目时间", notes = "查询项目时间")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/queryProjectYears", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult queryProjectYears(OperaResult result) {
        log.info("查询项目时间");
        List<DataDTO> list = new ArrayList<DataDTO>();
        
        list = tProjectService.findProjectYears();
        
        if(list==null){
            DataDTO data = new DataDTO();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");        
            Date date = new Date();
            String year = sdf.format(date);
            data.setName(year);
            data.setId(year);
            
            list.add(data);
        }
        
        result.getContent().put("list", list);
        return result;
    }
    
    
    @ApiOperation(value = "查询学校申报进度", notes = "查询学校申报进度")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/deleteProject", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult deleteProject(OperaResult result,Integer projectId ) {
        log.info("查询学校申报进度");
        
        List<TProjectSchool> list = tProjectSchoolService.findByProjectId(projectId);
        if(list!=null&&list.size()>0){
        	result.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
        	result.setResultDesc("项目下有学校信息，不能删除！");
        }else{
        	TProject findById = tProjectService.findById(projectId);
        	LogUtil.addLog(1, getUserGuid(),getUName(), "删除"+findById.getName());
        	tProjectService.delete(findById);
        }
        
        return result;
    }
    
    
    @ApiOperation(value = "删除学校", notes = "删除学校")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/deleteSchool", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult deleteSchool(OperaResult result,Integer schoolId ) {
        log.info("删除学校");
        
        TProjectSchool tProjectSchool = tProjectSchoolService.findById(schoolId);
        
        if(tProjectSchool.getNowSchedule()==null){
        	LogUtil.addLog(1, getUserGuid(),getUName(), "删除"+tProjectSchool.getSchoolName());
        	tProjectSchoolService.delete(tProjectSchool);
        }else{
        	result.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
        	result.setResultDesc("该学校下有项目申报信息，不能删除！");
        }
        
        return result;
    }
    
    @ApiOperation(value = "查询区县负责人", notes = "查询区县负责人")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/queryTownLeader", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult queryTownLeader(OperaResult result, Integer townId ) {
        log.info("查询区县负责人");
        
        List<TTeacher> list = tProjectService.findByTownId(townId);
        
        result.getContent().put("teacherList", list);
        
        return result;
    }
    
    
    @ApiOperation(value = "发送短信", notes = "发送短信")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/sendMessage", method = {RequestMethod.POST})
    @ResponseBody
	public void sendMessage(OperaResult result, String remindContent, Integer townId) {
    	log.info("发送短信");
    	List<String> list = new ArrayList<String>();
    	List<TTeacher> teacherlist = tProjectService.findByTownId(townId);
    	for(int i=0;i<teacherlist.size();i++){
    		list.add(teacherlist.get(i).getGlobalId());
    	}
    	
		// 1 只推送 2推送不成功发短信 3-推送兼短信
		// 4-只短信（系统内部人员）5-邮箱 6-短信（发送给系统外部人员）
		String messageUrl = apiPath + "/notify";// 调用平台短信接口地址
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("isOpenShotUrl", "0");
		map.put("secretkey", clientSecret);
		map.put("messageTitle", "赣州报表");
		map.put("messageContent", remindContent);
		MessagePushRange messagePushRange = new MessagePushRange();
		messagePushRange.setUsers(list);
		map.put("messagePushRangeJson", new Gson().toJson(messagePushRange));
		map.put("messagePushType", 4);
		try {
			HttpClients.post(messageUrl, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
    
    @ApiOperation(value = "查询阶段材料说明", notes = "查询阶段材料说明")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/queryProcedure", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult queryProcedure(OperaResult result, Integer scheduleId ) {
        log.info("查询阶段材料说明");
        
        TProjectSchedule tProjectSchedule = tProjectScheduleService.findById(scheduleId);
        
        TProcedure tProcedure = tProcedureService.findById(tProjectSchedule.getProcedureId());
        
        result.getContent().put("tProcedure", tProcedure);
        return result;
    }
    
    /**
     * form表单提交 Date类型数据绑定
     * <功能详细描述>
     * @param binder
     * @see [类、类#方法、类#成员]
     */
    @InitBinder  
    public void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }

}
