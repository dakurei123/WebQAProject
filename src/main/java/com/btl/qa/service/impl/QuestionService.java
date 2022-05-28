package com.btl.qa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btl.qa.dto.QuestionDTO;
import com.btl.qa.entity.Category;
import com.btl.qa.entity.Question;
import com.btl.qa.entity.User;
import com.btl.qa.repository.CategoryRepository;
import com.btl.qa.repository.QuestionRepository;
import com.btl.qa.repository.UserRepository;
import com.btl.qa.service.IQuestionService;
import com.btl.qa.util.Convert;

@Service
public class QuestionService implements IQuestionService {

	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Question save(QuestionDTO questionDTO, Long userId) {
		Question question = Convert.toEntity(questionDTO);
		question.setCategory(categoryRepository.findOneById(questionDTO.getCategory_id()));
		question.setUser(userRepository.findOneById(userId));
		return questionRepository.save(question);
	}

	@Override
	public List<QuestionDTO> getQuestions() {
		List<Question> questions = questionRepository.findAll();
		List<QuestionDTO> dtos = new ArrayList<>();
		if (questions != null) {
			for (int i = questions.size()-1; i >= 0; i--) {
				dtos.add(Convert.toDTO(questions.get(i)));
			}
		}
		return dtos;
	}

	@Override
	public QuestionDTO getQuestion(Long id) {
		Question question = questionRepository.findOne(id);
		QuestionDTO dto = Convert.toDTO(question);
		return dto;
	}

	@Override
	public Boolean checkQuestionId(Long id) {
		Question question = questionRepository.findOne(id);
		if (question == null)
			return false;
		return true;
	}

	@Override
	public List<QuestionDTO> getQuestionsByUser(Long userId) {
		User user = userRepository.findOne(userId);
		List<QuestionDTO> dto = new ArrayList<>();
		if (user == null)
			return dto;
		List<Question> questions = questionRepository.findAllByUserId(userId);
		for (int i = questions.size()-1; i >= 0; i--) {
			dto.add(Convert.toDTO(questions.get(i)));
		}
		return dto;
	}

	@Override
	public List<QuestionDTO> getQuestionsByCategory(Long categoryId) {
		Category category = categoryRepository.findOne(categoryId);
		List<QuestionDTO> dto = new ArrayList<>();
		if (category == null)
			return dto;
		List<Question> questions = questionRepository.findAllByCategoryId(categoryId);
		for (int i = questions.size()-1; i >= 0; i--) {
			dto.add(Convert.toDTO(questions.get(i)));
		}
		return dto;
	}

	@Override
	public List<QuestionDTO> searchQuestion(String key) {
		List<QuestionDTO> dto = new ArrayList<QuestionDTO>();
		List<Question> questions = questionRepository.findByTitleContainingIgnoreCase(key);
		if (questions != null) {
			for (int i = questions.size()-1; i >= 0; i--) {
				dto.add(Convert.toDTO(questions.get(i)));
			}
		}
		return dto;
	}

	@Override
	public List<QuestionDTO> getQuestionByUserAndCategory(Long userId, Long CategoryId) {
		List<QuestionDTO> dto = new ArrayList<QuestionDTO>();
		List<Question> questions = questionRepository.findAllByUserIdAndCategoryId(userId, CategoryId);
		if (questions != null) {
			for (int i = questions.size()-1; i >= 0; i--) {
				dto.add(Convert.toDTO(questions.get(i)));
			}
		}
		return dto;
	}

	@Override
	public List<QuestionDTO> searchQuestionUser(Long userId, String key) {
		List<QuestionDTO> dto = new ArrayList<QuestionDTO>();
		List<Question> questions = questionRepository.findByUserIdAndTitleContainingIgnoreCase(userId, key);
		if (questions != null) {
			for (int i = questions.size()-1; i >= 0; i--) {
				dto.add(Convert.toDTO(questions.get(i)));
			}
		}
		return dto;
	}

}
