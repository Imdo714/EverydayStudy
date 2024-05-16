package com.matcha.mvc.review.service;

import java.util.ArrayList;

import com.matcha.mvc.common.vo.PageInfo;
import com.matcha.mvc.review.vo.Review;
import com.matcha.mvc.review.vo.ReviewImg;

public interface reviewService {

	// 리뷰 등록하기
	int insertReview(Review r);
	
	// 리뷰 이미지 등록 
	int reviewImgInsert(ReviewImg ri);
	
	// 리뷰 총 갯수
	int selectListCount();
	
	// 리뷰 리스트 정보
	ArrayList<Review> selectReviewList(PageInfo pi);
	
	// 삭제할 리뷰 이미지 URL 가졍오기
	ArrayList<ReviewImg> selectReviewImgUrl(int reviewNo);
	
	// 리뷰 삭제
	int deletReview(int reviewNo);
	
	// 리뷰 이미지 삭제
	int deletReviewImg(int reviewNo);
}
