package com.btl.qa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btl.qa.entity.Like;
import com.btl.qa.repository.AnswerRepository;
import com.btl.qa.repository.LikeRepository;
import com.btl.qa.repository.UserRepository;
import com.btl.qa.service.ILikeService;

@Service
public class LikeService implements ILikeService{

	@Autowired
	LikeRepository likeRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AnswerRepository answerRepository;
	
	@Override
	public boolean like(Long userId, Long answerId) {
		Like like = likeRepository.findByUserIdAndAnswerId(userId, answerId);
		if (like == null) {
			like = new Like();
			like.setUser(userRepository.findOne(userId));
			like.setAnswer(answerRepository.findOne(answerId));
			likeRepository.save(like);
			return true;
		}
		likeRepository.delete(like);
		return false;
	}

	@Override
	public boolean checkLike(Long userId, Long answerId) {
		Like like = likeRepository.findByUserIdAndAnswerId(userId, answerId);
		if (like == null)
			return false;
		return true;
	}

	@Override
	public int totalLikeAnswer(Long answerId) {
		List<Like> tmp = likeRepository.findByAnswerId(answerId);
		if (tmp == null)
			return 0;
		else {
			return tmp.size();
		}
	}

	@Override
	public int totalLikeUser(Long userId) {
		List<Like> tmp = likeRepository.findByUserId(userId);
		if (tmp == null)
			return 0;
		else {
			return tmp.size();
		}
	}

}
