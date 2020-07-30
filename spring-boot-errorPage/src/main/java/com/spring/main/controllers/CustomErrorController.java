package com.spring.main.controllers;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class CustomErrorController implements ErrorController{

	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(response.getStatus() == HttpStatus.BAD_REQUEST.value()) {
			request.setAttribute("errorPage","400");
		}
		else if(response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			request.setAttribute("errorPage","500");
		}
		else {
			request.setAttribute("errorPage","404");
		}
		
		modelAndView.setViewName("error");
		
		return modelAndView;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}