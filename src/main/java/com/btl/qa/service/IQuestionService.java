package com.btl.qa.service;

import java.util.List;

import com.btl.qa.dto.QuestionDTO;
import com.btl.qa.entity.Question;

public interface IQuestionService {
	Question save(QuestionDTO questionDTO, Long userId);
	List<QuestionDTO> getQuestions();
	QuestionDTO getQuestion(Long id);
	Boolean checkQuestionId(Long id);
	List<QuestionDTO> getQuestionsByUser(Long userId);
	List<QuestionDTO> getQuestionsByCategory(Long categoryId);
	List<QuestionDTO> searchQuestion(String key);
	List<QuestionDTO> getQuestionByUserAndCategory(Long userId, Long categoryId);
	List<QuestionDTO> searchQuestionUser(Long userId, String key);
}
