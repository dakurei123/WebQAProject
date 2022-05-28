package com.btl.qa.api.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.btl.qa.dto.AnswerDTO;
import com.btl.qa.dto.ReplyDTO;
import com.btl.qa.security.MyUserDetails;
import com.btl.qa.service.IAnswerService;

@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080", "http://127.0.0.1:5500"})
public class CommentAPI {
	
	@Autowired
	IAnswerService answerService;
	
	@GetMapping("/api/answer/{questionId}")
	public List<AnswerDTO> getAnswers(@PathVariable Long questionId){
		return answerService.getAnswers(questionId);
	}
	
	@PostMapping("/api/answer")
	public AnswerDTO addAnswer(@RequestBody AnswerDTO dto, @AuthenticationPrincipal MyUserDetails loggedUser) {
		dto.setUserId(loggedUser.getId());
		return answerService.save(dto);
	}
	
	@PostMapping("/api/reply")
	public ReplyDTO addReply(@RequestBody ReplyDTO dto, @AuthenticationPrincipal MyUserDetails loggedUser) {
		dto.setUserId(loggedUser.getId());
		return answerService.save(dto);
	}
	
	@DeleteMapping("/api/answer/{answerId}")
	public boolean deleteAnswer(@PathVariable Long answerId, @AuthenticationPrincipal MyUserDetails loggedUser) {
		return answerService.deleteAnswer(answerId, loggedUser.getId());
	}
	
	@DeleteMapping("/api/reply/{replyId}")
	public boolean deleteReply(@PathVariable Long replyId, @AuthenticationPrincipal MyUserDetails loggedUser) {
		return answerService.deleteReply(replyId, loggedUser.getId());
	}
}
