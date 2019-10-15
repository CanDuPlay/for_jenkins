package com.zklcsoftware.ganzhoubaobiao.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zklcsoftware.common.dto.OperaResult;
import com.zklcsoftware.common.web.ExtBaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zklcsoftware.ganzhoubaobiao.service.TDataCollectService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Controller of TDataCollect
 * @author administrator
 * @date 2019-3-18
 */
@Slf4j
@Controller
@RequestMapping(path={"","/api"})
public class TDataCollectController extends ExtBaseController {
	
	@Autowired
	private TDataCollectService tDataCollectServiceImpl;
	
	
	
	 /**
	 * form表单提交 Date类型数据绑定
	 * <功能详细描述>
	 * @param binder
	 * @see [类、类#方法、类#成员]
	 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		    dateFormat.setLenient(false);  
		    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}
	
	@ApiOperation(value = "aa", notes = "aa")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "access_token"),
    })
    @RequestMapping(value = "/web/aa", method = {RequestMethod.POST})
    @ResponseBody
    public OperaResult deleteColumn(OperaResult result) {
        System.out.print("111");
        return result;
    }
}
