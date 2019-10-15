package com.zklcsoftware.ganzhoubaobiao.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class scheduleListDto implements Serializable {

	private Integer id;
	private Integer stage;
	private String name;
	private String townId;
	private Integer count;
	private Date time;
	private String color;
	
}
