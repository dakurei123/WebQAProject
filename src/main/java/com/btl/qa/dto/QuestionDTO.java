package com.btl.qa.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class QuestionDTO extends AbstractDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "Title is required")
	@Size(max = 255, message = "Max size of title is 255 character")
	private String title;
	@NotBlank(message = "Content is required")
	private String content;
	private Long user_id;
    private Long category_id;
    private String fullName;
    private String categoryName;
    private String avatarLink;
    
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
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}  
}
