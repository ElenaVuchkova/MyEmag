package com.controller;


import java.io.IOException;
import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String receiveUpload(@RequestParam("failche") MultipartFile multiPartFile, @ModelAttribute Product p,  Model model) throws IOException{
		try {
			ProductDAO.getInstance().addProduct(p);
		} catch (SQLException e) {
			System.out.println("SQL - " + e.getMessage());
		}
		return "index";
	}
	
}
