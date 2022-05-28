package com.btl.qa.service;

import java.util.List;

import com.btl.qa.dto.AnswerDTO;
import com.btl.qa.dto.ReplyDTO;

public interface IAnswerService {
	List<AnswerDTO> getAnswers(Long id);
	int getLikeAnswerByUser(Long userId);
	AnswerDTO save(AnswerDTO dto);
	ReplyDTO save (ReplyDTO dto);
	boolean deleteAnswer(Long answerId, Long idUser);
	boolean deleteReply(Long replyId, Long idUser);
}
