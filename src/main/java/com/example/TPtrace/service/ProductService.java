package com.example.TPtrace.service;

import java.util.List;
import java.util.Optional;

import com.example.TPtrace.model.Product;


public abstract interface ProductService {
	// save operation
	Product saveProduct(Product product);
	// read operation
	List<Product> fetchProductList();
	Product fetchProductById(String product_id);
	// update operation
	Product updateProduct(Product product, String product_id) throws Exception;
	// delete operation
	void deleteProductById(String product_id);
}