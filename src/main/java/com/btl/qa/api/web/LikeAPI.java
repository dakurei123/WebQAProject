package com.btl.qa.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btl.qa.security.MyUserDetails;
import com.btl.qa.service.IAnswerService;
import com.btl.qa.service.ILikeService;

@RestController
public class LikeAPI {
	@Autowired
	ILikeService likeService;
	@Autowired
	IAnswerService answerService;
	
	@GetMapping("/api/user/like/{userId}") 
	public int getLikeByUser(@PathVariable Long userId) {
		return answerService.getLikeAnswerByUser(userId);
	}
	
	//Total like in answer
	@GetMapping("/api/answer/like/{answerId}")
	public int getLikeByAnswer(@PathVariable Long answerId) {
		return likeService.totalLikeAnswer(answerId);
	}
	
	//Like & Unlike
	@PostMapping("/api/like/{answerId}")
	public boolean Like(@AuthenticationPrincipal MyUserDetails loggedUser, @PathVariable Long answerId) {
		return likeService.like(loggedUser.getId(), answerId);
	}
	
	//Check like
	@GetMapping("/api/like/{answerId}")
	public boolean checkLike(@AuthenticationPrincipal MyUserDetails loggedUser, @PathVariable Long answerId) {
		return likeService.checkLike(loggedUser.getId(), answerId);
	}
}




