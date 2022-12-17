package com.example.TPtrace.service;

import java.util.List;
import java.util.Optional;

import com.example.TPtrace.exception.NoUserWithId;
import com.example.TPtrace.exception.UserSameIdExist;
import com.example.TPtrace.model.User;

public abstract interface UserService {
	
	// save operation
	User saveUser(User user) throws UserSameIdExist;
	// read operation
	List<User> fetchUserList();
	User fetchUserById(String user_id) throws NoUserWithId;
	// update operation
//	User updateUser(User user, String user_id);
	// delete operation
//	void deleteUserById(String user_id);
}
