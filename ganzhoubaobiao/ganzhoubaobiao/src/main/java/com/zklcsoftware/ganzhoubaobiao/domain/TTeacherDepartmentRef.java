package com.zklcsoftware.ganzhoubaobiao.domain;

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
@Table(name="t_teacher_department_ref")
@ApiModel(value = "TTeacherDepartmentRef", description = "教职工组织机构关系表")
public class TTeacherDepartmentRef extends AbstractBaseDomain {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	@ApiModelProperty(value = "主键")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	id;
	
	@Column(name="teacher_id")
	@ApiModelProperty(value = "教职工标识号")
	@Length(max=32,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	teacherId;
	
	@Column(name="leader_flag")
	@ApiModelProperty(value = "领导标识(1-是 0-否)")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	leaderFlag;
	
	@Column(name="department_id")
	@ApiModelProperty(value = "组织机构标识号")
	@Length(max=32,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	departmentId;
	
	@Transient
	private String leaderName;
	
}
