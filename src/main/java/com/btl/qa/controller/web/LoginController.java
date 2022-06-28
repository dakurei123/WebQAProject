package com.btl.qa.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.btl.qa.util.auth;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login() {
		if (auth.isLogged()) {
	        return "redirect:https://hungdeptraino1.herokuapp.com/";
	    }
		return "login";
	}
	
	@GetMapping("/login_error")
	public String loginError(Model model) {
		model.addAttribute("err", "Username/Password is wrong or this account is locked");
		return "login";
	}
	
	@GetMapping("/logoutSuccessful")
	public String logoutSuccessful(Model model) {
		model.addAttribute("success", "Logout success!");
		return "login";
	}
}
