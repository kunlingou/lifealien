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
	
	public User(Long id,String username,String title,String password) {
		this.id = id;
		this.username = username;
		this.title = title;
		this.password = password;
	}

	@Id
	protected Long id;
	
	@Column(name = "title")
	protected String title;
	
	@Column(name = "username")
	protected String username;
	
	@Column(name = "password")
	protected String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
