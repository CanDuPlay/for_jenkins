package com.zklcsoftware.ganzhoubaobiao.domain;


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
@Table(name="t_role")
@ApiModel(value = "TRole", description = "角色表")
public class TRole extends AbstractBaseDomain {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	id;
	
	@Column(name="name")
	@ApiModelProperty(value = "名称")
	@Length(max=20,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	name;
	
	@Column(name="if_check")
	@ApiModelProperty(value = "是否需要审核")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	ifCheck; // 1 需要审核  其他 不需要审核
	
	@Column(name="status")
	@ApiModelProperty(value = "审批通过状态标识")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	status;
	
	@Column(name="type")
	@ApiModelProperty(value = "用户类别")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	type;
	
}
