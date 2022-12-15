package com.example.TPtrace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TPtrace.model.User;
import com.example.TPtrace.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<User> users = service.fetchUserList();
		model.addAttribute("users", users);
		return "index"; // name of html file without extension
	}
	
	@GetMapping("/goToUserForm")
	public String toUserForm(Model model) {
		model.addAttribute("user", new User());
		return "userForm"; // name of html file without extension
	}
	
	@PostMapping("/createUser")
	public String createUser(@ModelAttribute User user) {
		service.saveUser(user);
		return "userForm";
	}
	
	@PostMapping("/goToUserProductForm")
	public String toUserProduct(@RequestParam String selectedUser, Model model) {
		System.out.println("===="+selectedUser);
		User user = service.fetchUserById(selectedUser);
		model.addAttribute("selectedUser", user);
		return "userProduct";
	}
	
	
}
