package com.luckyryan.sample.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * User: ryan
 * Date: 2/7/13
 */
@Controller
public class StatusController {

    private final String PAGE_INDEX = "index";

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView viewStatus() {
        return new ModelAndView(PAGE_INDEX, "signupForm", "");
    }
    
    @RequestMapping(value = "/security-error", method = RequestMethod.GET)
    public String securityError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("page_error", "You do have have permission to do that!");
        return "redirect:/";
    }


}


