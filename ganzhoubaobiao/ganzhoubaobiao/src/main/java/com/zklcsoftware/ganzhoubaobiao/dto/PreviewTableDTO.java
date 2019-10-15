package com.zklcsoftware.ganzhoubaobiao.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class PreviewTableDTO implements Serializable{

	private PreviewHeaderDTO header;
	
	private List<PreviewBodyDTO> body;
	
	private Integer sumColums;
}
