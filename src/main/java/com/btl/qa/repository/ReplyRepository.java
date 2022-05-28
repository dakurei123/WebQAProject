package com.btl.qa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btl.qa.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	List<Reply> findAllByAnswerId(Long answerId);
	void deleteAllByAnswerId(Long answerId);
}
