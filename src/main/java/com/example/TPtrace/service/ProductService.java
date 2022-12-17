package com.example.TPtrace.service;

import java.util.List;
import java.util.Optional;

import com.example.TPtrace.exception.NoProductWithId;
import com.example.TPtrace.exception.ProductSameIdExist;
import com.example.TPtrace.model.Product;


public abstract interface ProductService {
	// save operation
	Product saveProduct(Product product) throws ProductSameIdExist;
	// read operation
	List<Product> fetchProductList();
	Product fetchProductById(String product_id) throws NoProductWithId;
	// update operation
	Product updateProduct(Product product) throws NoProductWithId;
	// delete operation
	void deleteProductById(String product_id) throws NoProductWithId;
}