package com.monitor.server.controller;

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

}
	