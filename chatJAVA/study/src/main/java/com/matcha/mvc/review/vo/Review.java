package com.matcha.mvc.review.vo;

import java.sql.Date;

public class Review {
	
	private int reviewNo;
	private String reviewTitle;
	private String reviewContent;
	private Date reviewDate;
	private int reviewStar;
	private int userNo;
	private String reviewChangName;

	public Review() {}

	public Review(int reviewNo, String reviewTitle, String reviewContent, Date reviewDate, int reviewStar, int userNo,
			String reviewChangName) {
		super();
		this.reviewNo = reviewNo;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewDate = reviewDate;
		this.reviewStar = reviewStar;
		this.userNo = userNo;
		this.reviewChangName = reviewChangName;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public int getReviewStar() {
		return reviewStar;
	}

	public void setReviewStar(int reviewStar) {
		this.reviewStar = reviewStar;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getReviewChangName() {
		return reviewChangName;
	}

	public void setReviewChangName(String reviewChangName) {
		this.reviewChangName = reviewChangName;
	}

	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", reviewTitle=" + reviewTitle + ", reviewContent=" + reviewContent
				+ ", reviewDate=" + reviewDate + ", reviewStar=" + reviewStar + ", userNo=" + userNo
				+ ", reviewChangName=" + reviewChangName + "]";
	}

	
}
