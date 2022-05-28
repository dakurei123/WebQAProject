package com.btl.qa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reply")
public class Reply extends BaseEntity {
	@Column(columnDefinition = "text")
	private String content;

	@ManyToOne
	@JoinColumn(name = "answer_id")
	private Answer answer;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
