package com.zklcsoftware.ganzhoubaobiao.domain;

import java.util.Date;
import java.io.Serializable;

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
@Table(name="t_data_collect")
@ApiModel(value = "TDataCollect", description = "")
public class TDataCollect extends AbstractBaseDomain {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	id;
	
	@Column(name="title")
	@ApiModelProperty(value = "标题")
	@Length(max=100,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	title;
	
	@Column(name="instruction")
	@ApiModelProperty(value = "说明")
	@Length(max=500,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	instruction;
	
	@Column(name="file_url")
	@ApiModelProperty(value = "文件路径")
	@Length(max=50,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	fileUrl;
	
	@Column(name="file_name")
	@ApiModelProperty(value = "上传文件名称")
	@Length(max=50,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String fileName;
	
	@Column(name="remind_way")
	@ApiModelProperty(value = "提醒方式")
	@Length(max=20,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	remindWay;
	
	@Column(name="remind_content")
	@ApiModelProperty(value = "提醒内容")
	@Length(max=500,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	remindContent;
	
	@Column(name="create_time")
	@ApiModelProperty(value = "创建时间")
	@JsonView(WithoutView.class)
	private Date createTime;
	
	@Transient
	private Integer referNumber; //已提交数量
	
	@Transient
	private Integer sumNumber; //总数
	
	@Transient
	private Boolean delFlag;
	
}
