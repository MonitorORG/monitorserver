package com.monitor.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;





import com.luckyryan.sample.dao.model.UserCommand;
import com.luckyryan.sample.service.UserCommandServiceImpl;
import com.socket.server.util.StringUtil;

@Controller
public class CommandController {
	
	@Autowired
	private UserCommandServiceImpl commandService;

//    private final String PAGE_INDEX = "command";

//  public @ResponseBody UserCommand command(@PathVariable String id) {
//  public @ResponseBody List<ProfessionArea> changeProfessionArea(HttpServletRequest request){  
    @RequestMapping(value = "/command", method = RequestMethod.GET)
    public @ResponseBody UserCommand command(@RequestParam(value="id") String id) { 
        
    	System.out.println("CommandController: id: " + id);
    	UserCommand command = commandService.getCommand(new Long(id));
    	
    	System.out.println("CommandController: command: " + command);
    	
    	return command;
    }
    
    @RequestMapping(value = "/commandlist", method = RequestMethod.GET)
    public ModelAndView commands() {
        return new ModelAndView("command", "signupForm", "");
    }
    
    @RequestMapping(value = "/getHostCommands", method = RequestMethod.GET)
    public @ResponseBody List<UserCommand> getHostCommands(@RequestParam(value="hostMacAddress") String hostMacAddress) { 
        
    	List<UserCommand> result = new ArrayList<UserCommand>();
    	System.out.println("getHostCommands: hostMacAddress: " + hostMacAddress);
    	
    	if (!StringUtil.isEmpty(hostMacAddress)) {
    		result = commandService.getAll(hostMacAddress);
    	}
    	
    	return result;
    }
    
    @RequestMapping(value = "/createCommand", method = RequestMethod.POST)
    public @ResponseBody String createCommand(@RequestParam(value="hostMacAddress") String hostMacAddress, 
   		 @RequestParam(value="commandStr") String commandStr) { 
       
		String result = "success";
	   	System.out.println("createCommand: hostMacAddress: " + hostMacAddress + " : commandStr: " + commandStr);
	   	
	   	if (!StringUtil.isEmpty(hostMacAddress) && !StringUtil.isEmpty(commandStr)) {
	   		
	   		UserCommand command = new UserCommand();
	   		command.setCommandStr(commandStr);
	   		command.setHostMacAddress(hostMacAddress);
	   		command.setStatus("Created");
	   		UserCommand newCommand = commandService.saveCommand(command);
	   		if (newCommand.getId() == null) {
	   			result = "Failed";
	   		}
	   	} else {
	   		result = "Failed";
	   	}
	   	
	   	return result;
    } 

}
	