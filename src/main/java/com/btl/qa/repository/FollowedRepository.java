package com.btl.qa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btl.qa.entity.Followed;

public interface FollowedRepository extends JpaRepository<Followed, Long>{
	Followed findOneByUserIdAndUserFollowedId(Long userId, Long UserFollowedId);
	List<Followed> findAllByUserId(Long userId);
}
