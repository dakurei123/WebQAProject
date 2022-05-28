package com.btl.qa.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question extends BaseEntity {
	@Column
	private String title;
	
	@Column(columnDefinition = "text")
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
	
	@OneToMany(mappedBy = "question")
	private Collection<Answer> answers;
	
	public Collection<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(Collection<Answer> answers) {
		this.answers = answers;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
