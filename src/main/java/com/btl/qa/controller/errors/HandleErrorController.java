package com.btl.qa.controller.errors;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpServerErrorException;

@Controller
public class HandleErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			model.addAttribute("code",statusCode);
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				model.addAttribute("message", "Page not found. Click OK to go home");
			} else if (statusCode == HttpStatus.FORBIDDEN.value()) {
				model.addAttribute("message", "You don't have permission to access page");
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				model.addAttribute("message", "Lỗi chưa xác định");
				System.out.println(HttpServerErrorException.class);
			}
		}
		return "error";
	}

	
	
	@Override
	public String getErrorPath() {

		return "/error";
	}

	
	
}
