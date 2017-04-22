package com.controller;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Product;
import com.model.dao.CategoryDAO;
import com.model.dao.ProductDAO;
import com.model.dao.SubcategoryDAO;

@Controller
public class ProductController {

//	@RequestMapping(value="/product", method=RequestMethod.GET)
//	public String createProduct (Model m) {
//		m.addAttribute("product", new Product());
//		return "product";
//	}
//	
//	@RequestMapping(value="/product", method=RequestMethod.POST)
//	public String receiveUpload(@ModelAttribute Product p,HttpSession session) {
//		try {
//			ProductDAO.getInstance().addProduct(p);
//			session.setAttribute("product", p);
//		} catch (SQLException e) {
//			System.out.println("SQL receive upload controller - " + e.getMessage());
//		}
//		return "upload";
//	}
	
	
	
	//view product
	@RequestMapping(value="product/{productId} ",method = RequestMethod.GET)
	public String viewProfile(Model model, @PathVariable("productId") Integer productId, HttpSession session) {			
		try {
			if(ProductDAO.getInstance().getAllProducts().containsKey(productId)) {
				Product p=ProductDAO.getInstance().getProduct(productId);
				model.addAttribute("product",p);
				session.setAttribute("product", p);
				return "productPage";
			} 
			else {
				return "index";
			}				
		} 
		catch (SQLException e) {
			return "404";
		}			
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String indexpage(Model m){		
			
		HashMap<Integer, Product> allProducts=null;
		try {
			allProducts = ProductDAO.getInstance().getAllProducts();
			for (Product p: allProducts.values()){
				System.out.println(p);
			}
			m.addAttribute("allproducts", allProducts);
		} catch (SQLException e) {
			System.out.println("kdxjfd");
		}		
		return "test";
	}	
	
	
}
