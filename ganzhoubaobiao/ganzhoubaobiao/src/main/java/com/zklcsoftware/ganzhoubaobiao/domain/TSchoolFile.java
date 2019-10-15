package com.zklcsoftware.ganzhoubaobiao.domain;


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
@Table(name="t_school_file")
@ApiModel(value = "TSchoolFile", description = "学校资质表")
public class TSchoolFile extends AbstractBaseDomain {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	id;
	
	@Column(name="t_project_school_id")
	@ApiModelProperty(value = "项目学校表id")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	projectSchoolId;
	
	@Column(name="file_url")
	@ApiModelProperty(value = "文件路径")
	@Length(max=100,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	fileUrl;
	
	@Column(name="file_name")
	@ApiModelProperty(value = "文件名称")
	@Length(max=100,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	fileName;
	
	@Column(name="type")
	@ApiModelProperty(value = "1学校图片2进度图片")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	type;
	
}
