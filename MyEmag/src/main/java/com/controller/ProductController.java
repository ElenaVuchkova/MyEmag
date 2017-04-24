package com.controller;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;

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
import com.model.dao.UserDAO;

@Controller
@RequestMapping(value="/product")
public class ProductController {

//	@RequestMapping(value="/product", method=RequestMethod.GET)
//	public String view (Model m) {
//		return "productPage";
//	}
		
	//view product
	//@RequestMapping(value="product/{productId}",method = RequestMethod.GET)
	@RequestMapping(value="/{productId}",method = RequestMethod.GET)
	public String viewProduct (Model model, @PathVariable(value="productId") Integer productId, HttpSession session) {			
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
			
		/*HashMap<Integer, Product> allProducts=null;
		try {
			allProducts = ProductDAO.getInstance().getAllProducts();
			for (Product p: allProducts.values()){
				System.out.println(p);
			}
			m.addAttribute("allproducts", allProducts);
		} catch (SQLException e) {
			System.out.println("kdxjfd");
		}	*/	
		return "test";
	}	
	
	//view product
		@RequestMapping(value="{productId}/review",method = RequestMethod.GET)
		public String review (@PathVariable(value="productId") Integer productId) {		
			System.out.println("find review jsp++++++++++++++");
			return "review";
		}		
		
		@RequestMapping(value = "{productId}/review", method = RequestMethod.POST)
		public String addReview(@PathVariable(value="productId") Integer productId, HttpSession session, HttpServletRequest req){
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
						//kak da se vurnem kum stanicata na toq product??
						return "product/"+ productId;
						}
				} catch (NumberFormatException | SQLException e) {
					System.out.println("SQL" + e.getMessage());
					return "404";
				}
				return "index";
				}
			return "login";
		}
}
