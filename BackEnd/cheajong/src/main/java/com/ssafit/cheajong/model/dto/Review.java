package com.ssafit.cheajong.model.dto;

public class Review {

	private int reviewNum;
	private String videoId;
	private String userId;
	private String content;
	private String writeTime;
	private int rate;

	public int getReviewNum() {
		return reviewNum;
	}

	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

}
