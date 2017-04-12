package com.model;

public class Review {
	
	private int reviewId;
	private String comment;
	private int rating;
	private User user;
	private Product product;
	
	public Review(String comment, int rating, User user, Product product) {
		this.comment = comment;
		this.rating = rating;
		this.user = user;
		this.product = product;
	}
	
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	
	

}
