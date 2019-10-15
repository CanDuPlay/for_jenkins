package com.zklcsoftware.common.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@SuppressWarnings("serial")
@ApiModel(value="API响应对象")
public class OperaResult implements Serializable {
	
	@ApiModelProperty(value="结果码   0-成功 -1-失败")
	private Integer resultCode = 0;
	
	@ApiModelProperty(value="结果描述(消息框的内容)")
	private String resultDesc;
	
	@ApiModelProperty(value="封装的对象")
	private Map<String,Object> content = new HashMap<String,Object>();
	
}
