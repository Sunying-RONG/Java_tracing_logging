package com.example.TPtrace.model;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Product {
	@Id
	private String product_id;
	@Column
	private String name;
	private float price;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private String expiration_date;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String product_id, String name, float price, String expiration_date) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.expiration_date = expiration_date;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", name=" + name + ", price=" + price + ", expiration_date="
				+ expiration_date + "]";
	}

	
}
