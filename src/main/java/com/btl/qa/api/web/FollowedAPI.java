package com.btl.qa.api.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btl.qa.dto.FollowedDTO;
import com.btl.qa.security.MyUserDetails;
import com.btl.qa.service.IFollowedService;

@RestController
public class FollowedAPI {
	@Autowired 
	IFollowedService followedService;
	
	@GetMapping("/api/user/follow")
	public List<FollowedDTO> getFollowed(@AuthenticationPrincipal MyUserDetails loggedUser){
		if (loggedUser == null)
			return null;
		return followedService.getListFollowed(loggedUser.getId());
	}
	
	@PostMapping("/api/user/follow/{followedId}")
	public boolean followUser(@PathVariable Long followedId, @AuthenticationPrincipal MyUserDetails loggedUser) {
		return followedService.follow(loggedUser.getId(), followedId);
	}
	
	@GetMapping("/api/user/follow/{followedId}")
	public boolean checkFollow(@PathVariable Long followedId, @AuthenticationPrincipal MyUserDetails loggedUser) {
		return followedService.checkFollowed(loggedUser.getId(), followedId);
	}
	
}
