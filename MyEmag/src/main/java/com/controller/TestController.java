package com.controller;



import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

	@RequestMapping(value="/test", method = RequestMethod.GET)
	public String viewTest (Model model, HttpSession session) {		
		return "test";		
	}
}
