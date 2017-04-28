package com.model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;

import java.util.Set;

public class Order {
	
	private int orderId;
	private HashSet<Product> products;
	private double price;
	private LocalDateTime date;
	private User user;
	private String payment;
	
	public Order(HashSet<Product> products,LocalDateTime date, User user, String payment) {
		this.products=products;
		this.date =date;
		this.user = user;
		this.payment = payment;
		this.calculatePrice();
	}
	
	public void calculatePrice() {
		if (this.products!=null) {
			for (Product p: products) {
				double price=p.getPrice();
				double salePrice=p.getSalePrice();
				if (salePrice!=0) {
					this.price+=salePrice;
				}
				this.price+=price;
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

	public Set<Product> getProducts() {
		return Collections.unmodifiableSet(products);
	}

	public void setProducts(HashSet<Product> products) {
		this.products = products;
	}
	
	

}
