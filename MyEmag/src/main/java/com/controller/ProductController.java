package com.controller;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.model.Product;
import com.model.Review;
import com.model.User;
import com.model.dao.ProductDAO;
import com.model.dao.ReviewDAO;
import com.model.dao.SubcategoryDAO;
import com.model.dao.UserDAO;

@Controller
public class ProductController {
	
	//view product
	@RequestMapping(value="product/{productId}",method = RequestMethod.GET)
	public static String viewProduct (Model model, @PathVariable(value="productId") Integer productId, HttpSession session) {
		try {
			if(ProductDAO.getInstance().getAllProducts().containsKey(productId)) {
				Product p=ProductDAO.getInstance().getProduct(productId);
				model.addAttribute("product",p);
				session.setAttribute("product", p);
				return "product";
			} 
			else {
				return "index";
			}				
		} 
		catch (SQLException e) {
			return "404";
		}			
	}
	
	//view review
		@RequestMapping(value="/product/{productId}/review",method = RequestMethod.GET)
		public String review (@PathVariable(value="productId") Integer productId) {		
			System.out.println("find review jsp++++++++++++++");
			return "review";
		}		

		

		@RequestMapping(value = "/product/{productId}/review", method = RequestMethod.POST)
		public String addReview(@PathVariable(value="productId") Integer productId, HttpSession session, HttpServletRequest req, Model model){
			if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
				try {
					if(ProductDAO.getInstance().getAllProducts().containsKey(productId)) {
						String comment=req.getParameter("comment");
						int rating=Integer.parseInt(req.getParameter("rating"));
						String username=(String) session.getAttribute("username");
						User user=UserDAO.getInstance().getUser(username);
						Product product=ProductDAO.getInstance().getProduct(productId);
						Review r=new Review(comment, rating, user, product, LocalDateTime.now());
						ReviewDAO.getInstance().addReview(r);
						return viewProduct(model, productId, session);
						}
				} catch (NumberFormatException | SQLException e) {
					System.out.println("SQL" + e.getMessage());
					return "404";
				}
				return "index";
				}
			return "login";
			}
		
		@RequestMapping(value="/{subcategory}", method = RequestMethod.GET)
		public String viewProductsBySubcategory (Model model, @PathVariable(value="subcategory") String subcategory, HttpSession session) {
			try {
				if (SubcategoryDAO.getInstance().isSubcategory(subcategory)) {
					ArrayList<Product> allProductsBySubcategorySortedByDate=ProductDAO.getInstance().getAllProductsBySubcategorySortedByDate(subcategory);
					model.addAttribute("products",allProductsBySubcategorySortedByDate);
					return "allProductsBySubcategory";
				} 
				else {
					return "index";
				}				
			} 
			catch (SQLException e) {
				System.out.println("SQL - allProductsBySubcategory" + e.getMessage());
				return "404";
			}			
		}
		
		@RequestMapping(value="/{subcategory}", method = RequestMethod.POST)
		public String viewProductsBySubcategoryOrderByParam (Model model, @PathVariable(value="subcategory") String subcategory,
				HttpServletRequest req, HttpSession session) {
			System.out.println("assdadsfdsfdsdsdsdgdfgdf");
			try {
				if (SubcategoryDAO.getInstance().isSubcategory(subcategory)) {
					String param=req.getParameter("param");
					if (param.equals("price desc.")) {
						ArrayList<Product> allProductsBySubcategorySortedByPriceDesc=ProductDAO.getInstance().getAllProductsBySubcategorySortedByPriceDesc(subcategory);
						model.addAttribute("products",allProductsBySubcategorySortedByPriceDesc);
						return "allProductsBySubcategory";
					}
					if (param.equals("price asc.")) {
						ArrayList<Product> allProductsBySubcategorySortedByPriceAsc=ProductDAO.getInstance().getAllProductsBySubcategorySortedByPriceAsc(subcategory);
						model.addAttribute("products",allProductsBySubcategorySortedByPriceAsc);
						return "allProductsBySubcategory";
					}
					if (param.equals("most reviews")) {
						TreeSet<Product> allProductsBySubcategorySortedByMostReviews=ProductDAO.getInstance().getAllProductsBySubcategorySortedByMostReviews(subcategory);
						model.addAttribute("products",allProductsBySubcategorySortedByMostReviews);
						return "allProductsBySubcategory";
					}
					if (param.equals("sale desc.")) {
						ArrayList<Product> allProductsBySubcategorySortedBySaleDesc=ProductDAO.getInstance().getAllProductsBySubcategorySortedBySaleDesc(subcategory);
						model.addAttribute("products",allProductsBySubcategorySortedBySaleDesc);
						return "allProductsBySubcategory";
					}
					if (param.equals("date")) {
						ArrayList<Product> allProductsBySubcategorySortedByDate=ProductDAO.getInstance().getAllProductsBySubcategorySortedByDate(subcategory);
						model.addAttribute("products",allProductsBySubcategorySortedByDate);
						return "allProductsBySubcategory";
					}
				}
				model.addAttribute("select", "Please select your option.");
				return "allProductsBySubcategory";
			} 
			catch (SQLException e) {
				System.out.println("SQL - allProductsBySubcategory" + e.getMessage());
				return "404";
			}			
		}
}
