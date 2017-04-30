package com.controller;


import static org.mockito.Matchers.doubleThat;
import static org.mockito.Matchers.intThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.model.Order;
import com.model.Product;
import com.model.User;
import com.model.dao.CategoryDAO;
import com.model.dao.FavouriteProductsDAO;
import com.model.dao.OrderDAO;
import com.model.dao.PaymentDAO;
import com.model.dao.ProductDAO;
import com.model.dao.SubcategoryDAO;
import com.model.dao.UserDAO;


@Controller
public class UserController {		
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String indexpage(Model m, HttpServletRequest req){
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
			//add to application
			ServletContext sc = req.getServletContext();
			sc.setAttribute("catAndSubcat", catAndSubcat);
			//session.setAttribute("catAndSubcat", catAndSubcat);
			ArrayList<Product> newProducts= ProductDAO.getInstance().getTopTwelveNewProducts();
			m.addAttribute("newProducts", newProducts);
			System.out.println("Sled login");
			System.out.println("vsichki produkti za index page");
			for (Product p: newProducts){
				System.out.println(p);
			}
		} catch (SQLException e) {
			System.out.println("sql index controller"+e.getMessage());
			return "404";
		}
		return "index";
	}	
	
	//login	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginPage(Model model, HttpSession session) {
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
			return new ModelAndView("index");
		}
		return new ModelAndView("login","user", new User());
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute("user") User userHelper, HttpSession session, Model m, HttpServletRequest req) {	
		try {
			String username=userHelper.getUsername();
			String password=userHelper.getPassword();
			if(UserDAO.getInstance().validLogin(username, password)){
				User u=UserDAO.getInstance().getUser(username);
				session.setAttribute("user", u);
				session.setAttribute("username", username);
				session.setAttribute("logged", true);
				session.setAttribute("cart", new HashMap<Product, Integer>());
				return indexpage(m, req);
			}
			else{
				session.setAttribute("logged", false);
				m.addAttribute("login", "Could not login. Please, enter a valid username and password!");
			}
		} catch (SQLException e) {
			System.out.println("Error loging in - " + e.getMessage());
			return "404";
		}
		return "login";
	}
	
	//register
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView register(HttpSession session, Model model) {
		return new ModelAndView("register", "user", new User());
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session,Model m){
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
			session.invalidate();
		}
		if(result.hasErrors()){
			m.addAttribute("register", "Could not register. Please, enter a valid data!");
		}
		else{			
			try {
				UserDAO.getInstance().addUser(user);
				m.addAttribute("register", "Successfully register! Please, login!");			
				return "login";
			} catch (SQLException e) {
				m.addAttribute("register", "The user already exists!");
				System.out.println(e.getMessage());
			}
		}
        return "register";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session, Model model, HttpServletRequest req) {
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
			session.invalidate();
		}
		return indexpage(model, req);
	}
	

	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test() {
		return "test";
	}
	

	@RequestMapping(value="/cart", method=RequestMethod.GET)
	public ModelAndView viewCart(Model model, HttpSession session) {
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
			return new ModelAndView("cart");
		}
		return new ModelAndView("login", "user", new User());
	}
	
	@ResponseBody
	@RequestMapping(value = "/setNewQuantity/{productId}/{quantity}", method = RequestMethod.POST)
	public String setNewQuantity(Model model, HttpServletRequest request, 
								@PathVariable("productId") Integer productId,
								@PathVariable("quantity") Integer quantity, HttpSession session){		
		//ot tekushtata sesiq vzimame productite
		//trqbva da e map s product i kolichestvo
		HashMap<Product, Integer> cartProducts=  (HashMap<Product, Integer>) session.getAttribute("cart");
		//opredeleniq product
		Product product;
		try {
			product = ProductDAO.getInstance().getProduct(productId);
			//slagame novoto kolichestvo na opredeliq product v mapa
			cartProducts.put(product, quantity);
			//smqtame novata cena
			double newCartPrice = 0.0;
			for (Entry<Product, Integer> entry : cartProducts.entrySet()) {
				newCartPrice += (entry.getKey().getPrice() * entry.getValue());
				System.out.println(newCartPrice);
			}
			
			//json object
			JsonObject respJSON = new JsonObject();
			//json arr
			JsonArray respArray = new JsonArray();
			
			//vsqko entry e otdelen obekt
			JsonObject change1 = new JsonObject();
			//place->total price na opredelen product
			change1.addProperty("place", productId);
			
			//message->cenata na producta*kolichestvoto
			double price=0.0;
			if (product.getSalePrice()!=0) {
				price=product.getSalePrice() * cartProducts.get(product);
			}
			else {
				price=product.getPrice() * cartProducts.get(product);
			}
			change1.addProperty("messege", price);
			
			JsonObject change2 = new JsonObject();
			//total_cart_price
			change2.addProperty("place", "total_cart_price");
			//novata cena v kolichkata
			change2.addProperty("messege", newCartPrice);
			
			respArray.add(change1);
			respArray.add(change2);
			
			respJSON.add("changes", respArray);
			
			return respJSON.toString();
		} catch (SQLException e) {
			System.out.println("setNewQuantity"+ e.getMessage());
		}		
		return "";
	}

	
	
	@RequestMapping(value="/cart/delete/{productId}", method=RequestMethod.POST)
	public ModelAndView cartPage(Model model, HttpSession session, @PathVariable(value="productId") Integer productId) {
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
			HashMap<Product, Integer> productsInCart=(HashMap<Product, Integer>) session.getAttribute("cart");
			Product product;
			try {
				product = ProductDAO.getInstance().getProduct(productId);
				if(productsInCart.containsKey(product)) {
					productsInCart.remove(product);
				}
			} catch (SQLException e) {
				return new ModelAndView("404");
			}
			return viewCart(model, session);
			}
		return new ModelAndView("login", "user", new User());
	}
	
	@RequestMapping(value="/sale", method=RequestMethod.GET)
	public String salePage(Model m){
		try {		
			HashMap<String,ArrayList<Product>> allProductsWithSale= ProductDAO.getInstance().getAllProductsWithSale();
			m.addAttribute("allProductsWithSale", allProductsWithSale);
			for (java.util.Map.Entry<String,ArrayList<Product>> entry: allProductsWithSale.entrySet() ) {
				ArrayList<Product> products=entry.getValue();
				System.out.println(entry.getKey());
				for (Product p:products ){
					System.out.println(p);
				}
			}
		} catch (SQLException e) {
			System.out.println("sql index controller"+e.getMessage());
			return "404";
		}
		return "sale";
	}	
	
	@RequestMapping(value="/wishlist", method=RequestMethod.GET)
	public ModelAndView wishlistPage(Model model, HttpSession session) {
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
			User u=(User) session.getAttribute("user");
			String username=u.getUsername();
			ArrayList<Product> favouriteProducts=new ArrayList<>(); 
			try {
				favouriteProducts=FavouriteProductsDAO.getInstance().getAllFavouriteProductsByUser(username);
				return new ModelAndView("wishlist", "favouriteProducts", favouriteProducts);
			} catch (SQLException e) {
				return new ModelAndView("404");
			}
		}
		return new ModelAndView("login", "user", new User());
	}
	
	@RequestMapping(value="/wishlist/delete/{productId}",method = RequestMethod.POST)
	public ModelAndView deleteFromWishlist (@PathVariable(value="productId") Integer productId,HttpSession session, Model model) {		
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
			User u=(User) session.getAttribute("user");
			int userId=u.getUserId(); 
			try {
				FavouriteProductsDAO.getInstance().deleteFavouriteProduct(userId, productId);
				return wishlistPage(model, session);
			} catch (SQLException e) {
				System.out.println("SQL delete favourite product - " + e.getMessage());
				return new ModelAndView("404");
			}
		}
		return new ModelAndView("login", "user", new User());
	}
	
	@RequestMapping(value="/wishlist/addToCart/{productId}",method = RequestMethod.POST)
	public ModelAndView  addToCartFromWishlist (@PathVariable(value="productId") Integer productId,HttpSession session, Model model) {		
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
			HashMap<Product,Integer> productsInCart=(HashMap<Product, Integer>) session.getAttribute("cart");
			try {
				if(ProductDAO.getInstance().getAllProducts().containsKey(productId)) {
					Product p=ProductDAO.getInstance().getProduct(productId);
					productsInCart.put(p,1);
					return wishlistPage(model, session);
				}
			} catch (SQLException e) {
				System.out.println("SQL add products to cart " + e.getMessage());
				return new ModelAndView("404");
			}
		}
		return new ModelAndView("login");
	}
	

	
	@RequestMapping(value="/order", method=RequestMethod.GET)
	public String order (Model model, HttpSession session) {
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
			HashMap<Product,Integer> products=(HashMap<Product, Integer>) session.getAttribute("cart");
			double price=0;
			for (Product p: products.keySet()) {
				if (p.getSalePrice()!=0) {
					price+=p.getSalePrice()*products.get(p);
				}
				else {
					price+=p.getPrice()*products.get(p);
				}
			}
			try {
				ArrayList<String> waysToPay=PaymentDAO.getInstance().getAllPayments();
				model.addAttribute("waysToPay", waysToPay);
				session.setAttribute("price", price);
			} catch (SQLException e) {
				System.out.println("sql order "+e.getMessage());
			}			
			return "order";
		}
		return "login";
	}
	
	@RequestMapping(value="/order",method = RequestMethod.POST)
	public String makeOrder (Model model, HttpServletRequest req,HttpSession session) {
		if(session.getAttribute("username") != null && (Boolean)session.getAttribute("logged")) {
			HashMap<Product,Integer> products=(HashMap<Product, Integer>) session.getAttribute("cart");
			String payment=req.getParameter("wayToPay");
			String address=req.getParameter("address");
			User user=(User)session.getAttribute("user");
			Order o=new Order(products, LocalDateTime.now(), user, payment,address);
			double price=(double) session.getAttribute("price");
			o.setPrice(price);
			try {
				OrderDAO.getInstance().addOrder(o);
				for (Iterator <Entry<Product, Integer>> iterator = products.entrySet().iterator(); iterator.hasNext();) {
					Entry<Product, Integer> e= iterator.next();
					Product p=e.getKey();
					int productId=p.getProductId();
					int quantity=e.getValue();
					if (quantity>0 && quantity<=p.getQuantity()) {
						ProductDAO.getInstance().changeQuantity(productId, quantity);	
					}
					iterator.remove();
				}
				session.setAttribute("cart", new HashMap<>());
			} catch (SQLException e) {
				System.out.println("SQL- make order " + e.getMessage());
				return "404";
			}
			return indexpage(model, req);
		}
		return "login";
	}


	@RequestMapping(value="/subscribe", method = RequestMethod.POST)
	public void  subscribe (HttpSession session, Model model) {		
		if(session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")){
			User u=(User) session.getAttribute("user");
			String username=u.getUsername();
			try {
				UserDAO.getInstance().subscribe(username);
			} catch (SQLException e) {
				System.out.println("SQL - subscribe " + e.getMessage());
			}
			
		}
	}	

}
