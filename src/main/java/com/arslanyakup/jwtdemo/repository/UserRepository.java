package com.arslanyakup.jwtdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arslanyakup.jwtdemo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String username);

	public User findByEmail(String email);

}
