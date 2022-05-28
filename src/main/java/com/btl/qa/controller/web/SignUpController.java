package com.btl.qa.controller.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.btl.qa.dto.UserDTO;
import com.btl.qa.service.IUserService;
import com.btl.qa.util.auth;

@Controller
public class SignUpController {
	
	@Autowired
	IUserService userService;
	
	@GetMapping("/signup")
	public String signup(Model model) {
		if (auth.isLogged()) {
	        return "home";
	    }
		model.addAttribute("UserDTO", new UserDTO());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid @ModelAttribute("UserDTO") UserDTO user, BindingResult bindingResult
			, Model model) {
		if (userService.isExisted(user.getUsername())) {
			bindingResult.addError(new ObjectError(user.getUsername(), "User Exited"));
			model.addAttribute("usernameerr", "User Exited");
		}
		
		if(bindingResult.getErrorCount() > 0) {
			for (FieldError error : bindingResult.getFieldErrors()) {
		        model.addAttribute(error.getField() + "err", error.getDefaultMessage());
		    }
			return "signup";
		}
		
		userService.save(user);
		model.addAttribute("success", "Sign Up Successful");
		return "login";
	}
	
}







