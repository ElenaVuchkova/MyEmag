package com.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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

import com.model.Product;
import com.model.User;
import com.model.dao.CategoryDAO;
import com.model.dao.ProductDAO;
import com.model.dao.SubcategoryDAO;

@Controller
@MultipartConfig
public class ChangeProductController {
	private static final String FILE_LOCATION = "C:\\Users\\Elena\\Desktop\\EmagImages\\";
	//private static final String FILE_LOCATION = "C:\\Users\\hp\\Desktop\\EmagImages\\";
	private String jspName;
	
	
	@RequestMapping(value="/addProduct", method=RequestMethod.GET)
	public String createProduct (Model m) {
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
					System.out.println(s);
				}
				
			}
			System.out.println(catAndSubcat.size());
			m.addAttribute("catAndSubcat", catAndSubcat);
		} catch (SQLException e) {
			System.out.println("sql index controller"+e.getMessage());
		}
		return "addProduct";
	}
	
	@RequestMapping(value="/addProduct",method = RequestMethod.POST)
	public String addPost(Model model,@RequestParam("picture") MultipartFile multiPartPicture, HttpServletRequest req,HttpSession session) {
		//if(session.getAttribute("username") != null && (Boolean)session.getAttribute("logged")) {
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
			
			
			//check size
			if(multiPartPicture.getSize() != 0) {			
				try {
					File fileOnDisk = new File(FILE_LOCATION + multiPartPicture.getOriginalFilename());
					Files.copy(multiPartPicture.getInputStream(), fileOnDisk.toPath(), StandardCopyOption.REPLACE_EXISTING);
					ArrayList<String> paths = new ArrayList<>();
					paths.add(FILE_LOCATION+multiPartPicture.getOriginalFilename());
					p.setImagePaths(paths);
				} catch (IOException e) {
					System.out.println("io exception int check size comment"+e.getMessage());
				}
			}
			
			try {
				ProductDAO.getInstance().addProduct(p);
			} catch (SQLException e) {
				jspName= "addProduct";
				System.out.println(e.getMessage());
			}
		//}
//		else {
//			jspName = "login";
//		}		
		return jspName;
	}
	
	//uspeshno triene ama neuspeshno preprashtane kum index
	@RequestMapping(value="product/{productId}/delete", method=RequestMethod.GET)
	public String deleteProduct (@PathVariable(value="productId") Integer productId, HttpSession session) {
		//check user is admin 
		//check user session
		User user = (User)session.getAttribute("user");
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged") && user.getRole()==0){
			try {
				ProductDAO.getInstance().deleteProduct(productId);
			} catch (SQLException e) {
				System.out.println("sql deleteProduct"+e.getMessage());
				return "404";
			}
		}
		return "redirect:/index";		
	}
	
	
	
	
	
	
	

}
