package com.monitor.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;

import com.luckyryan.sample.dao.model.UserEntity;
import com.luckyryan.sample.service.UserCommandServiceImpl;
import com.luckyryan.sample.service.UserServiceImpl;

@Controller
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;

//    private final String PAGE_INDEX = "index";

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public ModelAndView viewStatus() {
//        return new ModelAndView(PAGE_INDEX, "signupForm", "");
//    }
    
//    @RequestMapping(value = "/security-error", method = RequestMethod.GET)
//    public String securityError(RedirectAttributes redirectAttributes) {
//        redirectAttributes.addFlashAttribute("page_error", "You do have have permission to do that!");
//        return "redirect:/";
//    }
	 
	 @RequestMapping(value = "/login.html", method = RequestMethod.GET)
     public ModelAndView loginPage() {
         return new ModelAndView("login");
     }
	 
	 @RequestMapping(value = "/denied", method = RequestMethod.GET)
     public ModelAndView deniedPage() {
         return new ModelAndView("denied");
     }
	 
	 @RequestMapping(value = "/logout", method = RequestMethod.GET)
     public ModelAndView logoutPage() {
         return new ModelAndView("logout");
     }
	 
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)  
    protected String handle(UserEntity user, BindingResult result, Model model, HttpServletRequest request) {  
        String flag = userService.userLogin(user);  
        if ("success".equals(flag)) { 
        	request.getSession().setAttribute("username", user.getUsername());
        	request.getSession().setAttribute("userrole", user.getRole());
        	
//            Map<String, String> map = new HashMap<String, String>();  
//            map.put("name", user.getName());  
//            map.put("password", user.getPassword());  
//            return new ModelAndView("success", map);  
            return "index";  
        }  
        return "login.html";  
    }  

}
