package com.btl.qa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btl.qa.dto.FollowedDTO;
import com.btl.qa.entity.Followed;
import com.btl.qa.repository.FollowedRepository;
import com.btl.qa.repository.UserRepository;
import com.btl.qa.service.IFollowedService;
import com.btl.qa.util.Convert;

@Service
public class FollowedService implements IFollowedService{

	@Autowired
	FollowedRepository followedRepository;
	@Autowired
	UserRepository userRepository;
	@Override
	public List<FollowedDTO> getListFollowed(Long userId) {
		List<Followed> followeds = followedRepository.findAllByUserId(userId);
		List<FollowedDTO> dtos = new ArrayList<FollowedDTO>();
		if (followeds == null)
			return dtos;
		for(Followed followed : followeds) {
			dtos.add(Convert.toDTO(followed));
		}
		return dtos;
	}

	@Override
	public boolean checkFollowed(Long userId, Long followedId) {
		Followed followed = followedRepository.findOneByUserIdAndUserFollowedId(userId, followedId);
		if (followed == null)
			return false;
		return true;
	}

	@Override
	public boolean follow(Long userId, Long followedId) {
		Followed followed = followedRepository.findOneByUserIdAndUserFollowedId(userId, followedId);
		if (followed != null) {
			followedRepository.delete(followed);
			return false;
		}
		followed = new Followed();
		followed.setUser(userRepository.findOne(userId));
		followed.setUserFollowed(userRepository.findOne(followedId));
		followedRepository.save(followed);
		return true;
	}
}
