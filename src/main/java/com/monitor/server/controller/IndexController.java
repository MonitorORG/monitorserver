package com.monitor.server.controller;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.luckyryan.sample.dao.model.UserCommand;


/**
 * User: ryan
 * Date: 2/7/13
 */
@Controller
public class IndexController {

    private final String PAGE_INDEX = "index";

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView viewStatus(HttpSession session) {
    	
    	UserDetails user = getUser();
    	String roleName = getRole(user);
    	
        return new ModelAndView(PAGE_INDEX, "roleName", roleName);
    }
    
    @RequestMapping(value = "/security-error", method = RequestMethod.GET)
    public String securityError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("page_error", "You do have have permission to do that!");
        return "redirect:/";
    }
	 
	 public UserDetails getUser() {      
            //取得登录用户      
		 	UserDetails user = null;
            SecurityContext ctx = SecurityContextHolder.getContext();              
            Authentication auth = ctx.getAuthentication();                    
            if(auth.getPrincipal() instanceof UserDetails) {      
            	user = (UserDetails)auth.getPrincipal();                        
            }     
            return user;      
    }
	 
	public String getRole(UserDetails user) {
		String roleStr = "";
    	if (user != null) {
    		Object[] authorities = user.getAuthorities().toArray();
    		for (Object obj : authorities) {
    			roleStr = obj.toString();
    		}
    	}
    	return roleStr;
	}
	 
}


