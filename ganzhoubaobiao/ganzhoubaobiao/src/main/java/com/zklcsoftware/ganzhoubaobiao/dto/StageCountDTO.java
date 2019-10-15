package com.zklcsoftware.ganzhoubaobiao.dto;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class StageCountDTO implements Serializable {

    private String id;
    private String name;
    private Integer count;
    
}
