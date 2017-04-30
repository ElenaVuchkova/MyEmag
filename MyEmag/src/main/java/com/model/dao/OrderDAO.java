package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.model.Order;
import com.model.Product;
import com.model.User;

public class OrderDAO {

	private static OrderDAO instance;
	
	private OrderDAO(){
	}
	
	public static synchronized OrderDAO getInstance(){
		if(instance == null){
			instance = new OrderDAO();
		}
		return instance;
	}
	
	
	public synchronized void addOrder (Order o) throws SQLException{
		int userId=o.getUser().getUserId();
		String paymentType=o.getPayment();
		o.setDate(LocalDateTime.now());
		int paymentId=PaymentDAO.getInstance().getPaymentId(paymentType);
		String sql = "INSERT INTO orders (price, date, user_id, payment_id, address) values (?,?,?,?,?)";
		Connection con=DBManager.getInstance().getConnection();
		PreparedStatement st=null;
		try {
			con.setAutoCommit(false);
			st = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			st.setDouble(1, o.getPrice());
			st.setTimestamp(2, java.sql.Timestamp.valueOf(o.getDate()));
			st.setInt(3, userId);
			st.setInt(4, paymentId);
			st.setString(5, o.getAddress());
			st.executeUpdate();
			ResultSet rs=st.getGeneratedKeys();
			rs.next();
			int orderId=rs.getInt(1);
			o.setOrderId(orderId);
			Map<Product,Integer> products=o.getProducts();
			for (Product p: products.keySet()) {
				int productId=p.getProductId();
				OrderHasProductDAO.getInstance().addOrderedProduct(orderId, productId, products.get(p));
			}
			con.commit();
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
	
	
//	public synchronized ArrayList<Order> getAllOrdersByUser (String userName) throws SQLException {
//		User user=UserDAO.getInstance().getUser(userName);
//		int userId=user.getUserId();
//		ArrayList<Order> orders=new ArrayList<>();
//		String sql="SELECT o.order_id, o.date, p.type, FROM orders o JOIN payments p ON (o.payment_id=p.payment_id) WHERE user_id=?";
//		PreparedStatement st;
//		st = DBManager.getInstance().getConnection().prepareStatement(sql);
//		st.setInt(1, userId);
//		ResultSet res = st.executeQuery();
//		while (res.next()) {
//			int orderId=res.getInt("order_id");
//			String payment=res.getString("type");
//			java.sql.Timestamp time=res.getTimestamp("date");
//			LocalDateTime date=time.toLocalDateTime();
//			Order o=new Order(null, date, user, payment);
//			o.setOrderId(orderId);
//			HashSet<Product> products=ProductDAO.getInstance().getAllProductsFromOrder(orderId);
//			o.setProducts(products);
//			orders.add(o);
//		}
//		return orders;
//	}

}
