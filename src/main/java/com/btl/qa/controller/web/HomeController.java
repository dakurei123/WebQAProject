package com.btl.qa.controller.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.btl.qa.service.ICategoryService;
import com.btl.qa.service.IUserService;

@Controller
public class HomeController {

	@Autowired
	IUserService userService;
	@Autowired
	ICategoryService categoryService;
	
	@GetMapping(value = {"/", "/home"})
	public String home(Principal principal, Model model) {
		model.addAttribute("categories", categoryService.getCategories());
		return "home";
	}
	
}






