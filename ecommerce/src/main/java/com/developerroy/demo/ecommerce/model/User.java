package com.developerroy.demo.ecommerce.model;


public class User {

	private String userName;
	private String password;
	private String role;
	
	public User(){
		
	}

	public User(String userName, String password, String role) {
		this.setUserName(userName);
		this.setPassword(password);
		this.setRole(role);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
