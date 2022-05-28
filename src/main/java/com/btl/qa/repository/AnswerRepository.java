package com.btl.qa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btl.qa.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
	List<Answer> findAllByQuestionId(Long questionId);
	List<Answer> findByUserId(Long userId);
}
