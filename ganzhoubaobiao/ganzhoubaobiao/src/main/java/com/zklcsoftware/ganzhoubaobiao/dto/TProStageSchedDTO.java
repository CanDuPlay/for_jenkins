package com.zklcsoftware.ganzhoubaobiao.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TProStageSchedDTO implements Serializable {

    private String projectId;
    
    private String projectName;
    
    private String id;
    
    private String stage;
    
    private String scheduleId;
    
    private String scheduleName;
    
    private Date startTime;
    
    private Date finishTime;
    
    private Float invest;
    
    private String[] dateTime;

}
