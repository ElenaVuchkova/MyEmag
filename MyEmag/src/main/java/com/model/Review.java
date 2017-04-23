package com.model;

import java.time.LocalDateTime;

public class Review {
	
	private int reviewId;
	private String comment;
	private int rating;
	private User user;
	private Product product;
	private LocalDateTime date;
	

	public Review(String comment, int rating, User user, Product product, LocalDateTime date) {
		this.comment = comment;
		this.rating = rating;
		this.user = user;
		this.product = product;
		this.date=date;
		
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
