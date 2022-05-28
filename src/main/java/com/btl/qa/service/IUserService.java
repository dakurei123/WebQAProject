package com.btl.qa.service;

import com.btl.qa.dto.UserDTO;
import com.btl.qa.entity.User;

public interface IUserService {
	boolean isExisted(String username);;
	User save(UserDTO user);
	UserDTO getUser(Long userId);
	UserDTO getUser();
}
