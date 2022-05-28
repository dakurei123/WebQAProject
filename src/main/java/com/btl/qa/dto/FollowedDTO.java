package com.btl.qa.dto;

public class FollowedDTO extends AbstractDTO{

	private static final long serialVersionUID = -2553975767275169835L;
	
	private Long userId;
	private Long followedId;
	private String followName;
	private String followAvatar;
	
	public String getFollowAvatar() {
		return followAvatar;
	}
	public void setFollowAvatar(String followAvatar) {
		this.followAvatar = followAvatar;
	}
	public String getFollowName() {
		return followName;
	}
	public void setFollowName(String followName) {
		this.followName = followName;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getFollowedId() {
		return followedId;
	}
	public void setFollowedId(Long followedId) {
		this.followedId = followedId;
	}
}
