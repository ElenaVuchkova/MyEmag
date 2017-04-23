package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReviewController {
	
	@RequestMapping(value="/review", method=RequestMethod.GET)
	public String view (Model m) {
		return "review";
	}

}
