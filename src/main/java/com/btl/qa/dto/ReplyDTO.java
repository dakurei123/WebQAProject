package com.btl.qa.dto;

public class ReplyDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String content;
	
	private Long userId;
	private String avatarLink;
	private String fullName;
	
	private Long answerId;
	
	private boolean isMy;
	
	public boolean isMy() {
		return isMy;
	}

	public void setMy(boolean isMy) {
		this.isMy = isMy;
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

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
