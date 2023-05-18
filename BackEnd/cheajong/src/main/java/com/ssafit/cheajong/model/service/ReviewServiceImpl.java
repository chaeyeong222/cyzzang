package com.ssafit.cheajong.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.cheajong.model.dao.ReviewDao;
import com.ssafit.cheajong.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDao rDao;

	@Override
	public int insertReview(Review review) {
		return rDao.insertReview(review);
	}

	@Override
	public int updateReview(Review review) {
		return rDao.updateReview(review);
	}

	@Override
	public int deleteReview(int reviewId) {
		return rDao.deleteReview(reviewId);
	}

	@Override
	public List<Review> selectReviewByVideoId(String videoId) {
		return rDao.selectReviewByVideoId(videoId);
	}
	
	@Override
	public List<Review> selectReviewByuserId(String videoId) {
		return rDao.selectReviewByuserId(videoId);
	}

	@Override
	public int videoRate(String videoId) {
		return rDao.videoRate(videoId);
	}

}
