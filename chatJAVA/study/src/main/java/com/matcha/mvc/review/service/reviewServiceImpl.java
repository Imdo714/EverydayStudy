package com.matcha.mvc.review.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matcha.mvc.common.vo.PageInfo;
import com.matcha.mvc.review.dao.reviewDao;
import com.matcha.mvc.review.vo.Review;
import com.matcha.mvc.review.vo.ReviewImg;

@Service
public class reviewServiceImpl implements reviewService{

	@Autowired
	private reviewDao reviewDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int insertReview(Review r) {
		return reviewDao.insertReview(sqlSession, r);
	}

	@Override
	public int reviewImgInsert(ReviewImg ri) {
		return reviewDao.reviewImgInsert(sqlSession, ri);
	}

	@Override
	public int selectListCount() {
		return reviewDao.selectListCount(sqlSession);
	}

	@Override
	public ArrayList<Review> selectReviewList(PageInfo pi) {
		return reviewDao.selectReviewList(sqlSession, pi);
	}
	
	@Override
	public ArrayList<ReviewImg> selectReviewImgUrl(int reviewNo) {
		return reviewDao.selectReviewImgUrl(sqlSession, reviewNo);
	} 

	@Override
	@Transactional
	public int deletReview(int reviewNo) {
		return reviewDao.deletReview(sqlSession, reviewNo);
	}

	@Override
	public int deletReviewImg(int reviewNo) {
		return reviewDao.deletReviewImg(sqlSession, reviewNo);
	}

	
	
	
}
