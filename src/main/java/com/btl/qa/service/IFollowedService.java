package com.btl.qa.service;

import java.util.List;

import com.btl.qa.dto.FollowedDTO;

public interface IFollowedService {
	List<FollowedDTO> getListFollowed(Long userId);
	boolean checkFollowed(Long userId, Long followedId);
	boolean follow(Long userId, Long followedId);	
}
