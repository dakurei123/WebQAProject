package com.btl.qa.api.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NoPermissionException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.btl.qa.controller.errors.nullException;
import com.btl.qa.dto.QuestionDTO;
import com.btl.qa.entity.Question;
import com.btl.qa.security.MyUserDetails;
import com.btl.qa.service.IQuestionService;
import com.btl.qa.util.auth;

@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080", "http://127.0.0.1:5500"})
public class QuestionAPI {

	@Autowired
	IQuestionService questionService;
	
	@GetMapping("/api/question")
	public List<QuestionDTO> getQuestions(@RequestParam int page){
		return questionService.getQuestions();
	}
	
	@GetMapping("/api/user/question/{id}")
	public List<QuestionDTO> getQuestionByUser(@PathVariable Long id, @RequestParam int page){
		return questionService.getQuestionsByUser(id);
	}
	
	@GetMapping("/api/user/question/category")
	public List<QuestionDTO> getQuestionsByUserAndCategory(@RequestParam Long userId, @RequestParam Long categoryId, @RequestParam int page){
		return questionService.getQuestionByUserAndCategory(userId, categoryId);
	}
	
	@GetMapping("/api/user/question/search/")
	public List<QuestionDTO> searchQuestionUser(@RequestParam Long userId, @RequestParam String key, @RequestParam int page){
		return questionService.searchQuestionUser(userId, key);
	}
	
	@GetMapping("/api/question/{id}")
	public QuestionDTO getQuestion(@PathVariable Long id){
		return questionService.getQuestion(id);
	}

	@GetMapping("/api/question/category/{id}")
	public List<QuestionDTO> getQuestionByCategory(@PathVariable Long id, @RequestParam int page){
		return questionService.getQuestionsByCategory(id);
	}
	
	@GetMapping("/api/question/search")
	public List<QuestionDTO> SearchQuestion(@RequestParam String key, @RequestParam int page){
		return questionService.searchQuestion(key);
	}
	
	@PostMapping("/api/question")
	public ResponseEntity<Long> addQuestion(@Valid @RequestBody QuestionDTO questionDTO,
//			@RequestParam MultipartFile image,
			@AuthenticationPrincipal MyUserDetails loggedUser) throws NoPermissionException, nullException {

		if (!auth.isLogged())
			throw new NoPermissionException();
		if (questionDTO.getContent().equalsIgnoreCase("<p><br></p>"))
			throw new nullException();
		Question question = questionService.save(questionDTO, loggedUser.getId());

		return ResponseEntity.ok(question.getId());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(NoPermissionException.class)
	public String TodoException(NoPermissionException ex) {
		return "You are not logged in!";
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(nullException.class)
	public String TodoException(nullException ex) {
		return "Content is required";
	}
}
