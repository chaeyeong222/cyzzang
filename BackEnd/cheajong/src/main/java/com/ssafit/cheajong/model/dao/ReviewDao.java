package com.ssafit.cheajong.model.dao;

import java.util.List;

import com.ssafit.cheajong.model.dto.Review;

public interface ReviewDao {

	int insertReview(Review review);

	int updateReview(Review review);

	int deleteReview(int reviewId);

	List<Review> selectReviewByVideoId(String videoId);

	List<Review> selectReviewByuserId(String videoId);

	int videoRate(String videoId);
}
