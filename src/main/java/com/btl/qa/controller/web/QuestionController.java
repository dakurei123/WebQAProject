package com.btl.qa.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import com.btl.qa.dto.QuestionDTO;
import com.btl.qa.service.ICategoryService;
import com.btl.qa.service.IQuestionService;

import javassist.NotFoundException;

@Controller
public class QuestionController {
	
	@Autowired
	IQuestionService questionService;
	
	@Autowired
	ICategoryService categoryService;
	
	RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8081/api/question/";
	
	@GetMapping("question/{raw_id}")
	@PreAuthorize("isAuthenticated()")
	public String getQuestion(Model model, @PathVariable String raw_id) throws NotFoundException {
		Long id = null;
		try {
			id = Long.parseLong(raw_id);
		} catch (NumberFormatException e) {
			throw new NotFoundException("This question not found");
		}
		
		if (!questionService.checkQuestionId(id))
			throw new NotFoundException("This question not found");
		QuestionDTO dto = restTemplate.getForObject(url + id, QuestionDTO.class);
		model.addAttribute("question", dto);
		model.addAttribute("categories", categoryService.getCategories());
		return "question";
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public String TodoException(NotFoundException ex, Model model) {
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("code", "404");
		return "error";
	}
}
