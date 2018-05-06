package com.dtdm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "pages")
public class Page implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "header")
	private String header;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content", length=65535)
	@Type(type="text")
	private String content;

	public Page() {
		super();
	}

	public Page(String header, String title, String content) {
		super();
		this.header = header;
		this.title = title;
		this.content = content;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
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
}
