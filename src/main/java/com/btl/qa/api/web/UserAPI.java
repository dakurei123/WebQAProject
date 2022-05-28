package com.btl.qa.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.btl.qa.dto.NumberQADTO;
import com.btl.qa.dto.UserDTO;
import com.btl.qa.entity.User;
import com.btl.qa.repository.UserRepository;
import com.btl.qa.security.MyUserDetails;
import com.btl.qa.service.IAnswerService;
import com.btl.qa.util.Convert;

@RestController
public class UserAPI {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	IAnswerService answerService;
	
	@GetMapping("/user")
	public UserDTO getUser(@AuthenticationPrincipal MyUserDetails loggedUser) {
		return Convert.toDTO(userRepository.findOne(loggedUser.getId()));
	}
	
	@GetMapping("/api/user/info/{id}")
	public NumberQADTO getNumberQA(@PathVariable Long id) {
		User user = userRepository.findOne(id);
		NumberQADTO num = new NumberQADTO();
		if (user == null) {
			num.setLike(0);
			num.setNumberAnswers(0);
			num.setNumberQuestions(0);
			return num;
		}
		num.setLike(answerService.getLikeAnswerByUser(id));
		num.setNumberAnswers(user.getAnswers().size());
		num.setNumberQuestions(user.getQuestions().size());
		return num;
	}
}
