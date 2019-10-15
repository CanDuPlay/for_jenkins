package com.zklcsoftware.ganzhoubaobiao.dto;

import java.io.Serializable;
import java.util.List;

import com.zklcsoftware.ganzhoubaobiao.domain.TSchoolFile;
import com.zklcsoftware.ganzhoubaobiao.domain.TSchoolInverted;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class MonthDuibiListDto implements Serializable {
	
	private Integer tProjectSchoolId;
	private String schoolName;
	private String month;
	private Double allInvest;
	private List<scheduleListDto> scheduleList;
	
}
