package com.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.model.Product;
import com.model.User;
import com.model.dao.CategoryDAO;
import com.model.dao.EmailSender;
import com.model.dao.ProductDAO;
import com.model.dao.SubcategoryDAO;
import com.model.dao.UserDAO;

@Controller
@MultipartConfig
public class ChangeProductController {
	private static final String FILE_LOCATION = "C:\\Users\\Elena\\Desktop\\EmagImages\\";
	//private static final String FILE_LOCATION = "C:\\Users\\hp\\Desktop\\EmagImages\\";
	
	
	
	@RequestMapping(value="/addProduct", method=RequestMethod.GET)
	public ModelAndView createProduct (Model m, HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged") && user.getRole()==0){
			ArrayList<String> categories=new ArrayList<>();
			ArrayList<String> subcategories=new ArrayList<>();
			HashMap<String, ArrayList<String>> catAndSubcat=new HashMap<>();
			try {
				categories=CategoryDAO.getInstance().getAllCategories();
				for(String c: categories){
					System.out.println(c);
					if (!catAndSubcat.containsKey(c)) {
						catAndSubcat.put(c, new ArrayList<>());
					}
					subcategories=SubcategoryDAO.getInstance().getAllSubcategoryByCategory(c);
					for(String s:subcategories){
						catAndSubcat.get(c).add(s);
					}					
				}
				m.addAttribute("catAndSubcat", catAndSubcat);
			} catch (SQLException e) {
				System.out.println("sql index controller"+e.getMessage());
				return new ModelAndView("404");
			}
			return new ModelAndView("addProduct");
		}
		return new ModelAndView("login", "user", new User());
	}
	
	@RequestMapping(value="/addProduct",method = RequestMethod.POST)
	public String addProduct(Model model,@RequestParam("picture") MultipartFile multiPartPicture,
										@RequestParam("picture1") MultipartFile multiPartPicture1, 
										@RequestParam("picture2") MultipartFile multiPartPicture2, 
										HttpServletRequest req,HttpSession session) {
		if(session.getAttribute("username") != null && (Boolean)session.getAttribute("logged")) {
			String title=req.getParameter("title");
			int quantity=Integer.parseInt(req.getParameter("quantity"));
			double price = Double.parseDouble(req.getParameter("price"));
			String category=req.getParameter("category");
			String subcategory=req.getParameter("subcategory");
			String descrKey1=req.getParameter("descrKey1");
			String descrValue1=req.getParameter("descrValue1");
			String descrKey2=req.getParameter("descrKey2");
			String descrValue2=req.getParameter("descrValue2");
			String descrKey3=req.getParameter("descrKey3");
			String descrValue3=req.getParameter("descrValue3");
			//create product
			Product p=new Product(category, subcategory, title, quantity, price, descrKey1, descrValue1,
					descrKey2, descrValue2, descrKey3, descrValue3,0.0);
			ArrayList<String> paths = new ArrayList<>();
			if(multiPartPicture.getSize() != 0) {			
				try {
					File fileOnDisk = new File(FILE_LOCATION + multiPartPicture.getOriginalFilename());
					Files.copy(multiPartPicture.getInputStream(), fileOnDisk.toPath(), StandardCopyOption.REPLACE_EXISTING);
					paths.add(multiPartPicture.getOriginalFilename());
				} catch (IOException e) {
					System.out.println("io exception int check size comment"+e.getMessage());
					return "404";
				}
			}
			if(multiPartPicture1.getSize() != 0) {			
				try {
					File fileOnDisk = new File(FILE_LOCATION + multiPartPicture1.getOriginalFilename());
					Files.copy(multiPartPicture1.getInputStream(), fileOnDisk.toPath(), StandardCopyOption.REPLACE_EXISTING);
					paths.add(multiPartPicture1.getOriginalFilename());
				} catch (IOException e) {
					System.out.println("io exception int check size comment"+e.getMessage());
				}
			}
			if(multiPartPicture2.getSize() != 0) {			
				try {
					File fileOnDisk = new File(FILE_LOCATION + multiPartPicture2.getOriginalFilename());
					Files.copy(multiPartPicture2.getInputStream(), fileOnDisk.toPath(), StandardCopyOption.REPLACE_EXISTING);
					paths.add(multiPartPicture2.getOriginalFilename());
				} catch (IOException e) {
					System.out.println("io exception int check size comment"+e.getMessage());
				}
			}
			p.setImagePaths(paths);
			try {
				ProductDAO.getInstance().addProduct(p);
			} catch (SQLException e) {
				System.out.println("add product + "+e.getMessage());
				return "404";			
			}
			model.addAttribute("message", "Create new product!");
			return "addProduct";
		}
		return "login";		
	}
	
	
	@RequestMapping(value="product/{productId}/delete", method=RequestMethod.POST)
	public String deleteProduct (@PathVariable(value="productId") Integer productId, HttpSession session, Model m, HttpServletRequest req) {
		
		User user = (User)session.getAttribute("user");
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged") && user.getRole()==0){
			try {
				ProductDAO.getInstance().deleteProduct(productId);
			} catch (SQLException e) {
				System.out.println("sql deleteProduct"+e.getMessage());
				return "404";
			}
			return UserController.indexpage(m, req);
		}
		return "login";
	}
	
	
	@RequestMapping(value="product/{productId}/changeQuantity", method=RequestMethod.POST)
	public String changeQuantity (@PathVariable(value="productId") Integer productId, HttpSession session, HttpServletRequest req, Model model) {
		
		User user = (User)session.getAttribute("user");
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged") && user.getRole()==0){
			int quantity=Integer.parseInt(req.getParameter("quantity"));
			try {
				ProductDAO.getInstance().updateQuantity(productId, quantity);
			} catch (SQLException e) {
				System.out.println("sql changeQuantity "+ e.getMessage());
				return "404";
			}
			return ProductController.viewProduct(model, productId, session, req);
		}
		return "login";
	}
	
	
	private void sendEmail(ArrayList<User> users) throws MessagingException{
		EmailSender sender=EmailSender.getInstance();
		for(User u : users){
			sender.notify(u.getEmail());
		}
	}
	
	@RequestMapping(value="product/{productId}/setDiscount", method=RequestMethod.POST)
	public String setDiscount (@PathVariable(value="productId") Integer productId, HttpSession session, 
			HttpServletRequest req, Model model) {
		
		User user = (User)session.getAttribute("user");
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged") && user.getRole()==0){
			int discount=Integer.parseInt(req.getParameter("discount"));
			if(discount>=1 && discount<=100){
				try {
					ProductDAO.getInstance().makeSaleForOneProduct(productId, discount);
					ArrayList<User> users=UserDAO.getInstance().allSubscribers();
					sendEmail(users);
				} catch (SQLException e) {
					System.out.println("sql setDiscount "+e.getMessage());
					return "404";
				} catch (MessagingException e) {
					System.out.println("sql setDiscount messaging "+e.getMessage());
					return "404";
				}
			}
			else{
				session.setAttribute("messageDiscount", "Please, enter number between 1 and 100!");
			}
			return ProductController.viewProduct(model, productId, session, req);	
		}
		return "login";
	}
	
	
	@RequestMapping(value="{subcategory}/setDiscount", method=RequestMethod.POST)
	public String setDiscountForSubcat (@PathVariable(value="subcategory") String subcategory, HttpSession session, HttpServletRequest req, Model model) {
		
		User user = (User)session.getAttribute("user");
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged") && user.getRole()==0){
			try {
				if(SubcategoryDAO.getInstance().isSubcategory(subcategory)){					
					int discount=Integer.parseInt(req.getParameter("discount"));
					if(discount>=1 && discount<100){
						ProductDAO.getInstance().makeSaleForAllProductsBySubcategory(subcategory, discount);
						ArrayList<User> users=UserDAO.getInstance().allSubscribers();
						sendEmail(users);
					}
					else{
						session.setAttribute("messageDiscount", "Please, enter number between 1 and 100!");
					}	
				}
			} catch (SQLException | MessagingException e) {
				System.out.println("sql setDiscountForSubcat "+e.getMessage());
				return "404";
			}
			return ProductController.viewProductsBySubcategory(model, subcategory, session);
		}
		return "login";
	}
	
	
	@RequestMapping(value="/addCategory", method=RequestMethod.GET)
	public ModelAndView categoryPage (Model m, HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged") && user.getRole()==0){
			ArrayList<String> categories=new ArrayList<>();
			ArrayList<String> subcategories=new ArrayList<>();
			HashMap<String, ArrayList<String>> catAndSubcat=new HashMap<>();
			try {
				categories=CategoryDAO.getInstance().getAllCategories();
				for(String c: categories){
					System.out.println(c);
					if (!catAndSubcat.containsKey(c)) {
						catAndSubcat.put(c, new ArrayList<>());
					}
					subcategories=SubcategoryDAO.getInstance().getAllSubcategoryByCategory(c);
					for(String s:subcategories){
						catAndSubcat.get(c).add(s);
					}					
				}
				m.addAttribute("catAndSubcat", catAndSubcat);
			} catch (SQLException e) {
				System.out.println("sql index controller"+e.getMessage());
			}
			return new ModelAndView("addCategory");
		}
		return new ModelAndView("login", "user", new User());		
	}
	
	@RequestMapping(value="/addSubcategory", method=RequestMethod.POST)
	public ModelAndView addSubcategory (HttpSession session, Model m, HttpServletRequest req) {
		
		User user = (User)session.getAttribute("user");
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged") && user.getRole()==0){
			String name=req.getParameter("category");
			String subcategoryName=req.getParameter("subcategory");
			try {
				int categoryId=CategoryDAO.getInstance().getCategoryId(name);
				SubcategoryDAO.getInstance().addSubcategory(categoryId, subcategoryName);
			} catch (SQLException e) {
				System.out.println("sql add subcategory "+e.getMessage());
				return new ModelAndView("404");
			}
			return new ModelAndView("addCategory","words1", "You added new subcategory!");
		}
		return new ModelAndView("login", "user", new User());		
	}
	
	@RequestMapping(value="/addCategory", method=RequestMethod.POST)
	public ModelAndView addCategory (HttpSession session, Model m, HttpServletRequest req) {
		
		User user = (User)session.getAttribute("user");
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged") && user.getRole()==0){
			String category=req.getParameter("category");
			String subcategory1=req.getParameter("subcategory1").trim();
			String subcategory2=req.getParameter("subcategory2").trim();
			String subcategory3=req.getParameter("subcategory3").trim();
			ArrayList<String> subcategories=new ArrayList<>();
			if (subcategory1!=null && !subcategory1.isEmpty()) {
				subcategories.add(subcategory1);
			}
			if (subcategory2!=null && !subcategory2.isEmpty()) {
				subcategories.add(subcategory2);
			}
			if (subcategory3!=null && !subcategory3.isEmpty()) {
				subcategories.add(subcategory3);
			}
			try {
				CategoryDAO.getInstance().addCategory(category, subcategories);
			} catch (SQLException e) {
				System.out.println("sql add category "+e.getMessage());
				return new ModelAndView("404");
			}
			return new ModelAndView("addCategory","words2", "You added new categoory!");
		}
		return new ModelAndView("login", "user", new User());	
		
	}
}
