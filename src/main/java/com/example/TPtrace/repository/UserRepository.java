package com.example.TPtrace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TPtrace.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
