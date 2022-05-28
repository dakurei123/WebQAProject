package com.btl.qa.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class UserDTO extends AbstractDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "Username is required")
	@Size(min = 3, message = "Username must be at least 3 characters long")
	private String username;
	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;
	@NotBlank(message = "Full name is required")
	@Size(min = 3, message = "Full name must be at least 3 characters long")
	private String fullName;
	private Integer status;
	private String role;
	private String avatar;
	private int questions;
	private int answers;
	private int like;
	
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getQuestions() {
		return questions;
	}
	public void setQuestions(int questions) {
		this.questions = questions;
	}
	public int getAnswers() {
		return answers;
	}
	public void setAnswers(int answers) {
		this.answers = answers;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
