package com.astrokoala.models;

import java.time.LocalDate;

public class User {
	private int id;
	private String email;
	private String username;
	private LocalDate createDate;
	
	public User() {
		
	}
	
	public User(int id, String email, String username, LocalDate createDate) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	
	public String toString() {
		return 
				"id = " + this.id + ", "
				+ "email = " + this.email + ", "
				+ "username = " + this.username + ", "
				+ "createDate = " + this.createDate;
	}
	
}
