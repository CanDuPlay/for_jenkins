package com.zklcsoftware.ganzhoubaobiao.web.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zklcsoftware.basic.util.DateUtil;
import com.zklcsoftware.common.dto.OperaResult;
import com.zklcsoftware.common.web.ExtBaseController;
import com.zklcsoftware.common.web.util.ConstantUtil;
import com.zklcsoftware.common.web.util.LogUtil;
import com.zklcsoftware.ganzhoubaobiao.domain.TDictionary;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectSchool;
import com.zklcsoftware.ganzhoubaobiao.domain.TProjectStage;
import com.zklcsoftware.ganzhoubaobiao.domain.TSchoolFile;
import com.zklcsoftware.ganzhoubaobiao.domain.TSchoolInverted;
import com.zklcsoftware.ganzhoubaobiao.domain.TSchoolSchedule;
import com.zklcsoftware.ganzhoubaobiao.domain.TYearSort;
import com.zklcsoftware.ganzhoubaobiao.dto.DataDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.MonthContrastCountDto;
import com.zklcsoftware.ganzhoubaobiao.dto.SchoolScheduleListDto;
import com.zklcsoftware.ganzhoubaobiao.dto.YearDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.checkListDto;
import com.zklcsoftware.ganzhoubaobiao.dto.scheduleListDto;
import com.zklcsoftware.ganzhoubaobiao.dto.yearSortListDto;
import com.zklcsoftware.ganzhoubaobiao.service.TDictionaryService;
import com.zklcsoftware.ganzhoubaobiao.service.TProjectSchoolService;
import com.zklcsoftware.ganzhoubaobiao.service.TProjectStageService;
import com.zklcsoftware.ganzhoubaobiao.service.TSchoolFileService;
import com.zklcsoftware.ganzhoubaobiao.service.TSchoolInvertedService;
import com.zklcsoftware.ganzhoubaobiao.service.TSchoolScheduleService;
import com.zklcsoftware.ganzhoubaobiao.service.TYearSortService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * Controller of TDataCollect
 * @author administrator
 * @date 2019-3-18
 */
@Slf4j
@Controller
@RequestMapping(path={"","/api"})
public class TCkeckCollectController extends ExtBaseController {
	
	@Autowired
	private TSchoolScheduleService tSchoolScheduleService;
	@Autowired
	private TSchoolInvertedService tSchoolInvertedService;
	@Autowired
	private TSchoolFileService tSchoolFileService;
	@Autowired
	private TDictionaryService tDictionaryService;
	@Autowired
	private TProjectSchoolService tProjectSchoolService;
	@Autowired
	private TYearSortService tYearSortService;
	@Autowired
	private TProjectStageService tProjectStageService;
	
	@ApiOperation(value = "市级数据看板列表（查询所有学校的状态）", notes = "市级数据看板列表（查询所有学校的状态）")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "projectId", value = "项目id", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "month", value = "月份", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "checkStatus", value = "状态", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "town_id", value = "区县id", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "stage", value = "阶段", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "status", value = "当前应该看到的状态的学校", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "level", value = "1区级2市级", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/kanbanList", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult kanbanList(OperaResult result,String projectId,String month,String status,String town_id,String stage,String checkStatus,String level,String heshi,String checked) {
        Map<String,Object> params = new HashMap<>();
        params.put("projectId", projectId);
    	
    	if(StringUtils.isEmpty(month)){
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");        
            Date date = new Date();
            month = sdf.format(date);
            result.getContent().put("month", month);
    	}
    	params.put("month", month);
    	
        params.put("month", month);
        params.put("town_id", town_id);
        params.put("status", status);
        params.put("stage", stage);
        params.put("checkStatus", checkStatus);
        params.put("heshi", heshi);
        params.put("ifnow", checked);
        List<checkListDto> list = new ArrayList<>();
        boolean iscolor = false;
        
        //如果是市级
        if(level.equals("1")){
        	List<checkListDto> shijiList = tSchoolScheduleService.checkShijiList(params,checked);
        	//过程的个数
        	List<scheduleListDto> slist = tSchoolScheduleService.quxianScheduleList(params);
        	Integer jieduan = -1;
        	int colorNum = 0;
        	String color = "";
        	for(scheduleListDto sc:slist){
        		if(jieduan == sc.getStage()){
        			sc.setColor(color);
        		}else{
        			color = ConstantUtil.colors[colorNum%ConstantUtil.colors.length];
        			sc.setColor(color);
        			colorNum++;
        			jieduan = sc.getStage();
        		}
        	}
        	iscolor = true;
        	shijiList.get(0).setScheduleList(slist);
            list.addAll(shijiList);
        }
        //查询区级数据
        List<checkListDto> qujiList = tSchoolScheduleService.checkQujiList(params,checked);
        for(checkListDto item:qujiList){
        	list.add(item);
            params.put("townId", item.getTownId());
            //过程的个数
            List<scheduleListDto> slist = tSchoolScheduleService.quxianScheduleList(params);
            //如果是区级并且没设置颜色，则需要颜色 
            if(level.equals("2") && !iscolor){
            	Integer jieduan = -1;
            	int colorNum = 0;
            	String color = "";
                for(scheduleListDto sc:slist){
            		if(jieduan == sc.getStage()){
            			sc.setColor(color);
            		}else{
            			color = ConstantUtil.colors[colorNum%ConstantUtil.colors.length];
            			sc.setColor(color);
            			colorNum++;
            		}
            	}
                iscolor = true;
            }
            item.setScheduleList(slist);
        	//查询区级下面的学校
            List<checkListDto> xuexiaoList = tSchoolScheduleService.checkXuexiaoList(params,checked);
        	int sort = 1;
            for(checkListDto xuexiao:xuexiaoList){
            	xuexiao.setSort(sort);
            	sort++;
                //查询倒排时间
            	List<TSchoolInverted> ilist = tSchoolInvertedService.findByTProjectSchoolId(xuexiao.getId());
            	xuexiao.setInvertedList(ilist);
            	//查文件
            	List<TSchoolFile> flist = tSchoolFileService.findByTProjectSchoolId(xuexiao.getScheduleId(),2);
            	xuexiao.setFileList(flist);
            }
            list.addAll(xuexiaoList);
        }
        
        result.getContent().put("list", list);
        return result;
    }
	

	
	@ApiOperation(value = "市级数据看板列表统计信息", notes = "市级数据看板列表统计信息")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "projectId", value = "项目id", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "month", value = "月份", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "checkStatus", value = "状态", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "town_id", value = "区县id", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "stage", value = "阶段", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "status", value = "当前应该看到的状态的学校", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "level", value = "1区级2市级", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/kanbanCount", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult kanbanCount(OperaResult result,String projectId,String month,String status,String town_id,String stage,String checkStatus,String level) {
        Map<String,Object> params = new HashMap<>();
        params.put("projectId", projectId);
        params.put("month", month);
        params.put("town_id", town_id);
        params.put("status", status);
        params.put("stage", stage);
        params.put("checkStatus", checkStatus);
        List<scheduleListDto> list = tSchoolScheduleService.kanbanCount(params);
        result.getContent().put("list", list);
        return result;
    }
	
	@ApiOperation(value = "审核信息", notes = "审核信息")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "checkType(3按学校审核，2按区审核，1全部)", value = "checkType", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "status", value = "用户审核后的状态", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "lastStatus", value = "前一级审核状态", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "projectId", value = "项目id", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/checkStatus", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult checkStatus(OperaResult result,String id,String checkType,String lastStatus,Integer status,String projectId,String month) {
        Map<String,Object> params = new HashMap<>();
        String content = "";
        if(checkType.equals("3")){
        	params.put("schoolId", id);
        	TProjectSchool school = tProjectSchoolService.findById(Integer.valueOf(id));
        	content = school.getSchoolName();
        }else if(checkType.equals("2")){
        	params.put("townId", id);
        	TDictionary town = tDictionaryService.findByCode(id);
        	content = town.getName();
        }else{
        	content="全市";
        }
    	params.put("status", lastStatus);
    	params.put("projectId", projectId);
    	params.put("userStatus", status);
    	params.put("month", month);
        
        List<TSchoolSchedule> scheduleList = tSchoolScheduleService.checkStatusList(params);

		for(TSchoolSchedule item:scheduleList){
        	item.setStatus(status);
        	tSchoolScheduleService.save(item);
        }

		LogUtil.addLog(1, getUserGuid(),getUName(), "审核"+content+"进度数据");
        return result;
    }
	
	@ApiOperation(value = "审核页面列表（查询当前月需要审核的学校）", notes = "审核页面列表（查询当前月需要审核的学校）")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "projectId", value = "项目id", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "month", value = "月份", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "checkStatus", value = "状态", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "town_id", value = "区县id", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "stage", value = "阶段", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "status", value = "当前应该看到的状态的学校", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "level", value = "2区级1市级", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/checkList", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult checkList(OperaResult result,String projectId,String month,String status,String town_id,String stage,String checkStatus,String level,String heshi) {
        Map<String,Object> params = new HashMap<>();
        params.put("projectId", projectId);
        if(StringUtils.isEmpty(month)){
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");        
            Date date = new Date();
            month = sdf.format(date);
            result.getContent().put("month", month);
    	}
    	params.put("month", month);
        params.put("town_id", town_id);
        params.put("status", status);
        params.put("stage", stage);
        params.put("checkStatus", checkStatus);
        params.put("heshi", heshi);
        params.put("ifnow", "0");
        List<checkListDto> list = new ArrayList<>();
        boolean iscolor = false;
        //如果是市级
        if(level.equals("1")){
        	List<checkListDto> shijiList = tSchoolScheduleService.checkInfoShijiList(params);
        	//过程的个数
        	List<scheduleListDto> slist = tSchoolScheduleService.quxianScheduleList(params);
        	Integer jieduan = -1;
        	int colorNum = 0;
        	String color = "";
        	for(scheduleListDto sc:slist){
        		if(jieduan == sc.getStage()){
        			sc.setColor(color);
        		}else{
        			color = ConstantUtil.colors[colorNum%ConstantUtil.colors.length];
        			sc.setColor(color);
        			colorNum++;
        			jieduan = sc.getStage();
        		}
        	}
        	iscolor = true;
        	shijiList.get(0).setScheduleList(slist);
            list.addAll(shijiList);
        }
        //查询区级数据
        List<checkListDto> qujiList = tSchoolScheduleService.checkInfoQuxianList(params);
        for(checkListDto item:qujiList){
        	list.add(item);
            params.put("townId", item.getTownId());
            //过程的个数
            List<scheduleListDto> slist = tSchoolScheduleService.quxianScheduleList(params);
            //如果是区级并且没设置颜色，则需要颜色 
            if(level.equals("2") && !iscolor){
            	Integer jieduan = -1;
            	int colorNum = 0;
            	String color = "";
                for(scheduleListDto sc:slist){
            		if(jieduan == sc.getStage()){
            			sc.setColor(color);
            		}else{
            			color = ConstantUtil.colors[colorNum%ConstantUtil.colors.length];
            			sc.setColor(color);
            			colorNum++;
            		}
            	}
                iscolor = true;
            }
            item.setScheduleList(slist);
        	//查询区级下面的学校
            List<checkListDto> xuexiaoList = tSchoolScheduleService.checkInfoXuexiaoList(params);
            for(checkListDto xuexiao:xuexiaoList){
            	int sort = 1;
            	xuexiao.setSort(sort);
            	sort++;
                //查询倒排时间
            	List<TSchoolInverted> ilist = tSchoolInvertedService.findByTProjectSchoolId(xuexiao.getTProjectSchoolId());
            	xuexiao.setInvertedList(ilist);
            	//查文件
            	List<TSchoolFile> flist = tSchoolFileService.findByTProjectSchoolId(xuexiao.getScheduleId(),2);
            	xuexiao.setFileList(flist);
            }
            list.addAll(xuexiaoList);
        }
        
        result.getContent().put("list", list);
        return result;
    }
	
	@ApiOperation(value = "年度排名表", notes = "年度排名表")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "projectId", value = "项目id", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/yearSort", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult yearSort(OperaResult result,Integer projectId,String year) {
        Map<String,Object> params = new HashMap<>();
        params.put("projectId", projectId);
        params.put("year", year);
        List<TYearSort> list = new ArrayList<>();
        //合计
        TYearSort shijiite = tYearSortService.yearSortCount(params);
        shijiite.setTownName("合计");
        shijiite.setTypes(0);
        list.add(shijiite);
        //查询区县类别
        List<TDictionary> townTypeList = tDictionaryService.findByPcode("103");
        for(TDictionary item:townTypeList){
            params.put("town_type", item.getCode());
            List<TYearSort> qujiList = tYearSortService.findByYearAndProjectIdAndTownType(year, projectId, item.getCode());
            if(qujiList!=null && qujiList.size()>0){
            	list.addAll(qujiList);
                TYearSort qulei = tYearSortService.yearSortCount(params);
                qulei.setTownName("合计");
                qulei.setTownTypeName(item.getName());
                qulei.setTypes(1);
                list.add(qulei);
            }
            
        }
        result.getContent().put("list", list);
        return result;
    }
	
	@ApiOperation(value = "年度排名表计算", notes = "年度排名表计算")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "projectId", value = "项目id", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/yearSortJisuan", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult yearSortJisuan(OperaResult result,Integer projectId,String year) {
        Map<String,Object> params = new HashMap<>();
        params.put("projectId", projectId);
        params.put("year", year);
        List<yearSortListDto> list = new ArrayList<>();
        //删除数据
        tYearSortService.removeByYearAndProjectId(year, projectId);
        //表格中写数据
        List<TYearSort> ysList = tSchoolScheduleService.yearSortJisuan(params);
        for(TYearSort one:ysList){
        	one.setYear(year);
        	one.setProjectId(projectId);
        	one.setTypes(1);
        	tYearSortService.save(one);
        }
        //查询区县类别
        List<TDictionary> townTypeList = tDictionaryService.findByPcode("103");
        for(TDictionary item:townTypeList){
        	params.put("town_type", item.getCode());
        	yearSortListDto quleiitem = tSchoolScheduleService.yearSort(params);
        	params.put("zxm", quleiitem.getCount());
        	params.put("zmj", quleiitem.getArea());
        	params.put("ztz", quleiitem.getAllInvest());
        	if(quleiitem.getCount() != null && quleiitem.getCount()>0){
        		List<TYearSort> ttList = tYearSortService.findByYearAndProjectIdAndTownTypeSort(params);
            	for(int i=0;i<ttList.size();i++){
            		TYearSort tt = ttList.get(i);
            		tt.setScore(tt.getQunzhongfen());
            		tt.setSort1(tt.getQunzhongfensort());
            		tYearSortService.save(tt);
            	}
        	}
        	
        }
        //排序2
        List<TYearSort> ttList2 = tYearSortService.findByYearAndProjectIdOrderByAllInvestDesc(params);
        for(int i=0;i<ttList2.size();i++){
    		TYearSort tt = ttList2.get(i);
    		tt.setSort2(tt.getQunzhongfensort());
    		tYearSortService.save(tt);
    	}
        //排序3
        List<TYearSort> ttList3 = tYearSortService.findByYearAndProjectIdOrderByKaigonglvDesc(params);
        for(int i=0;i<ttList3.size();i++){
    		TYearSort tt = ttList3.get(i);
    		tt.setSort3(tt.getQunzhongfensort());
    		tYearSortService.save(tt);
    	}
        result.getContent().put("list", list);
        return result;
    }
	
	@ApiOperation(value = "月份比对统计", notes = "月份比对统计")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "projectId", value = "项目id", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "months", value = "对比的月份", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/monthContrast", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult monthContrast(OperaResult result,String projectId,String months) {
        Map<String,Object> params = new HashMap<>();
        params.put("projectId", projectId);
    	params.put("status", 1);
    	params.put("ifnow", "0");
        String[] month = months.split(",");
    	List<checkListDto> list = new ArrayList<>();
        for(String m:month){
        	params.put("month", m);
            boolean iscolor = false;
            //如果是市级
            	List<checkListDto> shijiList = tSchoolScheduleService.checkShijiList(params,"month");
            	//月份
            	for(checkListDto item:shijiList){
            		item.setMonth(m);
            	}
            	//过程的个数
            	List<scheduleListDto> slist = tSchoolScheduleService.quxianScheduleList(params);
            	Integer jieduan = -1;
            	int colorNum = 0;
            	String color = "";
            	if(!iscolor){
            		for(scheduleListDto sc:slist){
                		if(jieduan == sc.getStage()){
                			sc.setColor(color);
                		}else{
                			color = ConstantUtil.colors[colorNum%ConstantUtil.colors.length];
                			sc.setColor(color);
                			colorNum++;
                			jieduan = sc.getStage();
                		}
                	}
                	iscolor = true;
                	shijiList.get(0).setScheduleList(slist);
            	}            	
                list.addAll(shijiList);
        }
        List<TDictionary> townList = tDictionaryService.findByPcode("100");
        for(TDictionary town:townList){
        	params.put("town_id", town.getCode());
        	params.put("townId", town.getCode());
        	for(String m:month){
        		params.put("month", m);
        		List<checkListDto> qujiList = tSchoolScheduleService.checkQujiList(params,"month");
        		if(qujiList!=null && qujiList.size()>0){
        			List<scheduleListDto> slist = tSchoolScheduleService.quxianScheduleList(params);
            		qujiList.get(0).setScheduleList(slist);
            		qujiList.get(0).setMonth(m);
            		list.addAll(qujiList);
        		}        		
        	}
        	List<TProjectSchool> schoolList = tProjectSchoolService.findByTownId(town.getCode());
        	for(TProjectSchool school:schoolList){
        		int sort = 1;
        		params.put("schoolId", school.getId());
        		for(String m:month){
        			params.put("month", m);
        			List<checkListDto> xuexiaoList = tSchoolScheduleService.checkXuexiaoList(params,"month");
        			if(xuexiaoList!=null && xuexiaoList.size()>0){
        				try {
							xuexiaoList.get(0).setMonth(m);
							xuexiaoList.get(0).setSort(sort);
							list.addAll(xuexiaoList);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			}        			
        		}
        		sort++;
        	}
        }
        result.getContent().put("list", list);
        return result;
    }
	
	@ApiOperation(value = "月份比对统计", notes = "月份比对统计")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "projectId", value = "项目id", required = true, dataType = "String",paramType = "query"),
		@ApiImplicitParam(name = "months", value = "对比的月份", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/monthContrastCount", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult monthContrastCount(OperaResult result,String projectId,String months) {
        Map<String,Object> params = new HashMap<>();
        params.put("projectId", projectId);
        String[] month = months.split(",");
        List<MonthContrastCountDto> list = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.##");
        for(String m:month){
        	params.put("month", m);
        	MonthContrastCountDto item = tSchoolScheduleService.monthContrastCount(params);
        	item.setMonth(m);
        	if(item.getCount()!=null && item.getCount()!=0){
        		item.setWcl(item.getSy() == 0 ? "0" : df.format((float)item.getSy()/item.getCount()*100));
        		item.setKgl(item.getKg() == 0 ? "0" : df.format((float)item.getKg()/item.getCount()*100));
        		item.setWkgl(item.getWkg() == 0 ? "0" : df.format((float)item.getWkg()/item.getCount()*100));
        	}
        	list.add(item);
        }
        result.getContent().put("list", list);
        return result;
	}
	
	@SuppressWarnings("null")
    @ApiOperation(value = "按项目查询年度", notes = "按项目查询年度")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "projectId", value = "项目id", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/queryYear", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult queryYear(OperaResult result,String projectId,String months) {
        Map<String,Object> params = new HashMap<>();
        params.put("projectId", projectId);
        List<YearDTO> list = new ArrayList<>();
        list = tSchoolScheduleService.queryYear(params);
        Calendar cale = null;  
        cale = Calendar.getInstance();  
        int year = cale.get(Calendar.YEAR);  
        int month = cale.get(Calendar.MONTH) + 1;
        if(list==null){
        	YearDTO y = new YearDTO();
        	y.setLabel(year+"");
        	y.setValue(year+"");
        	list.add(y);
        }
        result.getContent().put("year", list.get(0).getValue());
        result.getContent().put("month", month<10?"0"+month:month);
        result.getContent().put("list", list);
        return result;
	}
	
	@ApiOperation(value = "按项目查询阶段", notes = "按项目查询阶段")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "projectId", value = "项目id", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/queryStage", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult queryStage(OperaResult result,Integer projectId,String months) {
        List<TProjectStage> list = tProjectStageService.findByProjectId(projectId);
        result.getContent().put("list", list);
        return result;
	}
	
	@ApiOperation(value = "按项目查询区县", notes = "按项目查询阶段")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "projectId", value = "项目id", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/queryTownByProjectId", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult queryTownByProjectId(OperaResult result,Integer projectId) {
		Map<String,Object> params = new HashMap<>();
        params.put("projectId", projectId);
        List<DataDTO> list = tSchoolScheduleService.queryTownByProjectId(params);
        result.getContent().put("list", list);
        return result;
	}
	
	@ApiOperation(value = "添加学校", notes = "添加学校")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "projectId", value = "项目id", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/addSchool", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult addSchool(OperaResult result,Integer projectId,String startYear,String endYear,String area, String newBuild, String schoolName, String reward, String fileStr, Integer id, String townId, String invest,String schoolType) {
		String content = "";
		Integer type = 0;
		TProjectSchool school = null;
		if(id == null){
			school = new TProjectSchool();
			content = "添加学校"+schoolName;
		}else{
			school = tProjectSchoolService.findById(id);
			type = 1;
			content = "修改学校"+schoolName;
		}
		
		school.setProjectId(projectId);
		school.setEndYear(endYear);
		school.setStartYear(startYear);
		school.setSchoolName(schoolName);
		school.setReward(reward);
		school.setArea(StringUtils.isEmpty(area)?null:Float.parseFloat(area));
		school.setNewBuild(StringUtils.isEmpty(newBuild)?null:Integer.valueOf(newBuild));
		school.setInvest(StringUtils.isEmpty(invest)?null:Float.parseFloat(invest));
		school.setTownId(townId);
		school.setSchoolType(schoolType);
		tProjectSchoolService.save(school);
		
		//查文件
		if(id != null){
			List<TSchoolFile> flist = tSchoolFileService.findByTProjectSchoolId(id,1);
			for(TSchoolFile item:flist){
				tSchoolFileService.delete(item);
			}
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
				    sf.setProjectSchoolId(school.getId());
				    sf.setType(1);
				    tSchoolFileService.save(sf);
				}
			} catch (JSONException  e) {
				System.out.println(e.getMessage());
			}
		}
		LogUtil.addLog(type, getUserGuid(),getUName(), content);
        return result;
	}
	
	@ApiOperation(value = "查询学校信息", notes = "查询学校信息")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "项目学校id", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/queryOneSchool", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult queryOneSchool(OperaResult result,Integer id) {
		TProjectSchool school = tProjectSchoolService.findById(id);
		result.getContent().put("school", school);
        return result;
	}	
	@ApiOperation(value = "查询学校文件", notes = "查询学校文件")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "项目学校id", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/queryOneSchoolFile", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult queryOneSchoolFile(OperaResult result,Integer id) {
		List<TSchoolFile> flist = tSchoolFileService.findByTProjectSchoolId(id,1);
		result.getContent().put("schoolFile", flist);
        return result;
	}	
	
	@ApiOperation(value = "设置倒排时间", notes = "设置倒排时间")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "schoolId", value = "项目学校id", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/shezhiDaopaishijian", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult shezhiDaopaishijian(OperaResult result,Integer schoolId,String timeStr) {
		List<TSchoolInverted> ilist = tSchoolInvertedService.findByTProjectSchoolId(schoolId);
		for(TSchoolInverted item:ilist){
			tSchoolInvertedService.delete(item);
		}
    	//更新学校文件
		if(StringUtils.isNotEmpty(timeStr)){
			try {
				JSONArray jsonArray = JSONArray.fromObject(timeStr);
				for (int i = 0; i < jsonArray.size(); i++) {
					TSchoolInverted sf = new TSchoolInverted();
		        	JSONObject myjObject = jsonArray.getJSONObject(i);
				    String name = myjObject.getString("name");    
				    String time = myjObject.getString("time");
				    if(myjObject.getString("isFinish")!=null&&!myjObject.getString("isFinish").equals("")){
				        sf.setIsFinish(0);
				        sf.setFinishTime(new Date());
				    }
				    Date date = null;
				    if(time.length()==10){
				    	date = DateUtil.parseDate(time, 3);
				    }else{
				    	long lt = new Long(time);
				        date = new Date(lt);
				    }
				    sf.setName(name);
				    sf.setTime(date);
				    sf.setProjectSchoolId(schoolId);
				    tSchoolInvertedService.save(sf);
				}
			} catch (JSONException  e) {
				System.out.println(e.getMessage());
			}
		}
        return result;
	}
	
	@ApiOperation(value = "查询倒排时间", notes = "查询倒排时间")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "schoolId", value = "项目学校id", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/queryDaopaishijian", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult queryDaopaishijian(OperaResult result,Integer schoolId) {
		List<TSchoolInverted> ilist = tSchoolInvertedService.findByTProjectSchoolId(schoolId);
		for (int i = 0; i < ilist.size(); i++) {
			TSchoolInverted tSchoolInverted = ilist.get(i);
			tSchoolInverted.setStringDate(DateUtil.toDateStr(tSchoolInverted.getTime(), 3));
		}
		result.getContent().put("list", ilist);
        return result;
	}
	
	@ApiOperation(value = "更新倒排时间状态", notes = "更新倒排时间状态")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "schoolId", value = "项目学校id", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/changeDaopaishijian", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult changeDaopaishijian(OperaResult result,Integer id) {
	    log.info("更新倒排时间状态");
	    TSchoolInverted tSchoolInverted = tSchoolInvertedService.findById(id);
	    tSchoolInverted.setIsFinish(1);
	    tSchoolInvertedService.save(tSchoolInverted);
	    
        return result;
    }
	
	@ApiOperation(value = "查询历史提交记录", notes = "查询历史提交记录")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "schoolId", value = "项目学校id", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/querySchoolSchedule", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult querySchoolSchedule(OperaResult result,Integer schoolId) {

		Map<String,Object> params = new HashMap<>();
        params.put("schoolId", schoolId);
		List<SchoolScheduleListDto> ilist = tSchoolScheduleService.querySchoolSchedule(params);
		for(SchoolScheduleListDto item:ilist){
			List<TSchoolFile> flist = tSchoolFileService.findByTProjectSchoolId(item.getId(),2);
			item.setFileList(flist);
		}
		result.getContent().put("list", ilist);
        return result;
	}

	@ApiOperation(value = "手机端饼图", notes = "手机端饼图")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "projectId", value = "项目id", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/queryStageCountForApp", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult queryStageCountForApp(OperaResult result,String projectId) {
        Map<String,Object> params = new HashMap<>();
        params.put("projectId", projectId);
        List<scheduleListDto> list = tSchoolScheduleService.queryStageCountForApp(params);
        result.getContent().put("list", list);
        return result;
    }
	
	@ApiOperation(value = "查询字典", notes = "查询字典")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "access_token")
	})
	@RequestMapping(value = "/web/queryDic", method = {RequestMethod.POST})
	@ResponseBody
	public OperaResult queryDic(OperaResult result,String pcode) {
		List<TDictionary> list = tDictionaryService.findByPcode(pcode);
		result.getContent().put("list", list);
		return result;
	}
}
