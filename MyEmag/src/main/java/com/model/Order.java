package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
	
	private int orderId;
	private ArrayList<Product> products;
	private double price;
	private LocalDateTime date;
	private User user;
	private String payment;
	
	public Order(ArrayList<Product> products,LocalDateTime date, User user, String payment) {
		this.products=products;
		this.date =date;
		this.user = user;
		this.payment = payment;
		this.calculatePrice();
	}
	
	public void calculatePrice() {
		if (this.products!=null) {
			for (Product p: products) {
				this.price+=p.getPrice();
			}
		}
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

	public List<Product> getProducts() {
		return Collections.unmodifiableList(products);
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	

}
