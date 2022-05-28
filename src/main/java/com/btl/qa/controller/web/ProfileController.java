package com.btl.qa.controller.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.btl.qa.dto.UserDTO;
import com.btl.qa.repository.UserRepository;
import com.btl.qa.security.MyUserDetails;
import com.btl.qa.service.ICategoryService;
import com.btl.qa.service.IUserService;
import com.btl.qa.util.Convert;
import com.btl.qa.util.IOFile;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserService userService;
    
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	ICategoryService categoryService;
    
    @GetMapping("/profile/{userId}")
	@PreAuthorize("isAuthenticated()")
	public String user(Model model, @PathVariable Long userId) {
    	model.addAttribute("categories", categoryService.getCategories());
		model.addAttribute("user", userService.getUser(userId));
		return "profile";
	}
	
	@GetMapping("/editprofile")
	@PreAuthorize("isAuthenticated()")
	public String editProfile(Model model) {
		model.addAttribute("UserDTO", userService.getUser());
		return "editprofile";
	}
    
    @PostMapping("/editprofile")
    @PreAuthorize("isAuthenticated()")
    public String editProfile(@RequestParam String fullName, @RequestParam String password,
    		Model model, @RequestParam MultipartFile image,
    		@AuthenticationPrincipal MyUserDetails loggedUser) throws IOException {
     
    	UserDTO updateUser = userService.getUser();
    	updateUser.setFullName(fullName);
    	updateUser.setPassword(password);
    	
    	boolean check = false;
    	if (password.isEmpty()|| password == null || password.length() < 8) {
    		model.addAttribute("passworderr", "Password must be at least 8 characters long");
    		check = true;
    	}
    	
    	if (fullName.isEmpty()|| fullName == null || fullName.length() < 3) {
    		model.addAttribute("fullNameerr", "FullName must be at least 3 characters long");
    		check = true;
    	}
    	
    	if (check) {
    		model.addAttribute("UserDTO", updateUser);
    		return "editprofile";
    	}
    		
    	String idUser = updateUser.getId().toString();
    	String avatarPath = IOFile.saveImage(image, idUser);
    	if (avatarPath != null)
    		updateUser.setAvatar(avatarPath);
    	
    	userRepository.save(Convert.toEntity(updateUser));
    	
    	loggedUser.setAvatar(updateUser.getAvatar());
    	loggedUser.setFullName(updateUser.getFullName());
    	
        return "edit_success";
    }
}
