package com.controller;
import java.sql.SQLException;
import java.time.LocalDateTime;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.Product;
import com.model.Review;
import com.model.User;
import com.model.dao.FavouriteProductsDAO;
import com.model.dao.ProductDAO;
import com.model.dao.ReviewDAO;
import com.model.dao.SubcategoryDAO;
import com.model.dao.UserDAO;

@Controller
public class ProductController {
	public static final String DATE="date";
	public static final String PRICE_ASC="price asc.";
	public static final String PRICE_DESC="price desc.";
	public static final String SALE_DESC="sale desc.";
	public static final String MOST_REVIEWS="most reviews";
	
	//view product
	@RequestMapping(value="product/{productId}",method = RequestMethod.GET)
	public static ModelAndView viewProduct (Model model, @PathVariable(value="productId") Integer productId, HttpSession session) {
		try {
			if(ProductDAO.getInstance().getAllProducts().containsKey(productId)) {
				Product p=ProductDAO.getInstance().getProduct(productId);
				model.addAttribute("product",p);
				session.setAttribute("product", p);
				return new ModelAndView("product");
			} 
			else {
				return new ModelAndView("index");
			}				
		} 
		catch (SQLException e) {
			return new ModelAndView("404");
		}			
	}
	
	//TODO
	//view review
		@RequestMapping(value="/product/{productId}/review",method = RequestMethod.GET)
		public ModelAndView review (@PathVariable(value="productId") Integer productId, HttpSession session) {
			if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
				return new ModelAndView("review");
			}
			return new ModelAndView("login", "user", new User());
		}		

		

		@RequestMapping(value = "/product/{productId}/review", method = RequestMethod.POST)
		public ModelAndView addReview(@PathVariable(value="productId") Integer productId, HttpSession session, HttpServletRequest req, Model model){
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
					return new ModelAndView("404");
				}
				return new ModelAndView("index");
				}
			return new ModelAndView("login", "user", new User());
			}
		
		@RequestMapping(value="/{subcategory}", method = RequestMethod.GET)
		public static ModelAndView viewProductsBySubcategory (Model model, @PathVariable(value="subcategory") String subcategory, HttpSession session) {
			try {
				if (SubcategoryDAO.getInstance().isSubcategory(subcategory)) {
					ProductDAO pDao=ProductDAO.getInstance();
					HashSet<Product> allProductsBySubcategory=pDao.getAllProductsBySubcategory(subcategory);
					model.addAttribute("products",allProductsBySubcategory);
					model.addAttribute("sortedProducts", null);
					return new ModelAndView("allProductsBySubcategory");
				} 
				else {
					return new ModelAndView("index");
				}				
			} 
			catch (SQLException e) {
				System.out.println("SQL - allProductsBySubcategory" + e.getMessage());
				return new ModelAndView("404");
			}			
		}
		
		
		@RequestMapping(value="/{subcategory}", method = RequestMethod.POST)
		public String viewProductsBySubcategoryOrderByParam (Model model, @PathVariable(value="subcategory") String subcategory,
				HttpServletRequest req, HttpSession session) {
			System.out.println("assdadsfdsfdsdsdsdgdfgdf");
			try {
				if (SubcategoryDAO.getInstance().isSubcategory(subcategory)) {
					ProductDAO pDao=ProductDAO.getInstance();
					HashSet<Product> allProductsBySubcategory=pDao.getAllProductsBySubcategory(subcategory);
					String param=req.getParameter("param");
					TreeSet<Product> allProductsBySubcategorySorted=null;
					Comparator<Product> comp=null;
					switch (param) {
					case PRICE_DESC:
						comp=new Comparator<Product>() {
							@Override
							public int compare(Product o1, Product o2) {
								double razlika= o1.getPrice()-o2.getPrice();
								if (razlika>0) {
									return -1;
								}
								if (razlika<0) {
									return 1;
								}
								return 0;
							}
						};
						break;
					case PRICE_ASC:
						comp=new Comparator<Product>() {
							@Override
							public int compare(Product o1, Product o2) {
								double razlika= o1.getPrice()-o2.getPrice();
								if (razlika>0) {
									return 1;
								}
								if (razlika<0) {
									return -1;
								}
								return 0;
							}
						};
						break;
					case MOST_REVIEWS:
						comp=new Comparator<Product>() {
							@Override
							public int compare(Product o1, Product o2) {
								int size1=0;
								int size2=0;
								if (o1.getReviews()!=null) {
									size1=o1.getReviews().size();
								}
								if (o2.getReviews()!=null) {
									size2=o2.getReviews().size();
								}
								return size2-size1;
							}
						};
						break;
					case SALE_DESC:
						comp=new Comparator<Product>() {
							@Override
							public int compare(Product o1, Product o2) {
								double razlika1=o1.getPrice()-o1.getSalePrice();
								double razlika2=o2.getPrice()-o2.getSalePrice();
								double razlika=razlika1-razlika2;
								if (razlika>0) {
									return -1;
								}
								return 1;
							}
						};
						break;
					case DATE:
						comp=new Comparator<Product>() {
							@Override
							public int compare(Product o1, Product o2) {
								return o1.getProductId()-o2.getProductId()*(-1);
							}
						};
						break;
					default:
						model.addAttribute("select", "Please select your option.");
						break;
					}
					if (comp!=null) {
						allProductsBySubcategorySorted=new TreeSet<Product>(comp);
						for (Product p: allProductsBySubcategory) {
							allProductsBySubcategorySorted.add(p);
						}
						model.addAttribute("sortedProducts", allProductsBySubcategorySorted);
					}
				}
				return "allProductsBySubcategory";
			} 
			catch (SQLException e) {
				System.out.println("SQL - allProductsBySubcategory" + e.getMessage());
				return "404";
			}			
		}
		
		@RequestMapping(value="/product/{productId}/addToCart",method = RequestMethod.POST)
		public ModelAndView addToCart (@PathVariable(value="productId") Integer productId,HttpSession session, Model model) {		
			if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
				HashMap<Product, Integer> productsInCart=(HashMap<Product, Integer>) session.getAttribute("cart");
				try {
					if(ProductDAO.getInstance().getAllProducts().containsKey(productId)) {
						Product p=ProductDAO.getInstance().getProduct(productId);
						productsInCart.put(p,1);
						return viewProduct(model, productId, session);
					}
				} catch (SQLException e) {
					System.out.println("SQL add products to cart " + e.getMessage());
					return new ModelAndView("404");
				}
			}
			return new ModelAndView("login", "user", new User());
		}
		

		@RequestMapping(value="/product/{productId}/addToWishList",method = RequestMethod.POST)
		public ModelAndView addToWishList (@PathVariable(value="productId") Integer productId,HttpSession session, Model model) {		
			if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
				User u=(User) session.getAttribute("user");
				int userId=u.getUserId();
				try {
					if(ProductDAO.getInstance().getAllProducts().containsKey(productId)) {
						FavouriteProductsDAO.getInstance().addFavouriteProduct(userId, productId);
						return viewProduct(model, productId, session);
					}
				} catch (SQLException e) {
					System.out.println("SQL add to favourite products " + e.getMessage());
					model.addAttribute("message","Your wishlist contains this product!");
					return viewProduct(model, productId, session);
				}
			}
			return new ModelAndView("login", "user", new User());
		}
		
		@RequestMapping (value="/search", method=RequestMethod.GET)
		public String searchProducts(HttpServletRequest request,Model model ) {
			String keyword = request.getParameter("keyword").toLowerCase().trim();
			String[] keywords = keyword.split(" ");
			HashSet<Product> searchResult = new HashSet<>();
			try {
				searchResult=ProductDAO.getInstance().searchProduct(keywords);
			} catch (SQLException e) {
				System.out.println("SQL- search product- " + e.getMessage());
				return "404";
			}
			model.addAttribute("searchResult", searchResult.size() > 0?searchResult:null);
			return "search";
		}
		
	}
