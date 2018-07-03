package com.arslanyakup.jwtdemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {

	public String signin(String username, String password);

	public List<String> userRoles();
}
