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
import com.luckyryan.sample.dao.model.UserEntity;
import com.luckyryan.sample.service.HostStatusInfoServiceImpl;
import com.luckyryan.sample.service.UserServiceImpl;
import com.socket.server.util.StringUtil;

@Controller
public class HostController {
	
	@Autowired
	private HostStatusInfoServiceImpl hostService;
	
	@Autowired
	private UserServiceImpl userService;

	@RequestMapping(value = "/toNewHostManagePage", method = RequestMethod.GET)
    public ModelAndView toNewHostManagePage(HttpServletRequest request) {
		
		List<UserEntity> alluserlist = userService.findAll();
		return new ModelAndView("newhostmanage", "alluserlist", alluserlist);
    }
	 
	@RequestMapping(value = "/getAllHostList", method = RequestMethod.GET)
	public @ResponseBody List<HostStatusInfo> getAllHostList() { 
	        
		List<HostStatusInfo> newhostlist = hostService.getAllHostList();   	
		return newhostlist;
	}
	
	@RequestMapping(value = "/toAssignedHostManagePage", method = RequestMethod.GET)
    public ModelAndView toAssignedHostManagePage(HttpServletRequest request) {
		
		List<HostStatusInfo> assignedhostlist = hostService.getAssignedHostList();		
		
        return new ModelAndView("assignedhostmanage", "assignedhostlist", assignedhostlist);
    }
	 
	@RequestMapping(value = "/getAssignedwHostList", method = RequestMethod.GET)
	public @ResponseBody List<HostStatusInfo> getAssignedHostList() { 
	        
		List<HostStatusInfo> assignedhostlist = hostService.getAssignedHostList();   	
		return assignedhostlist;
	}
	 
	 @RequestMapping(value = "/assginUserToHost", method = RequestMethod.GET)
     public @ResponseBody String assginUserToHost(@RequestParam(value="userId") String userId, 
    		 @RequestParam(value="hostId") String hostId) { 
        
		String result = "success";
    	System.out.println("assginUserToHost: userId: " + userId + " : hostId: " + hostId);
    	
    	if (!StringUtil.isEmpty(userId) && !StringUtil.isEmpty(hostId)) {
    		result = hostService.assignUserToHost(Long.valueOf(userId), Long.valueOf(hostId));
    	} else {
    		result = "Failed";
    	}
    	
    	System.out.println("result: " + result);
    	
    	return result;
     } 
	 
	 @RequestMapping(value = "/enableHost", method = RequestMethod.GET)
     public @ResponseBody String enableHost(@RequestParam(value="hostId") String hostId, 
    		 @RequestParam(value="isEnable") Boolean isEnable) { 
        
		String result = "success";
    	System.out.println("enableHost: hostId: " + hostId + " : isEnable: " + isEnable);
    	
    	if (!StringUtil.isEmpty(hostId) && isEnable != null) {
    		result = hostService.enableHost(Long.valueOf(hostId), isEnable);
    	} else {
    		result = "Failed";
    	}
    	
    	System.out.println("result: " + result);
    	
    	return result;
     } 
	 
	 
	 @RequestMapping(value = "/deleteHost", method = RequestMethod.GET)
     public @ResponseBody String deleteHost(@RequestParam(value="hostId") String hostId) { 
        
		String result = "success";
    	System.out.println("delete host: hostId: " + hostId);
    	
    	if (!StringUtil.isEmpty(hostId)) {
    		result = hostService.deleteHostInfo(Long.valueOf(hostId));
    	} else {
    		result = "Failed";
    	}
    	
    	System.out.println("result: " + result);
    	
    	return result;
     } 
	 
}
