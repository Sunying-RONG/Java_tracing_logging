package com.example.TPtrace.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TPtrace.exception.ProductSameIdExist;
import com.example.TPtrace.model.Product;
import com.example.TPtrace.model.User;
import com.example.TPtrace.service.ProductService;

//@RestController
@Controller
public class ProductController {
	private static Logger loggerWithJsonLayout = LogManager.getLogger(ProductController.class.getName());
	
	@Autowired
	ProductService service;

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
	public String createProduct(@ModelAttribute Product product, HttpSession session) {
		service.saveProduct(product);
		loggerWithJsonLayout.info("Write action (create) by: "+session.getAttribute("selectedUser"));
		return "productForm";
	}
	
	@PostMapping("/chooseProduct")
	public String chooseProduct(@RequestParam String selectedProduct, Model model, HttpSession session) {
		System.out.println("Selected product id: "+selectedProduct);
		Product product = service.fetchProductById(selectedProduct);
		model.addAttribute("selectedProduct", product);
		if (product.getProduct_id().equals("00004")) {
			loggerWithJsonLayout.info("Read most expensive object by: "+session.getAttribute("selectedUser"));
		} else {
			loggerWithJsonLayout.info("Read action by: "+session.getAttribute("selectedUser"));
		}
		return "userProduct";
	}
	
	@GetMapping("/deleteSelectedProduct/{id}")
	public String deleteSelectedProduct(@PathVariable("id") String id, HttpSession session) {
		service.deleteProductById(id);
		List<Product> products = service.fetchProductList();
		session.setAttribute("products", products);
		loggerWithJsonLayout.info("Write action (delete) by: "+session.getAttribute("selectedUser"));
		return "userProduct";
	}
	
	@GetMapping("/updateSelectedProduct/{id}")
	public String updateSelectedProduct(@PathVariable("id") String id, Model model) {
		Product product = service.fetchProductById(id);
		model.addAttribute("selectedProduct", product);
		return "updateProductForm";
	}
	
	@PostMapping("/updateProduct")
	public String updateProduct(@ModelAttribute Product selectedProduct, HttpSession session) {
		service.updateProduct(selectedProduct);
		loggerWithJsonLayout.info("Write action (update) by: "+session.getAttribute("selectedUser"));
		return "userProduct";
	}

}
