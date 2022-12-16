package com.example.TPtrace.model;

import java.util.List;

import java.util.Set;

import javax.persistence.*;

import org.springframework.context.annotation.Scope;
@Entity
public class User {
	@Id
	private String user_id;
	@Column
	private String name;
	private int age;
	private String email;
	private String password;
	
	public User() {
		super();
	}

	public User(String user_id, String name, int age, String email, String password) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.password = password;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Product [user_id=" + user_id + ", name=" + name + ", age=" + age + ", email=" + email + ", password="
				+ password + "]";
	}
	
}
