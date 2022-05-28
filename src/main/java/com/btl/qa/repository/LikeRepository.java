package com.btl.qa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btl.qa.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Long>{
	List<Like> findByUserId(Long userId);
	List<Like> findByAnswerId(Long answerId);
	Like findByUserIdAndAnswerId(Long userId, Long answerId);
}
