package com.zklcsoftware.ganzhoubaobiao.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class MonthContrastCountDto implements Serializable {

	private String month;
	private Integer count;
	private Integer kg;
	private Integer wkg;
	private Integer sy;
	private String kgl;
	private String wkgl;
	private String wcl;
	
}
