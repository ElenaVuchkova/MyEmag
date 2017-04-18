package com.controller;



import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.User;
import com.model.dao.UserDAO;


@Controller
public class UserController {
		
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String indexpage(){
		return "index";
	}	
	
	//login	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginpage( Model model, HttpSession session){
		//session.setAttribute("user", new User());
		model.addAttribute("user", new User()); 	
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute("user") User user, HttpSession session, HttpServletRequest req) {	
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		try {
			if(UserDAO.getInstance().validLogin(user.getUsername(), user.getPassword())){
				session.setAttribute("username", user.getUsername());
				session.setAttribute("logged", true);	
				return "index";
			}
			else{
				session.setAttribute("login", "Could not login. Please, enter a valid username and password!");
			}
		} catch (SQLException e) {
			System.out.println("Error loging in - " + e.getMessage());			
		}
		return "login";
	}
	
	//register
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView register(HttpSession session, Model model) {
		
		//model.addAttribute("user", new User()); 
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
				return "login";
			} catch (SQLException e) {
				session.setAttribute("register", "The user already exists!");
			}
		}
        return "register";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session, Model model) {
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
			session.invalidate();
		}
		return "index";
	}
}
