package com.zklcsoftware.ganzhoubaobiao.domain;

import java.util.Date;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name="t_log")
@ApiModel(value = "TLog", description = "日志表")
public class TLog extends AbstractBaseDomain {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	id;
	
	@Column(name="user_name")
	@ApiModelProperty(value = "操作人姓名")
	@Length(max=20,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	userName;
	
	@Column(name="user_id")
	@ApiModelProperty(value = "操作人id")
	@Length(max=32,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	userId;
	
	@Column(name="content")
	@ApiModelProperty(value = "操作内容")
	@Length(max=200,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	content;
	
	@Column(name="create_time")
	@ApiModelProperty(value = "操作时间")
	@JsonView(WithoutView.class)
	private Date	createTime;
	
	@Column(name="type")
	@ApiModelProperty(value = "操作类型")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	type;
	
	@Column(name="role_id")
	@ApiModelProperty(value = "角色id")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	roleId;
	
	@Transient
    private String roleName;		/* 角色名  */
	
}
