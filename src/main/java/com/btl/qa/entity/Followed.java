package com.btl.qa.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "followed")
public class Followed extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "user_followed_id")
	private User userFollowed;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUserFollowed() {
		return userFollowed;
	}

	public void setUserFollowed(User userFollowed) {
		this.userFollowed = userFollowed;
	}
	
}
