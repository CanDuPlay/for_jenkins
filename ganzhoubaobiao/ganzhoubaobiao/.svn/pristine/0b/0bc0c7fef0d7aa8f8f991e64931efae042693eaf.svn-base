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
@Table(name="t_dictionary")
@ApiModel(value = "TDictionary", description = "字典表")
public class TDictionary extends AbstractBaseDomain {
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name="code")
	@Length(max=10,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	code;
	
	@Column(name="name")
	@Length(max=100,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	name;
	
	@Column(name="pcode")
	@Length(max=10,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	pcode;
	
	@Column(name="town_type")
	@Length(max=10,groups= {UpdateValid.class, AddValid.class})
	@JsonView(WithoutView.class)
	private String	townType;
	
}
