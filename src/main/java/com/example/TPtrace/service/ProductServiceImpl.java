package com.example.TPtrace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TPtrace.exception.NoProductWithId;
import com.example.TPtrace.exception.ProductSameIdExist;
import com.example.TPtrace.model.Product;
import com.example.TPtrace.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public Product saveProduct(Product product) throws ProductSameIdExist {
		String id = product.getProduct_id();
		for (Product p : fetchProductList()) {
			if (p.getProduct_id().equals(id)) {
				throw new ProductSameIdExist("Product already exists !");
			}
		}
		return productRepository.save(product);
	}

	@Override
	public List<Product> fetchProductList() {
		return productRepository.findAll();
	}

	@Override
	public Product fetchProductById(String product_id) throws NoProductWithId {
		Optional<Product> p = productRepository.findById(product_id);
		if (p.isPresent()) {
			return p.get();
		} else {
			throw new NoProductWithId("Product doesn't exist, can't fetch !");
		}
	}

	@Override
	public Product updateProduct(Product product) throws NoProductWithId {
		Optional<Product> oldProduct = productRepository.findById(product.getProduct_id());
	
		if (oldProduct.isPresent()) {
			return productRepository.save(product);
		} else {
			throw new NoProductWithId("Product doesn't exist, can't update !");
		}
	}

	@Override
	public void deleteProductById(String product_id) throws NoProductWithId {
		Optional<Product> oldProduct = productRepository.findById(product_id);
		if (oldProduct.isPresent()) {
			productRepository.delete(oldProduct.get());
		} else {
			throw new NoProductWithId("Product doesn't exist, can't delete !");
		}
	}

}
