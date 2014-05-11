package com.monitor.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.luckyryan.sample.service.HostStatusInfoServiceImpl;

@Controller
public class HostController {
	
	@Autowired
	private HostStatusInfoServiceImpl hostService;

}
