package com.monitor.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

	@RequestMapping(value = "/toHostManagePage", method = RequestMethod.GET)
    public ModelAndView toHostManagePage(HttpServletRequest request) {
		
		List<UserEntity> alluserlist = userService.findAll();
		return new ModelAndView("hostmanage", "alluserlist", alluserlist);
    }
	 
	@RequestMapping(value = "/getAllHostList", method = RequestMethod.GET)
	public @ResponseBody List<HostStatusInfo> getAllHostList() { 
	        
		List<HostStatusInfo> newhostlist = hostService.getAllHostList();   	
		return newhostlist;
	}
	 
	@RequestMapping(value = "/getAssignedHostList", method = RequestMethod.GET)
	public @ResponseBody List<HostStatusInfo> getAssignedHostList() { 
	        
		System.out.println("getAssignedHostList starting...");
		List<HostStatusInfo> assignedhostlist = new ArrayList<HostStatusInfo>();
		UserEntity user = getLoggedUser();
		if (user != null && user.getId() != null) {
			assignedhostlist = hostService.getAssignedHostList(user.getId());   	
		}
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
	 
	 
	 public UserEntity getLoggedUser() {      
         //取得登录用户      
		 UserDetails user = null;
		 UserEntity userEntity = null;
         SecurityContext ctx = SecurityContextHolder.getContext();              
         Authentication auth = ctx.getAuthentication();                    
         if(auth.getPrincipal() instanceof UserDetails) {      
         	user = (UserDetails)auth.getPrincipal();                        
         }  
         
         if (user != null && !StringUtil.isEmpty(user.getUsername())) {
        	 userEntity = userService.findUserByUsername(user.getUsername());   
         }
         return userEntity;      
	 }
	 
	 @RequestMapping(value = "/editHostProcessList", method = RequestMethod.POST)
     public @ResponseBody String editHostProcessList(@RequestParam(value="macAddress") String macAddress, 
    		 @RequestParam(value="processList") String processList) { 
        
		String result = "success";
    	System.out.println("editHostProcessList: macAddress: " + macAddress + " : processList: " + processList);
    	
    	if (!StringUtil.isEmpty(macAddress) && !StringUtil.isEmpty(processList)) {
    		HostStatusInfo host = new HostStatusInfo();
    		host.setMacAddress(macAddress);
    		host.setProcessList(processList);
    		host.setProcessStatusResults(null);
    		host.setIsAgentCommited(false);
    		
    		HostStatusInfo returns = hostService.saveInfo(host);
    	} else {
    		result = "Failed";
    	}
    	
    	System.out.println("result: " + result);
    	
    	return result;
     } 
}
