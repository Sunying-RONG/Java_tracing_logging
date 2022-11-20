package com.example.TPtrace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.TPtrace.model.User;
import com.example.TPtrace.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/userForm")
	public String toUserForm(Model model) {
		model.addAttribute("user", new User());
		return "userForm"; // name of html file without extension
	}
	
	@PostMapping("/createUser")
	public String createUser(@ModelAttribute User user) {
		service.saveUser(user);
		return "userForm";
	}
}
