package com.zklcsoftware.common.web.util;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zklcsoftware.common.web.ExtBaseController;
import com.zklcsoftware.ganzhoubaobiao.domain.TLog;
import com.zklcsoftware.ganzhoubaobiao.domain.TUserRole;
import com.zklcsoftware.ganzhoubaobiao.service.TLogService;
import com.zklcsoftware.ganzhoubaobiao.service.TUserRoleService;

@Component
public class LogUtil  extends ExtBaseController{
	@Autowired
	private TLogService tLogService;
//	@Autowired
//	private TUserRoleService tUserRoleService;
	private static LogUtil logUtil;
	
	@PostConstruct
	public void init(){
		logUtil = this;
	}
	
	/**
	 * 操作记录日志
	 * @param type  操作类型 0-新增 1-修改 2-删除
	 * @param userId   用户id
	 * @param content   操作内容
	 */
	public static void addLog(Integer type, String userId, String userName, String content){
		TLog log = new TLog();
		log.setContent(content);
		log.setCreateTime(new Date());
		log.setType(type);
		log.setUserId(userId);
		log.setUserName(userName);
//		TUserRole userRole = logUtil.tUserRoleService.findByUserId(userId);
//		log.setRoleId(userRole.getRoleId());
		logUtil.tLogService.save(log);
	}
}
