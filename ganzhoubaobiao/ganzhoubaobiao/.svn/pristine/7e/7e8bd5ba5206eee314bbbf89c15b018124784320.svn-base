package com.zklcsoftware.ganzhoubaobiao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name="t_school_schedule")
@ApiModel(value = "TSchoolSchedule", description = "学校项目进度表")
public class TSchoolSchedule extends AbstractBaseDomain {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	id;
	
	@Column(name="t_project_school_id")
	private Integer	schoolId;
	
	@Column(name="t_project_schedule_id")
	private Integer	scheduleId;
	
	@Column(name="finish_time")
	@ApiModelProperty(value = "完成时间")
	@JsonView(WithoutView.class)
	private Date	finishTime;
	
	@Column(name="status")
	@ApiModelProperty(value = "审批流程状态")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	status;
	
	@Column(name="invest")
	@ApiModelProperty(value = "投资")
	@JsonView(WithoutView.class)
	private Float	invest;
	
	@Column(name="reward")
	@ApiModelProperty(value = "重点说明")
	@Length(max=200,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	reward;
	
}
