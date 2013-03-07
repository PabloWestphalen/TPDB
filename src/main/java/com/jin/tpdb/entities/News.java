package com.jin.tpdb.entities;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@ManyToOne
	private User user;

	@ManyToMany
	private Set<Tag> tags;

	@Column(nullable = false)
	private Date date;

	private String title;

	@Column(length = 65535, columnDefinition = "Text")
	private String content;
	
	//@OneToMany(cascade=CascadeType.ALL, mappedBy="news")
	@OneToMany(mappedBy="news")
	@OrderBy("date ASC")
	private Set<NewsComment> comments;
	
	public News() {
		date = new Date();
	}
	
	public News(String title, String content, User user, Set<Tag> tags) {
		this();
		this.title = title;
		this.content = content;
		this.user = user;
		this.tags = tags;
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public Set<NewsComment> getComments() {
		return comments;
	}

	public void setComments(Set<NewsComment> comments) {
		this.comments = comments;
	}
}