package com.dtdm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "posts")
public class Post implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "subtitle")
	private String subtitle;
	
	
	@Column(name = "content", length=65535)
	@Type(type="text")
	private String content;
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	
	public int getId() {
		return id;
	}
	public Post() {
		super();
	}
	
	
	public Post(int id, String title, String subtitle, String content, int author) {
		super();
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
		this.content = content;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
