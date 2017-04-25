package com.controller;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.Product;
import com.model.User;
import com.model.dao.CategoryDAO;
import com.model.dao.DBManager;
import com.model.dao.ProductDAO;
import com.model.dao.SubcategoryDAO;
import com.model.dao.UserDAO;


@Controller
public class UserController {		
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String indexpage(Model m, HttpSession session){		
		ArrayList<String> categories=new ArrayList<>();
		ArrayList<String> subcategories=new ArrayList<>();
		HashMap<String, ArrayList<String>> catAndSubcat=new HashMap<>();
		try {
			categories=CategoryDAO.getInstance().getAllCategories();
			for(String c: categories){
				if (!catAndSubcat.containsKey(c)) {
					catAndSubcat.put(c, new ArrayList<>());
				}
				subcategories=SubcategoryDAO.getInstance().getAllSubcategoryByCategory(c);
				for(String s:subcategories){
					catAndSubcat.get(c).add(s);
				}				
			}
			m.addAttribute("catAndSubcat", catAndSubcat);		
			session.setAttribute("catAndSubcat", catAndSubcat);
			HashMap<Integer, Product> allProducts= ProductDAO.getInstance().getAllProducts();
			m.addAttribute("allproducts", allProducts);
			session.setAttribute("allproducts", allProducts);
			System.out.println("vsichki produkti za index page");
			for (Product p: allProducts.values()){
				System.out.println(p);
			}
		} catch (SQLException e) {
			System.out.println("sql index controller"+e.getMessage());
		}
		return "index";
	}	
	
	//login	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginPage(Model model, HttpSession session) {
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
			return new ModelAndView("index", "user", new User());
		}
		return new ModelAndView("login", "user", new User());
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute("user") User userHelper, HttpSession session) {	
		try {
			String username=userHelper.getUsername();
			String password=userHelper.getPassword();
			if(UserDAO.getInstance().validLogin(username, password)){
				User u=UserDAO.getInstance().getUser(username);
				session.setAttribute("user", u);
				session.setAttribute("username", username);
				session.setAttribute("logged", true);	
				return "index";
			}
			else{
				session.setAttribute("logged", false);
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
				session.setAttribute("register", "Successfully register! Please, login!");			
				return "login";
			} catch (SQLException e) {
				session.setAttribute("register", "The user already exists!");
				System.out.println(e.getMessage());
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
	
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test() {
		return "test";
	}
}
