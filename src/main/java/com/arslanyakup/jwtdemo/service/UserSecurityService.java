package com.arslanyakup.jwtdemo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arslanyakup.jwtdemo.model.User;
import com.arslanyakup.jwtdemo.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserSecurityService.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username);

		if (null == user) {
			LOGGER.warn("Username  {} not found!", username);
			throw new UsernameNotFoundException("Username: " + username + " not found!");
		} else {
			return createSpringSecurityUser(user);
		}
	}

	private UserDetails createSpringSecurityUser(User user) {
		List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}

}
