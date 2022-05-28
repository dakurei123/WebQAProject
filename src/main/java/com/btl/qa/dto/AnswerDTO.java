package com.btl.qa.dto;

import java.util.List;

public class AnswerDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String content;
	
	private Long userId;
	private String avatarLink;
	private String fullName;
	
	private Long questionId;
	
	private List<ReplyDTO> replies;
	
	private boolean isMy;
	private int like;
	
	
	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public boolean isMy() {
		return isMy;
	}

	public void setMy(boolean isMy) {
		this.isMy = isMy;
	}

	public List<ReplyDTO> getReplies() {
		return replies;
	}

	public void setReplies(List<ReplyDTO> replies) {
		this.replies = replies;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAvatarLink() {
		return avatarLink;
	}

	public void setAvatarLink(String avatarLink) {
		this.avatarLink = avatarLink;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
