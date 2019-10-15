package com.zklcsoftware.ganzhoubaobiao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonView;
import com.zklcsoftware.basic.model.AbstractBaseDomain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="t_school_inverted")
@ApiModel(value = "TSchoolInverted", description = "学校倒排表")
public class TSchoolInverted extends AbstractBaseDomain {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	id;
	
	@Column(name="t_project_school_id")
	@ApiModelProperty(value = "项目学校id")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	projectSchoolId;
	
	@Column(name="name")
	@ApiModelProperty(value = "倒排工作名称")
	@Length(max=200,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	name;
	
	@Column(name="time")
	@ApiModelProperty(value = "计划时间")
	@JsonView(WithoutView.class)
	private Date	time;
	
	@Column(name="is_finish")
	@ApiModelProperty(value = "是否完成")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	isFinish;
	
	@Column(name="finish_time")
	@ApiModelProperty(value = "完成时间")
	@JsonView(WithoutView.class)
	private Date	finishTime;
	
	@Transient
	private String stringDate;
}
