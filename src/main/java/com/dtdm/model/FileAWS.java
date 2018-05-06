package com.dtdm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class FileAWS implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	private int id;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public FileAWS() {
		super();
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getLinkdownload() {
		return linkdownload;
	}

	public void setLinkdownload(String linkdownload) {
		this.linkdownload = linkdownload;
	}

	@Column(name = "linkdownload")
	private String linkdownload;
	
	
	public FileAWS(int id, String linkdownload, String name) {
		super();
		this.id = id;
		this.linkdownload = linkdownload;
		this.name = name;
	}

	@Column(name = "name")
	private String name;
}
