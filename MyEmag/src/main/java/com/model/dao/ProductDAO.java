package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.Product;
import com.model.Review;
import com.mysql.jdbc.Statement;


public class ProductDAO {
	
	private static ProductDAO instance;
	private static final HashMap<Integer, Product> allproducts = new HashMap<>();//produuctId - > product
	
	private ProductDAO() throws SQLException{
			getAllProducts();
	}
	
	public static synchronized ProductDAO getInstance() throws SQLException{
		if(instance == null){
			instance = new ProductDAO();
		}
		return instance;
	}

	public synchronized void addProduct (Product p) throws SQLException {
		String sql = "INSERT INTO products (title, quantity, price, descr_key1, descr_value1, descr_key2, "
				+ "descr_value2, descr_key3, descr_value3, subcategory_id, sale_price) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con=DBManager.getInstance().getConnection();
		PreparedStatement st=null;
		try {
			int	subcategoryId = SubcategoryDAO.getInstance().getSubcategoryId(p.getSubcategory());
			st =con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			st.setString(1, p.getTitle());
			st.setInt(2, p.getQuantity());
			st.setDouble(3, p.getPrice());
			st.setString(4, p.getDescrKey1());
			st.setString(5, p.getDescrValue1());
			st.setString(6, p.getDescrKey2());
			st.setString(7, p.getDescrValue2());
			st.setString(8, p.getDescrKey3());
			st.setString(9, p.getDescrValue3());
			st.setInt(10, subcategoryId);
			st.setInt(11, 0);
			st.executeUpdate();
			ResultSet res = st.getGeneratedKeys();
			res.next();
			int productId = res.getInt(1);
			p.setProductId(productId);
			List<String> imagePaths=p.getImagePaths();
			for (String path : imagePaths) {
				ImageDAO.getInstance().addImagePath(path, productId);
			}
			p.setImagePaths(imagePaths);
		} catch (SQLException e) {
			System.out.println("SQL transaction to insert product -" + e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("SQL transaction to insert product- rollback -" + e.getMessage());
			}
		} finally {
			con.setAutoCommit(true);
		}
	}
	
	public HashMap<Integer, Product> getAllProducts() throws SQLException{
		if(allproducts.isEmpty()){
			String sql = "SELECT p.product_id, p.title, quantity, p.price, 	p.descr_key1, p.descr_value1," 
					+"p.descr_key2, p.descr_value2, p.descr_key3, p.descr_value3, p.sale_price, s.name as subcategory,"
					+"c.name AS category	FROM products p JOIN subcategories s ON (p.subcategory_id=s.subcategory_id) "
					+"JOIN categories c ON (s.category_id=c.category_id);";	
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while(res.next()){
				int productId=res.getInt("product_id");
				Product p=new Product(res.getString("category"), 
						res.getString("subcategory"), 
						res.getString("title"),
						res.getInt("quantity"), 
						res.getDouble("price"),  
						res.getString("descr_key1"),
						res.getString("descr_value1"),
						res.getString("descr_key2"), 
						res.getString("descr_value2"), 
						res.getString("descr_key3"), 
						res.getString("descr_value3"),
						res.getDouble("sale_price")
						);
				p.setProductId(productId);
				ArrayList<String> imagePaths=ImageDAO.getInstance().getAllImagePathsByProduct(productId);
				p.setImagePaths(imagePaths);
				ArrayList<Review> reviews=ReviewDAO.getInstance().getAllReviewsByProduct(productId);
				for (Review r : reviews) {
					r.setProduct(p);
				}
				p.setReviews(reviews);
				allproducts.put(productId, p);		
			}
		}
		return allproducts;
	}
	
	public Product getProduct (int productId) {
		if (allproducts.containsKey(productId)) {
			Product p=allproducts.get(productId);
			return p;			
		}
		return null;
	}
	
	public synchronized ArrayList<Product> getAllProductsFromOrder (int orderId) throws SQLException{
		ArrayList<Product> products=new ArrayList<>();
		String sql="SELECT p.product_id, p.title, p.quantity, p.price, p.descr_key1, p.descr_value1, p.descr_key2, p.descr_value2, p.descr_key3, p.descr_value3, p.sale_price"
				+ " s.name as subcategory, c.name AS category "
				+ "FROM products p JOIN orders_has_products o ON (p.product_id=o.product_id)," 
				+ "JOIN subcategories s ON (p.subcategory_id=s.subcategory_id), JOIN categories c (s.category_id=c.category_id)" 
				+ " WHERE order_id=?";
		PreparedStatement st;
		st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setInt(1, orderId);
		ResultSet res = st.executeQuery();
		while (res.next()) {
			int productId=res.getInt("product_id");
			Product p=new Product(res.getString("category"), 
					res.getString("subcategory"), 
					res.getString("title"),
					res.getInt("quantity"), 
					res.getDouble("price"), 
					res.getString("descr_key1"),
					res.getString("descr_value1"),
					res.getString("descr_key2"), 
					res.getString("descr_value2"), 
					res.getString("descr_key3"), 
					res.getString("descr_value3"),
					res.getDouble("sale_price")
					);
			
			p.setProductId(productId);
			ArrayList<String> imagePaths=ImageDAO.getInstance().getAllImagePathsByProduct(productId);
			p.setImagePaths(imagePaths);
			ArrayList<Review> reviews=ReviewDAO.getInstance().getAllReviewsByProduct(productId);
			for (Review r : reviews) {
				r.setProduct(p);
			}
			p.setReviews(reviews);
			products.add(p);
		}
		return products;
	}
	
	public synchronized ArrayList<Product> searchProduct (String word) {
		ArrayList<Product> products=new ArrayList<>();
		for (Product p : allproducts.values()) {
			String title=p.getTitle();
			if (title.toLowerCase().contains(word.toLowerCase())) {
				products.add(p);
			}
		}
		return products;
	}
	
	public synchronized void updateQuantity (int id, int quantity) throws SQLException{
		String sql = "UPDATE products SET quantity=? WHERE product_id=?";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setInt(1, quantity);
		st.setInt(2, id);
		st.executeUpdate();
		Product p=allproducts.get(id);
		p.setQuantity(quantity);
	}
	
	public synchronized void updateTitle (int id, String title) throws SQLException{
		String sql = "UPDATE products SET title=? WHERE product_id=?";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, title);
		st.setInt(2, id);
		st.executeUpdate();
		Product p=allproducts.get(id);
		p.setTitle(title);
	}
	
	public synchronized void updateDescr1 (int id, String descrValue1) throws SQLException{
		String sql = "UPDATE products SET descr_value1=? WHERE product_id=?";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, descrValue1);
		st.setInt(2, id);
		st.executeUpdate();
		Product p=allproducts.get(id);
		p.setDescrValue1(descrValue1);
	}
	public synchronized void updateDescr2 (int id, String descrValue2) throws SQLException{
		String sql = "UPDATE products SET descr_value2=? WHERE product_id=?";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, descrValue2);
		st.setInt(2, id);
		st.executeUpdate();
		Product p=allproducts.get(id);
		p.setDescrValue2(descrValue2);
	}
	public synchronized void updateDescr3 (int id, String descrValue3) throws SQLException{
		String sql = "UPDATE products SET descr_value3=? WHERE product_id=?";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, descrValue3);
		st.setInt(2, id);
		st.executeUpdate();
		Product p=allproducts.get(id);
		p.setDescrValue3(descrValue3);
	}
	public synchronized void deleteProduct (int id) throws SQLException{
		String sql1 = "DELETE FROM reviews WHERE product_id=?";
		String sql2 = "DELETE FROM images WHERE product_id=?";
		String sql3 = "DELETE FROM products WHERE product_id=?";
		PreparedStatement st1=null;
		PreparedStatement st2=null;
		PreparedStatement st3=null;
		Connection con=DBManager.getInstance().getConnection();
		try {
			con.setAutoCommit(false);
			st1 = con.prepareStatement(sql1);
			st2 = con.prepareStatement(sql2);
			st3 = con.prepareStatement(sql3);
			st1.executeUpdate();
			st2.executeUpdate();
			st3.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("SQL transaction to delete product -" + e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("SQL transaction to delete product- rollback -" + e.getMessage());
			}
		} finally {
			con.setAutoCommit(true);
		}
		allproducts.remove(id);
	}
	
	public void makeSaleForOneProduct (int id, int percent) throws SQLException {
		if (percent>0 && percent<100) {
			Product p=allproducts.get(id);
			double price=p.getPrice();
			double salePrice=price-(price*percent/100);
			p.setSalePrice(salePrice);
			String sql = "UPDATE products SET sale_price=? WHERE product_id=?";
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
			st.setDouble(1, salePrice);
			st.setInt(2, id);
			st.executeUpdate();
		}
	}
	
	public void makeSaleForAllProductsBySubcategory (String subcategory, int percent) throws SQLException {
		if (percent>0 && percent<100) {
			int subcategoryId=SubcategoryDAO.getInstance().getSubcategoryId(subcategory);
			String sql = "UPDATE products SET sale_price=price-(price*?/100) WHERE subcategory_id=?";
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
			st.setDouble(1, percent);
			st.setInt(2, subcategoryId);
			st.executeUpdate();
			getAllProducts();
		}
	}
	
	public ArrayList<Product> getAllProductsWithSale () {
		ArrayList<Product> productsWithSale = new ArrayList<>();
		for (Product p : allproducts.values()) {
			if (p.getSalePrice()!=0) {
				productsWithSale.add(p);
			}
		}
		return productsWithSale;
	}
}
