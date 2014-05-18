package com.monitor.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.luckyryan.sample.dao.model.UserEntity;
import com.luckyryan.sample.service.UserServiceImpl;
import com.socket.server.util.StringUtil;

@Controller
public class UserController {
	
	 @Autowired
	 private UserServiceImpl userService;
	 
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
	 
	 @RequestMapping(value = "/admin", method = RequestMethod.GET)
     public ModelAndView adminPage(HttpServletRequest request) {
         return new ModelAndView("admin");
     }
	 
	 @RequestMapping(value = "/registerUsr", method = RequestMethod.GET)
     public ModelAndView registerUsr(HttpServletRequest request) {
         return new ModelAndView("register");
     }
	 
	 @RequestMapping(value = "/createUser", method = RequestMethod.POST)
     public ModelAndView createUser(HttpServletRequest request) {
		 
		 String errormsg = "";
		 UserEntity user = new UserEntity();
		 user.setUsername(request.getParameter("username"));
		 user.setPassword(StringUtil.makeMD5(request.getParameter("password")));
		 user.setPhone(request.getParameter("phonenumber"));
		 user.setFirstName(request.getParameter("firstname"));
		 user.setEmail("");
		 user.setRole("ROLE_USER");
		 user.setEnable(true);
		 
		 try {
			 UserEntity newUser = userService.saveUser(user);
			 if (newUser.getId() != null) {
				 return new ModelAndView("admin", "newUser", newUser);
			 }
		 } catch (Exception e) {
			 errormsg = e.getMessage();			 
		 }
		 
		 return new ModelAndView("register", "errormsg", errormsg);
     }
	 
}
