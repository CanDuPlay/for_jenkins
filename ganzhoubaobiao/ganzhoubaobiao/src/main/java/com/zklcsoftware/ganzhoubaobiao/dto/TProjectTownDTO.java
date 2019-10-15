package com.zklcsoftware.ganzhoubaobiao.dto;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TProjectTownDTO implements Serializable{

	private String code;
	
	private String name;
	
	private String danwei;
	
	private String lingdao;
	
	private String townType;
	
}
