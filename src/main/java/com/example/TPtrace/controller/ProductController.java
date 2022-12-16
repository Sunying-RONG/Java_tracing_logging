package com.example.TPtrace.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TPtrace.model.Product;
import com.example.TPtrace.model.User;
import com.example.TPtrace.service.ProductService;
import com.example.TPtrace.service.UserService;

//@RestController
@Controller
public class ProductController {
	
	@Autowired
	ProductService service;
	@Autowired
	UserService userService;

//	@RequestMapping(value="/index")
//	@ResponseBody
	
	
//	@RequestMapping(value="/productList", method=RequestMethod.GET)
//	public List<Product> fetchProductList() {
//		return service.fetchProductList();
//	}
	
	@GetMapping("/productPage")
	public String productPage(Model model, HttpSession session) {
//		System.out.println("===="+selectedUser);
//		User user = userService.fetchUserById(selectedUser);
//		session.setAttribute("selectedUser", user);
		List<Product> products = service.fetchProductList();
		session.setAttribute("products", products);
		return "userProduct"; // name of html file without extension
	}
	
	@GetMapping("/goToProductForm")
	public String toProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "productForm"; // name of html file without extension
	}
	
	@PostMapping("/createProduct")
	public String createProduct(@ModelAttribute Product product) {
		service.saveProduct(product);
		return "productForm";
	}
	
	@PostMapping("/chooseProduct")
	public String chooseProduct(@RequestParam String selectedProduct, Model model) {
		System.out.println("Selected product id: "+selectedProduct);
		Product product = service.fetchProductById(selectedProduct);
		model.addAttribute("selectedProduct", product);
		return "userProduct";
	}

}
