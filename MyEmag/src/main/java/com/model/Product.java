package com.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Product implements Comparable<Product> {
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", category=" + category + ", subcategory=" + subcategory
				+ ", title=" + title + ", quantity=" + quantity + ", price=" + price + "]";
	}


	private int productId;
	private String category;
	private String subcategory;
	private  String title;
	private int quantity;
	private double price;
	private List<String>  imagePaths;
	private String descrKey1;
	private String descrValue1;
	private String descrKey2;
	private String descrValue2;
	private String descrKey3;
	private String descrValue3;
	private ArrayList<Review> reviews;
	private double salePrice;
	
	

	public Product () {
		
	}
	
	public Product(String category, String subcategory, String title, int quantity, double price, String descrKey1, String descrValue1, String descrKey2,
			String descrValue2, String descrKey3, String descrValue3, double salePrice) {
		this.category=category;
		this.subcategory=subcategory;
		this.title = title;
		this.quantity = quantity;
		this.price=price;
		this.descrKey1 = descrKey1;
		this.descrValue1 = descrValue1;
		this.descrKey2 = descrKey2;
		this.descrValue2 = descrValue2;
		this.descrKey3 = descrKey3;
		this.descrValue3 = descrValue3;
		this.salePrice=salePrice;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getSubcategory() {
		return subcategory;
	}


	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public List<String> getImagePaths() {
		return Collections.unmodifiableList(imagePaths);
	}


	public void setImagePaths(List<String> imagePaths) {
		this.imagePaths = imagePaths;
	}


	public String getDescrKey1() {
		return descrKey1;
	}


	public void setDescrKey1(String descrKey1) {
		this.descrKey1 = descrKey1;
	}


	public String getDescrValue1() {
		return descrValue1;
	}


	public void setDescrValue1(String descrValue1) {
		this.descrValue1 = descrValue1;
	}


	public String getDescrKey2() {
		return descrKey2;
	}


	public void setDescrKey2(String descrKey2) {
		this.descrKey2 = descrKey2;
	}


	public String getDescrValue2() {
		return descrValue2;
	}


	public void setDescrValue2(String descrValue2) {
		this.descrValue2 = descrValue2;
	}


	public String getDescrKey3() {
		return descrKey3;
	}


	public void setDescrKey3(String descrKey3) {
		this.descrKey3 = descrKey3;
	}


	public String getDescrValue3() {
		return descrValue3;
	}


	public void setDescrValue3(String descrValue3) {
		this.descrValue3 = descrValue3;
	}
	
	public List<Review> getReviews() {
		return Collections.unmodifiableList(reviews);
	}
	
	public void addReview(Review r){
		this.reviews.add(r);
	}


	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	@Override
	public int compareTo(Product p) {
		return this.reviews.size()-p.reviews.size();
	}
}
