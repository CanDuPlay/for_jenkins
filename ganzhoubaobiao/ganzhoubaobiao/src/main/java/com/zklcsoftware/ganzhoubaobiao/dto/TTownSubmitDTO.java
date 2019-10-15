package com.zklcsoftware.ganzhoubaobiao.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;

import lombok.Data;


@SuppressWarnings("serial")
@Data
public class TTownSubmitDTO implements Serializable{

	@Transient
	private Integer	id;
	
	@Transient
	private String title; //数据收集标题
	
	@Transient
	private String townId;
	
	@Transient
	private Integer	isSubmit;
	
	@Transient
	private String fileUrl;
	
	@Transient
	private Date submitTime;
	
	@Transient
	private Date createTime;
	
	@Transient
	private Integer collectId;
	
	@Transient
	private Integer submitId;
	
	@Transient
	private String name; //接收人姓名
	
	@Transient
	private String townName; //区县名称
	
	@Transient
	private String phone; //联系电话
	
	@Transient
	private Integer remindCount; //提醒次数
	
}
