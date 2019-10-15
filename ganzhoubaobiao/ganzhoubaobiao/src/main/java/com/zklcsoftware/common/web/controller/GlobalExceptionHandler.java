package com.zklcsoftware.common.web.controller;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zklcsoftware.basic.util.DateUtil;
import com.zklcsoftware.common.dto.OperaResult;
import com.zklcsoftware.common.web.util.ConstantUtil;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})  
    public ResponseEntity<Object> handleOtherExceptions(final Exception ex) {  
        OperaResult tResult = new OperaResult();
        tResult.setResultCode(ConstantUtil.OPT_RESULT_CODE_FAIL);
        tResult.getContent().put("msg", ex.getMessage());
        log.error(DateUtil.formatDate(new Date()), ex);
        return new ResponseEntity<Object>(tResult,HttpStatus.OK);
    }  
  
}
