package com.monitor.server.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.luckyryan.sample.dao.model.HostStatusInfo;
import com.luckyryan.sample.dao.model.UserCommand;
import com.luckyryan.sample.service.HostStatusInfoServiceImpl;

@Controller
public class HostController {
	
	@Autowired
	private HostStatusInfoServiceImpl hostService;


	@RequestMapping(value = "/toNewHostManagePage", method = RequestMethod.GET)
    public ModelAndView toNewHostManagePage(HttpServletRequest request) {
		
		List<HostStatusInfo> newhostlist = hostService.getNewHostList();		
		
        return new ModelAndView("newhostmanage", "newhostlist", newhostlist);
    }
	 
	@RequestMapping(value = "/getNewHostList", method = RequestMethod.GET)
	public @ResponseBody List<HostStatusInfo> getNewHostList() { 
	        
		List<HostStatusInfo> newhostlist = hostService.getNewHostList();   	
		return newhostlist;
	}
	
	@RequestMapping(value = "/toAssignedHostManagePage", method = RequestMethod.GET)
    public ModelAndView toAssignedHostManagePage(HttpServletRequest request) {
		
		List<HostStatusInfo> assignedhostlist = hostService.getAssignedHostList();		
		
        return new ModelAndView("assignedhostmanage", "assignedhostlist", assignedhostlist);
    }
	 
	@RequestMapping(value = "/getNewHostList", method = RequestMethod.GET)
	public @ResponseBody List<HostStatusInfo> getAssignedHostList() { 
	        
		List<HostStatusInfo> assignedhostlist = hostService.getAssignedHostList();   	
		return assignedhostlist;
	}
	 
	 
	 
	 
	 
}
