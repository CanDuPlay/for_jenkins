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
@Table(name="t_project_town_schedule")
@ApiModel(value = "TProjectTownSchedule", description = "")
public class TProjectTownSchedule extends AbstractBaseDomain {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	id;
	
	@Column(name="town_id")
	@Length(max=10,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	townId;
	
	@Column(name="schedule_id")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	scheduleId;
	
	@Column(name="project_id")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	projectId;
	
	@Column(name="start_time")
	@ApiModelProperty(value = "计划开始时间")
	@JsonView(WithoutView.class)
	private Date	startTime;
	
	@Column(name="finish_time")
	@JsonView(WithoutView.class)
	private Date	finishTime;
	
}
