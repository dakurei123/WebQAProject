package com.btl.qa.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends BaseEntity {
	@Column
	private String username;
	@Column
	private String password;
	@Column(name = "fullname")
	private String fullName;
	@Column
	private Integer status;
	@Column 
	private String role;
	@Column
	private String avatar;
	
	@OneToMany(mappedBy = "user")
	private Collection<Question> questions;
	
	@OneToMany(mappedBy = "user")
	private Collection<Answer> answers;
	
	@OneToMany(mappedBy = "user")
	private Collection<Reply> replies;
	
	@OneToMany(mappedBy = "user")
	private Collection<Followed> followeds;
	
	@OneToMany(mappedBy = "user")
	private Collection<Like> likes;
	
	public Collection<Like> getLikes() {
		return likes;
	}
	public void setLikes(Collection<Like> likes) {
		this.likes = likes;
	}
	public Collection<Followed> getFolloweds() {
		return followeds;
	}
	public void setFolloweds(Collection<Followed> followeds) {
		this.followeds = followeds;
	}
	public Collection<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(Collection<Answer> answers) {
		this.answers = answers;
	}
	public Collection<Reply> getReplies() {
		return replies;
	}
	public void setReplies(Collection<Reply> replies) {
		this.replies = replies;
	}
	public Collection<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Collection<Question> questions) {
		this.questions = questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
