package com.zklcsoftware.ganzhoubaobiao.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class PreviewBodyDTO implements Serializable{

	private PreviewTdDTO label;
	
	private List<PreviewTdDTO> body;
	
}
