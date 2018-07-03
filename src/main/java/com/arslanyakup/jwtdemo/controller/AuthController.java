package com.arslanyakup.jwtdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arslanyakup.jwtdemo.dto.PersonLoginDTO;
import com.arslanyakup.jwtdemo.service.AuthService;

@RestController
@RequestMapping(value = "/oauth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestBody PersonLoginDTO person) {
		return authService.signin(person.getUsername(), person.getPassword());
	}

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ResponseEntity<List<String>> info() {
		return new ResponseEntity(authService.userRoles(), HttpStatus.OK);
	}

}
