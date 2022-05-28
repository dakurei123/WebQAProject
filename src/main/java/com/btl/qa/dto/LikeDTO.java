package com.btl.qa.dto;

public class LikeDTO extends AbstractDTO{

	private static final long serialVersionUID = 1032580314441850021L;
	private Long UserId;
	private Long answerId;
	public Long getUserId() {
		return UserId;
	}
	public void setUserId(Long userId) {
		UserId = userId;
	}
	public Long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}
}
