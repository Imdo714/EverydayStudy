package com.matcha.mvc.review.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.matcha.mvc.common.vo.PageInfo;
import com.matcha.mvc.review.vo.Review;
import com.matcha.mvc.review.vo.ReviewImg;

@Repository
public class reviewDao {

	// 리뷰 등록하기
	public int insertReview(SqlSessionTemplate sqlSession, Review r) {
		return sqlSession.insert("ReviewMapper.insertReview", r);
	}
	
	// 리뷰 이미지 등록 
	public int reviewImgInsert(SqlSessionTemplate sqlSession, ReviewImg ri) {
		return sqlSession.insert("ReviewMapper.reviewImgInsert", ri);
	}
	
	// 리뷰 총 갯수
	public int selectListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("ReviewMapper.selectListCount");
	}
	
	// 리뷰 리스트 정보 가져오기 
	public ArrayList<Review> selectReviewList(SqlSessionTemplate sqlSession, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("ReviewMapper.selectReviewList", null, rowBounds);
	}
	
	//  삭제할 리뷰 이미지 URL 가졍오기
	public ArrayList<ReviewImg> selectReviewImgUrl(SqlSessionTemplate sqlSession, int reviewNo) {
		
		return (ArrayList)sqlSession.selectList("ReviewMapper.selectReviewImgUrl", reviewNo);
	}
	
	// 리뷰 삭제
	public int deletReview(SqlSessionTemplate sqlSession, int reviewNo) {
		return sqlSession.delete("ReviewMapper.deletReview", reviewNo);
	}
	
	// 리뷰 이미지 삭제
	public int deletReviewImg(SqlSessionTemplate sqlSession, int reviewNo) {
		return sqlSession.delete("ReviewMapper.deletReviewImg", reviewNo);
	}
	

	
}
