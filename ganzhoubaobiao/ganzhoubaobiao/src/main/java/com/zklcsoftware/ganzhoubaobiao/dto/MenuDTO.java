package com.zklcsoftware.ganzhoubaobiao.dto;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class MenuDTO implements Serializable {

    private String path;
    private String label;
    private String icon;

}
