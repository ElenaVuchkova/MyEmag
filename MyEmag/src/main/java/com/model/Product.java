package com.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Product implements Comparable<Product>{
	
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
	private ArrayList<Review> reviews=new ArrayList<>();
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
	public int compareTo(Product o) {
		return this.productId-o.productId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((descrKey1 == null) ? 0 : descrKey1.hashCode());
		result = prime * result + ((descrKey2 == null) ? 0 : descrKey2.hashCode());
		result = prime * result + ((descrKey3 == null) ? 0 : descrKey3.hashCode());
		result = prime * result + ((descrValue1 == null) ? 0 : descrValue1.hashCode());
		result = prime * result + ((descrValue2 == null) ? 0 : descrValue2.hashCode());
		result = prime * result + ((descrValue3 == null) ? 0 : descrValue3.hashCode());
		result = prime * result + ((imagePaths == null) ? 0 : imagePaths.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + productId;
		result = prime * result + quantity;
		result = prime * result + ((reviews == null) ? 0 : reviews.hashCode());
		temp = Double.doubleToLongBits(salePrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((subcategory == null) ? 0 : subcategory.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (descrKey1 == null) {
			if (other.descrKey1 != null)
				return false;
		} else if (!descrKey1.equals(other.descrKey1))
			return false;
		if (descrKey2 == null) {
			if (other.descrKey2 != null)
				return false;
		} else if (!descrKey2.equals(other.descrKey2))
			return false;
		if (descrKey3 == null) {
			if (other.descrKey3 != null)
				return false;
		} else if (!descrKey3.equals(other.descrKey3))
			return false;
		if (descrValue1 == null) {
			if (other.descrValue1 != null)
				return false;
		} else if (!descrValue1.equals(other.descrValue1))
			return false;
		if (descrValue2 == null) {
			if (other.descrValue2 != null)
				return false;
		} else if (!descrValue2.equals(other.descrValue2))
			return false;
		if (descrValue3 == null) {
			if (other.descrValue3 != null)
				return false;
		} else if (!descrValue3.equals(other.descrValue3))
			return false;
		if (imagePaths == null) {
			if (other.imagePaths != null)
				return false;
		} else if (!imagePaths.equals(other.imagePaths))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (productId != other.productId)
			return false;
		if (quantity != other.quantity)
			return false;
		if (reviews == null) {
			if (other.reviews != null)
				return false;
		} else if (!reviews.equals(other.reviews))
			return false;
		if (Double.doubleToLongBits(salePrice) != Double.doubleToLongBits(other.salePrice))
			return false;
		if (subcategory == null) {
			if (other.subcategory != null)
				return false;
		} else if (!subcategory.equals(other.subcategory))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", category=" + category + ", subcategory=" + subcategory
				+ ", title=" + title + ", quantity=" + quantity + ", price=" + price + "]";
	}

}
