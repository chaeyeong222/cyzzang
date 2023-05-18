package com.ssafit.cheajong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssafit.cheajong.model.dto.Review;
import com.ssafit.cheajong.model.service.ReviewService;

@Controller
@RequestMapping("/review")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class ReviewController {

	@Autowired
	ReviewService rService;

	@PostMapping("")
	public ResponseEntity<?> insertReview(@RequestBody Review review) {
		try {
			int res = rService.insertReview(review);
			return new ResponseEntity<Integer>(res, HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("")
	public ResponseEntity<?> updateReview(@RequestBody Review review) {
		try {
			int res = rService.updateReview(review);
			return new ResponseEntity<Integer>(res, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<?> deleteReview(@PathVariable int reviewId) {
		try {
			int res = rService.deleteReview(reviewId);
			return new ResponseEntity<Integer>(res, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/{videoId}")
	public ResponseEntity<?> selectReviewByVideoId(@PathVariable String videoId) {
		try {
			List<Review> res = rService.selectReviewByVideoId(videoId);
			return new ResponseEntity<List<Review>>(res, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<?> selectReviewByuserId(@PathVariable String userId) {
		try {
			List<Review> res = rService.selectReviewByuserId(userId);
			return new ResponseEntity<List<Review>>(res, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/rate/{videoId}")
	public ResponseEntity<?> videoRate(@PathVariable String videoId) {
		try {
			int res = rService.videoRate(videoId);
			return new ResponseEntity<Integer>(res, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<?> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
