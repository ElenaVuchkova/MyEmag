package com.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView register(HttpSession session, Model model) {
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
			session.invalidate();
			model.addAttribute("user", new User()); 
			return new ModelAndView("redirect:/login", "user", new User());
		}
		
		if(session.getAttribute("register") != null){
			session.setAttribute("register", null);
		}
		model.addAttribute("user", new User()); 
		return new ModelAndView("register", "user", new User());
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session){
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
			session.invalidate();
		}
		
		if(result.hasErrors()){
			session.setAttribute("register", "Could not register. Please, enter a valid data!");
		}
		else{
			
			try {
				UserDAO.getInstance().addUser(user);
				session.setAttribute("login", "Successfully registered. You can <a href=\"login\">login</a> now.");
				return "login";
			} catch (SQLException e) {
				session.setAttribute("register", "The user already exists!");
			}
		}
        return "register";
	}
}
