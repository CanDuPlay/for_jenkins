package com.zklcsoftware.ganzhoubaobiao.domain;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import org.hibernate.validator.constraints.Length;

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
@Table(name="t_project_stage")
@ApiModel(value = "TProjectStage", description = "项目阶段表")
public class TProjectStage extends AbstractBaseDomain {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	id;
	
	@Column(name="project_id")
	@ApiModelProperty(value = "项目id")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	projectId;
	
	@Column(name="name")
	@ApiModelProperty(value = "阶段名称")
	@Length(max=500,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	name;
	
	@Column(name="is_daopai")
	@ApiModelProperty(value = "是否需要设置倒排时间")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	isDaopai;
	
	@Column(name="sort")
	@ApiModelProperty(value = "是否需要设置倒排时间")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	sort;
	
	@Transient
	private String plan;
	
	@Transient
	private Integer selectValue; //选中的流程值
	
	@Transient
	private List<Integer> flowIds;
	
}
