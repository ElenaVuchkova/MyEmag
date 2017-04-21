package com.controller;



import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.model.Product;
import com.model.dao.ProductDAO;

@Controller
public class ProductController {

	@RequestMapping(value="/product", method=RequestMethod.GET)
	public String createProduct (Model m) {
		m.addAttribute("product", new Product());
		return "product";
	}
	
	@RequestMapping(value="/product", method=RequestMethod.POST)
	public String receiveUpload(@ModelAttribute Product p,HttpSession session) {
		try {
			ProductDAO.getInstance().addProduct(p);
			session.setAttribute("product", p);
		} catch (SQLException e) {
			System.out.println("SQL receive upload controller - " + e.getMessage());
		}
		return "upload";
	}
	
}
