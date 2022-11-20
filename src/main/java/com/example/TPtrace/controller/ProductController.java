package com.example.TPtrace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TPtrace.model.Product;
import com.example.TPtrace.service.ProductService;

@Controller
//@RestController
public class ProductController {
	
	@Autowired
	ProductService service;
	
//	@RequestMapping(value="/index")
//	@ResponseBody
	@GetMapping("/index")
	public String index() {
		return "index"; // name of html file without extension
	}
	
	@RequestMapping(value="/productList", method=RequestMethod.GET)
	public List<Product> fetchProductList() {
		return service.fetchProductList();
	}

}
