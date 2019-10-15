package com.zklcsoftware.ganzhoubaobiao.service;

import java.util.List;
import java.util.Map;

import com.zklcsoftware.basic.service.BaseService;
import com.zklcsoftware.ganzhoubaobiao.domain.TSchoolSchedule;
import com.zklcsoftware.ganzhoubaobiao.domain.TYearSort;
import com.zklcsoftware.ganzhoubaobiao.dto.DataDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.MonthContrastCountDto;
import com.zklcsoftware.ganzhoubaobiao.dto.SchoolScheduleListDto;
import com.zklcsoftware.ganzhoubaobiao.dto.YearDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.checkListDto;
import com.zklcsoftware.ganzhoubaobiao.dto.scheduleListDto;
import com.zklcsoftware.ganzhoubaobiao.dto.yearSortListDto;

public interface TSchoolScheduleService extends BaseService<TSchoolSchedule,Integer> {


	List<TSchoolSchedule> checkStatusList(Map<String, Object> params);

	List<checkListDto> checkShijiList(Map<String, Object> params,String type);

	List<checkListDto> checkQujiList(Map<String, Object> params,String type);

	List<checkListDto> checkXuexiaoList(Map<String, Object> params,String type);

	List<scheduleListDto> quxianScheduleList(Map<String, Object> params);

	List<checkListDto> checkInfoShijiList(Map<String, Object> params);

	List<checkListDto> checkInfoQuxianList(Map<String, Object> params);

	List<checkListDto> checkInfoXuexiaoList(Map<String, Object> params);

	yearSortListDto yearSort(Map<String, Object> params);

	List<scheduleListDto> kanbanCount(Map<String, Object> params);

	MonthContrastCountDto monthContrastCount(Map<String, Object> params);

	List<TYearSort> yearSortJisuan(Map<String, Object> params);

	List<YearDTO> queryYear(Map<String, Object> params);

	List<DataDTO> queryTownByProjectId(Map<String, Object> params);

	List<SchoolScheduleListDto> querySchoolSchedule(Map<String, Object> params);

	List<scheduleListDto> queryStageCountForApp(Map<String, Object> params);
	
}
