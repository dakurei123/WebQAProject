package com.btl.qa.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.btl.qa.entity.Answer;
import com.btl.qa.entity.Question;
import com.btl.qa.repository.AnswerRepository;
import com.btl.qa.repository.LikeRepository;
import com.btl.qa.repository.QuestionRepository;
import com.btl.qa.repository.ReplyRepository;

@RestController
public class AdminQuestionAPI {
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	AnswerRepository answerRepository;
	@Autowired
	ReplyRepository replyRepository;
	@Autowired
	LikeRepository likeRepository;
	
	@DeleteMapping("/api/question/{questionId}")
	public boolean deleteQuestion(@PathVariable Long questionId) {
		Question question = questionRepository.findOne(questionId);
		if (question != null) {
			List<Answer> answers = (List<Answer>) question.getAnswers();
			for (Answer answer : answers) {
				replyRepository.delete(answer.getReplies());
				likeRepository.delete(answer.getLikes());
			}
			answerRepository.delete(answers);
			questionRepository.delete(question);
			return true;
		}
		return false;
	}
}








