package com.btl.qa.api.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btl.qa.dto.UserDTO;
import com.btl.qa.entity.User;
import com.btl.qa.repository.UserRepository;
import com.btl.qa.service.IAnswerService;
import com.btl.qa.util.Convert;

@RestController
public class AdminUserAPI {
	@Autowired
	UserRepository userRepository;
	@Autowired
	IAnswerService answerService;
	
	@GetMapping("admin/api/users")
	public List<UserDTO> getUsers() {
		List<UserDTO> dto = new ArrayList<>();
		List<User> users = userRepository.findAll();
		for (User user : users) {
			UserDTO tmp = Convert.toDTO(user);
			tmp.setLike(answerService.getLikeAnswerByUser(tmp.getId()));
			dto.add(tmp);
		}
		return dto;
	}
	
	@PutMapping("admin/api/user/lock/{id}")
	public Integer lockUser(@PathVariable Long id) {
		if (id != 1) {
			User user = userRepository.findOne(id);
			if (user.getStatus() == 1)
				user.setStatus(0);
			else {
				user.setStatus(1);
			}
			userRepository.save(user);
			return user.getStatus();
		}
		return null;
	}
	
	@PutMapping("admin/api/user/role/{id}")
	public String changeRoleUser(@PathVariable Long id) {
		if (id != 1) {
			User user = userRepository.findOne(id);
			if (user.getRole().equalsIgnoreCase("ROLE_USER"))
				user.setRole("ROLE_ADMIN");
			else {
				user.setRole("ROLE_USER");
			}
			userRepository.save(user);
			return user.getRole();
		}
		return null;
	}
}
