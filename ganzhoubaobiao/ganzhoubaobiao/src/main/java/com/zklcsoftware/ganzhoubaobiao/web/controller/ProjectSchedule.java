package com.zklcsoftware.ganzhoubaobiao.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zklcsoftware.ganzhoubaobiao.service.TProjectService;

@Component
public class ProjectSchedule {

	@Autowired
	private TProjectService tProjectService;
	
	@Scheduled(cron = "0 30 5 * * *")//5:30
	public void updateProjectStatus(){
		tProjectService.updateProjectStatus();
	}
}
