package com.btl.qa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.btl.qa.dto.UserDTO;
import com.btl.qa.entity.User;
import com.btl.qa.repository.UserRepository;
import com.btl.qa.service.IUserService;
import com.btl.qa.util.Convert;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository ur;
	
	@Override
	public UserDTO getUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();	
		return Convert.toDTO(ur.findOneByUsername(username));
	}
	
	@Override
	public UserDTO getUser(Long userId) {
		return Convert.toDTO(ur.findOne(userId));
	}
	
	@Override
	public boolean isExisted(String username) {
		if (ur.findOneByUsername(username) != null) {
			return true;
		}
		return false;
	}

	@Override
	public User save(UserDTO user) {
		user.setRole("ROLE_USER");
		user.setStatus(1);
		user.setAvatar("1\\User.png");
		return ur.save(Convert.toEntity(user));
	}
}
