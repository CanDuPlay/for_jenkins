package com.zklcsoftware.ganzhoubaobiao.domain;


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
@Table(name="t_year_sort")
@ApiModel(value = "TYearSort", description = "")
public class TYearSort extends AbstractBaseDomain {
	
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
	
	@Column(name="town_name")
	@Length(max=20,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	townName;
	
	@Column(name="town_type")
	@Length(max=10,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	townType;
	
	@Column(name="town_type_name")
	@Length(max=20,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	townTypeName;
	
	@Column(name="area")
	@ApiModelProperty(value = "建筑面积")
	@JsonView(WithoutView.class)
	private Float	area;
	
	@Column(name="invest")
	@ApiModelProperty(value = "投资")
	@JsonView(WithoutView.class)
	private Float	invest;
	
	@Column(name="score")
	@ApiModelProperty(value = "评分")
	@JsonView(WithoutView.class)
	private Float	score;
	
	@Column(name="sort1")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	sort1;
	
	@Column(name="all_invest")
	@ApiModelProperty(value = "累计投资")
	@JsonView(WithoutView.class)
	private Float	allInvest;
	
	@Column(name="sort2")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	sort2;
	
	@Column(name="count")
	@ApiModelProperty(value = "项目总数")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	count;
	
	@Column(name="kaigong")
	@ApiModelProperty(value = "开工数量")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	kaigong;
	
	@Column(name="kaigonglv")
	@ApiModelProperty(value = "开工率")
	@JsonView(WithoutView.class)
	private Float	kaigonglv;
	
	@Column(name="sort3")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	sort3;
	
	@Column(name="weikaigong")
	@ApiModelProperty(value = "未开工数量")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	weikaigong;
	
	@Column(name="year")
	@ApiModelProperty(value = "年度")
	@Length(max=4,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	year;
	
	@Column(name="project_id")
	@Length(max=11,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	projectId;
	
	@Column(name="types")
	@Length(max=1,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private Integer	types;
	
	@Transient
    private Float qunzhongfen;		/* 综合分  */
    
    @Transient
    private Integer qunzhongfensort; /* 综合分排名  */
}
