package com.example.TPtrace.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TPtrace.model.User;
import com.example.TPtrace.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
public class UserController {
	private static Logger loggerWithJsonLayout = LogManager.getLogger(UserController.class.getName());
	
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
	public String toUserProduct(@RequestParam String selectedUser, HttpSession session) {
		System.out.println("Selected user id: "+selectedUser);
		User user = service.fetchUserById(selectedUser);
		session.setAttribute("selectedUser", user);
		loggerWithJsonLayout.info("Login user: "+user);
		return "redirect:/productPage";
	}
	
	
}
