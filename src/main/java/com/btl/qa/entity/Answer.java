package com.btl.qa.entity;

import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name = "answer")
public class Answer extends BaseEntity {
	@Column(columnDefinition = "text")
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;
	
	@OneToMany(mappedBy = "answer")
	private Collection<Reply> replies;
	
	@OneToMany(mappedBy = "answer")
	private Collection<Like> likes;
	
	public Collection<Like> getLikes() {
		return likes;
	}

	public void setLikes(Collection<Like> likes) {
		this.likes = likes;
	}

	public Collection<Reply> getReplies() {
		return replies;
	}

	public void setReplies(Collection<Reply> replies) {
		this.replies = replies;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
