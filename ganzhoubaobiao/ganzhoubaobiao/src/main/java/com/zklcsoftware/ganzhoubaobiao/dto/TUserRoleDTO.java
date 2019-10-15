package com.zklcsoftware.ganzhoubaobiao.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TUserRoleDTO implements Serializable{

	private Integer	id; //主键id
	
	private Integer roleId;//角色id
	
	private String type;//用户类型
	
	private String townId;//用户所属区县
	
	private Integer status;//当前用户审核状态
	
	private Integer lastStatus;//上一级审核状态
	
	private Integer nextStatus;//下一级审核状态
	
	private Integer ifCheck;//是否需要审核
	
}
