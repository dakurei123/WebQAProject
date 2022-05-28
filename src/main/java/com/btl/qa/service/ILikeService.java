package com.btl.qa.service;

public interface ILikeService {
	boolean like(Long userId, Long answerId);
	boolean checkLike(Long userId, Long answerId);
	int totalLikeAnswer(Long answerId);
	int totalLikeUser(Long userId);
}
