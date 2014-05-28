package com.monitor.server.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.luckyryan.sample.dao.model.HostStatusInfo;
import com.luckyryan.sample.dao.model.UserEntity;
import com.luckyryan.sample.service.UserServiceImpl;
import com.socket.server.util.StringUtil;

@Controller
public class UserController {
	
	 @Autowired
	 private UserServiceImpl userService;
		
	@Autowired
	private UserValidation userValidation; // 用户自定义验证
	 
	 @RequestMapping(value = "/login.html", method = RequestMethod.GET)
     public ModelAndView loginPage() {
         return new ModelAndView("login");
     }
	 
	 @RequestMapping(value = "/denied", method = RequestMethod.GET)
     public ModelAndView deniedPage() {
         return new ModelAndView("denied");
     }
	 
	 @RequestMapping(value = "/logout", method = RequestMethod.GET)
     public ModelAndView logoutPage(HttpServletRequest request) {
		 request.getSession().invalidate();
         return new ModelAndView("login");
     }
	 
	 @RequestMapping(value = "/toAdminPage", method = RequestMethod.GET)
     public ModelAndView adminPage(HttpServletRequest request) {
         return new ModelAndView("admin");
     }
	 
	 @RequestMapping(value = "/toUserManagePage", method = RequestMethod.GET)
     public ModelAndView toUserManagePage(HttpServletRequest request) {
         return new ModelAndView("usermanage");
     }
	 
	 @RequestMapping(value = "/toRegisterUsrPage", method = RequestMethod.GET)
     public ModelAndView registerUsr(HttpServletRequest request, Map<String, UserEntity> model) {
		 
		 UserEntity user = new UserEntity();
		 model.put("user", user);
         return new ModelAndView("register");
     }
	 
	 @RequestMapping(value = "/getAllUserList", method = RequestMethod.GET)
	 public @ResponseBody List<UserEntity> getAllUserList() { 
	        
		 List<UserEntity> alluserlist = userService.findAll();   	
		 return alluserlist;
	 }
	 
	 
	 @RequestMapping(value = "/createUser", method = RequestMethod.POST)
     public String createUser(@Valid @ModelAttribute("user") UserEntity user,
    		 BindingResult result, Model model) {
		 
		 userValidation.validate(user, result);
		 if (result.hasErrors()) {
			 model.addAttribute("user",user);
			 return "register";
		 }
		 
		 try {
			 user.setPassword(StringUtil.makeMD5(user.getPassword()));
			 user.setConfirmPassword(StringUtil.makeMD5(user.getConfirmPassword()));
			 UserEntity newUser = userService.saveUser(user);
			 if (newUser.getId() != null) {
				 return "admin";
			 }
		 } catch (Exception e) {			 
			 result.rejectValue("username",
						"save.error",
						e.getMessage());
		 }
		 
		 model.addAttribute("user",user);
		 return "register";
	 }
	 
	 public UserEntity getUser() {      
             //取得登录用户      
		 	 UserEntity user = null;
             SecurityContext ctx = SecurityContextHolder.getContext();              
             Authentication auth = ctx.getAuthentication();                    
             if(auth.getPrincipal() instanceof UserDetails) {      
                     user   =   (UserEntity)auth.getPrincipal();                        
             }     
             return user;      
     }
	 
	 @RequestMapping(value = "/changeUserPwd", method = RequestMethod.GET)
     public @ResponseBody String changeUserPwd(@RequestParam(value="userId") String userId, 
    		 @RequestParam(value="newPwd") String newPwd) { 
        
		String result = "success";
    	System.out.println("enableUser: userId: " + userId + " : newPwd: " + newPwd);
    	
    	if (!StringUtil.isEmpty(userId) && !StringUtil.isEmpty(newPwd)) {
    		result = userService.changeUserPwd(Long.valueOf(userId), StringUtil.makeMD5(newPwd));
    	} else {
    		result = "Failed";
    	}
    	
    	System.out.println("result: " + result);
    	
    	return result;
     } 
	 
	 @RequestMapping(value = "/enableUser", method = RequestMethod.GET)
     public @ResponseBody String enableUser(@RequestParam(value="userId") String userId, 
    		 @RequestParam(value="isEnable") Boolean isEnable) { 
        
		String result = "success";
    	System.out.println("enableUser: userId: " + userId + " : isEnable: " + isEnable);
    	
    	if (!StringUtil.isEmpty(userId) && isEnable != null) {
    		result = userService.enableUser(Long.valueOf(userId), isEnable);
    	} else {
    		result = "Failed";
    	}
    	
    	System.out.println("result: " + result);
    	
    	return result;
     } 
	 
	 
	 @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
     public @ResponseBody String deleteUser(@RequestParam(value="userId") String userId) { 
        
		String result = "success";
    	System.out.println("delete user: userId: " + userId);
    	
    	if (!StringUtil.isEmpty(userId)) {
    		result = userService.deleteUser(Long.valueOf(userId));
    	} else {
    		result = "Failed";
    	}
    	
    	System.out.println("result: " + result);
    	
    	return result;
     } 
	 
}
