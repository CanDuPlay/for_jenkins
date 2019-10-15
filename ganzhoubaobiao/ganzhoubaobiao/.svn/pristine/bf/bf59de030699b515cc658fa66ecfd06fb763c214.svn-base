package com.zklcsoftware.ganzhoubaobiao.domain;

import java.util.Date;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Table(name="t_project_town")
@ApiModel(value = "TProjectTown", description = "项目区县表")
public class TProjectTown extends AbstractBaseDomain {
	
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
	
	@Column(name="town_id")
	@ApiModelProperty(value = "区县id")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	townId;
	
	@Column(name="accountability_unit")
	@ApiModelProperty(value = "责任单位")
	@Length(max=100,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	accountabilityUnit;
	
	@Column(name="contacts")
	@ApiModelProperty(value = "联系领导")
	@Length(max=20,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	contacts;
	
	@Column(name="start_time")
	@ApiModelProperty(value = "项目开始时间")
	@JsonView(WithoutView.class)
	private Date	startTime;
	
	@Column(name="end_time")
	@ApiModelProperty(value = "项目结束时间")
	@JsonView(WithoutView.class)
	private Date	endTime;
	
}
