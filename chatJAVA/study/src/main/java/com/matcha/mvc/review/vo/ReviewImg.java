package com.matcha.mvc.review.vo;

public class ReviewImg {
	
	private int reviewImgNo;
	private String reviewImgUrl;
	private String reviewOrginName;
	private String reviewChangName;
	private int reviewNo;
	
	public ReviewImg() {}

	public ReviewImg(int reviewImgNo, String reviewImgUrl, String reviewOrginName, String reviewChangName,
			int reviewNo) {
		super();
		this.reviewImgNo = reviewImgNo;
		this.reviewImgUrl = reviewImgUrl;
		this.reviewOrginName = reviewOrginName;
		this.reviewChangName = reviewChangName;
		this.reviewNo = reviewNo;
	}

	public int getReviewImgNo() {
		return reviewImgNo;
	}

	public void setReviewImgNo(int reviewImgNo) {
		this.reviewImgNo = reviewImgNo;
	}

	public String getReviewImgUrl() {
		return reviewImgUrl;
	}

	public void setReviewImgUrl(String reviewImgUrl) {
		this.reviewImgUrl = reviewImgUrl;
	}

	public String getReviewOrginName() {
		return reviewOrginName;
	}

	public void setReviewOrginName(String reviewOrginName) {
		this.reviewOrginName = reviewOrginName;
	}

	public String getReviewChangName() {
		return reviewChangName;
	}

	public void setReviewChangName(String reviewChangName) {
		this.reviewChangName = reviewChangName;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	@Override
	public String toString() {
		return "ReviewImg [reviewImgNo=" + reviewImgNo + ", reviewImgUrl=" + reviewImgUrl + ", reviewOrginName="
				+ reviewOrginName + ", reviewChangName=" + reviewChangName + ", reviewNo=" + reviewNo + "]";
	}
	
	
}