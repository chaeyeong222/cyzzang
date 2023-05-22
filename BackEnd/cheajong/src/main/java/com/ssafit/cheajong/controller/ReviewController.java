package com.ssafit.cheajong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.cheajong.model.dto.Review;
import com.ssafit.cheajong.model.service.ReviewService;

@RestController
@RequestMapping("/reviewapi")
public class ReviewController {

	@Autowired
	ReviewService rService;

	@PostMapping("/review")
	public ResponseEntity<?> insertReview(@RequestBody Review review) {
		try {
			int res = rService.insertReview(review);
			if (res == 1)
				return new ResponseEntity<Integer>(res, HttpStatus.CREATED);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/review")
	public ResponseEntity<?> updateReview(@RequestBody Review review) {
		try {
			int res = rService.updateReview(review);
			if (res == 1)
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/review/{reviewId}")
	public ResponseEntity<?> deleteReview(@PathVariable int reviewId) {
		try {
			int res = rService.deleteReview(reviewId);
			if (res == 1)
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/video/{videoId}")
	public ResponseEntity<?> selectReviewByVideoId(@PathVariable String videoId) {
		try {
			List<Review> res = rService.selectReviewByVideoId(videoId);
			if (res.size() > 0)
				return new ResponseEntity<List<Review>>(res, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<?> selectReviewByuserId(@PathVariable String userId) {
		try {
			List<Review> res = rService.selectReviewByuserId(userId);
			if (res.size() > 0)
				return new ResponseEntity<List<Review>>(res, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/rate/{videoId}")
	public ResponseEntity<?> videoRate(@PathVariable String videoId) {
		try {
			int res = rService.videoRate(videoId);
			if (res > 0)
				return new ResponseEntity<Integer>(res, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<?> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("오류발생: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
