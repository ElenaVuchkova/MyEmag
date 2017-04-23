package com.controller;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.model.Product;
import com.model.dao.ProductDAO;

@Controller
public class ProductController {

	@RequestMapping(value="/product", method=RequestMethod.GET)
	public String view (Model m) {
		return "productPage";
	}
		
	//view product
	@RequestMapping(value="product/{productId}",method = RequestMethod.GET)
	public String viewProduct (Model model, @PathVariable("productId") Integer productId, HttpSession session) {			
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
	
	
}
