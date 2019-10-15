package com.zklcsoftware.ganzhoubaobiao.dto;

import java.io.Serializable;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class PreviewTdDTO implements Serializable{

	private String label;
	
	private String isNumber; // 1 只允许填数字
}
