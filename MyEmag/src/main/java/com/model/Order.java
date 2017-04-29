package com.model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Order {
	
	private int orderId;
	HashMap<Product,Integer> products;
	private double price;
	private LocalDateTime date;
	private User user;
	private String payment;
	private String address;
	
	public Order(HashMap<Product,Integer> products,LocalDateTime date, User user, String payment, String address) {
		this.products=products;
		this.date =date;
		this.user = user;
		this.payment = payment;
		this.address=address;

	}
	

	public String getAddress() {
		return address;
	}


	public void setAdress(String address) {
		this.address = address;
	}


	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Map<Product,Integer> getProducts() {
		return Collections.unmodifiableMap(products);
	}

	public void setProducts(HashMap<Product, Integer> products) {
		this.products = products;
	}
	
	

}
