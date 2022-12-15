package com.example.TPtrace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TPtrace.exception.NoProductWithId;
import com.example.TPtrace.exception.NoUserWithId;
import com.example.TPtrace.exception.ProductSameIdExist;
import com.example.TPtrace.exception.UserSameIdExist;
import com.example.TPtrace.model.Product;
import com.example.TPtrace.model.User;
import com.example.TPtrace.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(User user) {
		String id = user.getUser_id();
		for (User u : fetchUserList()) {
			if (u.getUser_id().equals(id)) {
				throw new UserSameIdExist("User already exists !");
			}
		}
		return userRepository.save(user);
	}

	@Override
	public List<User> fetchUserList() {
		return userRepository.findAll();
	}

	@Override
	public User fetchUserById(String user_id) {
		Optional<User> u = userRepository.findById(user_id);
		if (u.isPresent()) {
			return u.get();
		} else {
			throw new NoUserWithId("User doesn't exist, can't fetch !");
		}
	}


}
