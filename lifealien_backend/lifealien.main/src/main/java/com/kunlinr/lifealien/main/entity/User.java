package com.kunlinr.lifealien.main.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 4943467772109474455L;
	
	public User() {
	}
	
	public User(Long id,String name,String title) {
		this.id = id;
		this.name = name;
		this.title = title;
	}

	@Id
	protected Long id;
	
	@Column(name = "name")
	protected String name;
	
	@Column(name = "title")
	protected String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
