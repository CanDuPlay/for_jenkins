package com.zklcsoftware.ganzhoubaobiao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zklcsoftware.basic.service.impl.BaseServiceImpl;
import com.zklcsoftware.ganzhoubaobiao.domain.TSchoolSchedule;
import com.zklcsoftware.ganzhoubaobiao.domain.TYearSort;
import com.zklcsoftware.ganzhoubaobiao.dto.DataDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.MonthContrastCountDto;
import com.zklcsoftware.ganzhoubaobiao.dto.SchoolScheduleListDto;
import com.zklcsoftware.ganzhoubaobiao.dto.YearDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.checkListDto;
import com.zklcsoftware.ganzhoubaobiao.dto.scheduleListDto;
import com.zklcsoftware.ganzhoubaobiao.dto.yearSortListDto;
import com.zklcsoftware.ganzhoubaobiao.repository.TSchoolScheduleRepository;
import com.zklcsoftware.ganzhoubaobiao.service.TSchoolScheduleService;

@Service
@Transactional
public class TSchoolScheduleServiceImpl extends BaseServiceImpl<TSchoolSchedule, Integer> implements TSchoolScheduleService {

	@Autowired 
	private TSchoolScheduleRepository tSchoolScheduleRepository;
	
	@Override
	public List<TSchoolSchedule> checkStatusList(Map<String, Object> params) {
		List<TSchoolSchedule> scheduleList = tSchoolScheduleRepository.checkStatusList(params);
		return scheduleList;
	}
	
	@Override
	public List<checkListDto> checkShijiList(Map<String, Object> params,String type) {
		return tSchoolScheduleRepository.checkShijiList(params,type);
	}
	
	@Override
	public List<checkListDto> checkQujiList(Map<String, Object> params,String type) {
		return tSchoolScheduleRepository.checkQujiList(params,type);
	}
	
	@Override
	public List<checkListDto> checkXuexiaoList(Map<String, Object> params,String type) {
		return tSchoolScheduleRepository.checkXuexiaoList(params,type);
	}
	
	@Override
	public List<scheduleListDto> quxianScheduleList(Map<String, Object> params) {
		return tSchoolScheduleRepository.quxianScheduleList(params);
	}
	
	@Override
	public List<checkListDto> checkInfoShijiList(Map<String, Object> params) {
		return tSchoolScheduleRepository.checkInfoShijiList(params);
	}
	
	@Override
	public List<checkListDto> checkInfoQuxianList(Map<String, Object> params) {
		return tSchoolScheduleRepository.checkInfoQuxianList(params);
	}
	
	@Override
	public List<checkListDto> checkInfoXuexiaoList(Map<String, Object> params) {
		return tSchoolScheduleRepository.checkInfoXuexiaoList(params);
	}
	
	@Override
	public yearSortListDto yearSort(Map<String, Object> params) {
		return tSchoolScheduleRepository.yearSort(params);
	}
		
	@Override
	public List<scheduleListDto> kanbanCount(Map<String, Object> params) {
		return tSchoolScheduleRepository.kanbanCount(params);
	}
	
	@Override
	public MonthContrastCountDto monthContrastCount(Map<String, Object> params) {
		return tSchoolScheduleRepository.monthContrastCount(params);
	}
	
	@Override
	public List<TYearSort> yearSortJisuan(Map<String, Object> params) {
		return tSchoolScheduleRepository.yearSortJisuan(params);
	}
	
	@Override
	public List<YearDTO> queryYear(Map<String, Object> params) {
		return tSchoolScheduleRepository.queryYear(params);
	}
	
	@Override
	public List<DataDTO> queryTownByProjectId(Map<String, Object> params) {
		return tSchoolScheduleRepository.queryTownByProjectId(params);
	}
	
	@Override
	public List<SchoolScheduleListDto> querySchoolSchedule(Map<String, Object> params) {
		return tSchoolScheduleRepository.querySchoolSchedule(params);
	}
	
	@Override
	public List<scheduleListDto> queryStageCountForApp(Map<String, Object> params) {
		return tSchoolScheduleRepository.queryStageCountForApp(params);
	}
}
