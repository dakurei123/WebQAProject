package com.btl.qa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btl.qa.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{
	List<Question> findAllByUserId(Long userId);
	List<Question> findAllByCategoryId(Long categoryId);
	List<Question> findByTitleContainingIgnoreCase(String title);
	List<Question> findAllByUserIdAndCategoryId(Long userId, Long CategoryId);
	List<Question> findByUserIdAndTitleContainingIgnoreCase(Long userId, String key);
}
