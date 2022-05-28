package com.btl.qa.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btl.qa.dto.AnswerDTO;
import com.btl.qa.dto.ReplyDTO;
import com.btl.qa.entity.Answer;
import com.btl.qa.entity.Like;
import com.btl.qa.entity.Reply;
import com.btl.qa.repository.AnswerRepository;
import com.btl.qa.repository.LikeRepository;
import com.btl.qa.repository.QuestionRepository;
import com.btl.qa.repository.ReplyRepository;
import com.btl.qa.repository.UserRepository;
import com.btl.qa.service.IAnswerService;
import com.btl.qa.util.Convert;

@Service
public class AnswerService implements IAnswerService {
	
	@Autowired
	AnswerRepository answerRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	ReplyRepository replyRepository;
	@Autowired
	LikeRepository likeRepository;
	
	@Override
	public List<AnswerDTO> getAnswers(Long questionId) {
		List<AnswerDTO> dtos = new ArrayList<>();
		List<Answer> answers = answerRepository.findAllByQuestionId(questionId);
		if (answers != null) {
			for (int i = answers.size()-1; i >= 0; i--) {
				AnswerDTO dto = Convert.toDTO(answers.get(i));
				List<Like> tmp = likeRepository.findByAnswerId(dto.getId());
				if (tmp == null)
					dto.setLike(0);
				else {
					dto.setLike(tmp.size());
				}
				dtos.add(dto);
			}
			dtos.sort(new Comparator<AnswerDTO>() {
				@Override
				public int compare(AnswerDTO o1, AnswerDTO o2) {
					return o2.getLike()-o1.getLike();
				}
			});
		}
		return dtos;
	}

	@Override
	public AnswerDTO save(AnswerDTO dto) {
		Answer answer = Convert.toEntity(dto);
		answer.setUser(userRepository.findOne(dto.getUserId()));
		answer.setQuestion(questionRepository.findOne(dto.getQuestionId()));
		return Convert.toDTO(answerRepository.save((answer)));
	}

	@Override
	public ReplyDTO save(ReplyDTO dto) {
		Reply reply = Convert.toEntity(dto);
		reply.setUser(userRepository.findOne(dto.getUserId()));
		reply.setAnswer(answerRepository.findOne(dto.getAnswerId()));
		return Convert.toDTO(replyRepository.save(reply));
	}

	@Override
	public boolean deleteAnswer(Long answerId, Long idUser) {
		Answer answer = answerRepository.findOne(answerId);
		if (answer == null)
			return false;
		if (answer.getUser().getId() == idUser || idUser == 1) {
			replyRepository.delete(answer.getReplies());
			answerRepository.delete(answer);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean deleteReply(Long replyId, Long idUser) {
		Reply reply = replyRepository.findOne(replyId);
		if (reply == null)
			return false;
		if (reply.getUser().getId() == idUser || idUser == 1) {
			replyRepository.delete(reply);
			return true;
		}
		return false;
	}

	@Override
	public int getLikeAnswerByUser(Long userId) {
		List<Answer> answers = answerRepository.findByUserId(userId);
		int totalLikeUser = 0;
		if (answers != null) {
			for (Answer answer : answers) {
				List<Like> tmp = likeRepository.findByAnswerId(answer.getId());
				if (tmp != null)
					totalLikeUser += tmp.size();
			}
		}
		return totalLikeUser;
	}

}
