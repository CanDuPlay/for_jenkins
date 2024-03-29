package com.zklcsoftware.ganzhoubaobiao.dto;

import java.io.Serializable;
import java.util.List;

import com.zklcsoftware.ganzhoubaobiao.domain.TSchoolFile;

import lombok.Data;


@SuppressWarnings("serial")
@Data
public class TProjectSchoolDTO implements Serializable {

    private String projectId;           //项目id
    
    private String id;                  //学校id
    
    private Integer schoolScheduleId;   //学校项目进度id
    
    private String townId;              //区县id
    
    private String townName;            //区县名
    
    private String schoolId;            //学校id
    
    private String schoolName;          //学校名称
    
    private String schoolType;          //学校类型
    
    private Float area;                 //建筑面积
    
    private Integer newBuild;           //新增学位
    
    private Float invest;               //规划总投资
    
    private String startYear;           //开工年份
    
    private String endYear;             //竣工年份
    
    private String nowSchedule;         //项目状态
    
    private Boolean shenBao;          //是否申报
    
    private Integer roleStatus;         //审核id 

    private Integer isDaopai;           //是否倒排   0或空：否   1：是
    
    private String name;                //联系人
    
    private String phone;               //联系电话
    
    private Float planinvest;               //计划总投资
    
    private String reward;				//重点说明
    
    private Integer projectStatus;		//项目状态
    
	private List<TSchoolFile> fileList;
    
 
}
