package com.zklcsoftware.ganzhoubaobiao.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 程序流程适应前端VUE辅助类
 * @author nobody
 *
 */
@SuppressWarnings("serial")
@Data
public class TProcedureVueDTO implements Serializable{
	
	private String id; //唯一id 区分主从关系

	private String procedure; //程序流程
	
	private String childProcedure; //子程序流程
	
	private String name; //办理部门
	
	private String material; //需要提交的材料
	
	private String timeLimit; //工作时限
	
	private Float cost; //报建费用
	
	private String remarks; //备注
	
	private boolean hasChild; //是否包含子程序
	
	private boolean isChild; //是否子程序
	
	private Integer sortFlag;
	
}
