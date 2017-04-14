package com.controller;

import java.sql.SQLException;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.User;
import com.model.dao.UserDAO;




@Controller
public class UserController {
	
	//login	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginpage(){
		return "login";
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String indexpage(){
		return "index";
	}	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model viewModel, HttpSession s, HttpServletRequest req) {	
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		boolean validData = true;
		if (username == null || username.trim().isEmpty() || password == null ||password.trim().isEmpty()) {
				validData=false;
		}
		if(validData){
			s.setAttribute("username", username);
			s.setAttribute("logged", true);	
		}
		return "index";
	}
	
	//register
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute(new User());		
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute User user, Model model){
		//save to db
		try {
			UserDAO.getInstance().addUser(user);
		} catch (SQLException e) {
			System.out.println("register sql exception"+e.getMessage());
		}
		//redirect to success.jsp	
		return "login";
	}

}
