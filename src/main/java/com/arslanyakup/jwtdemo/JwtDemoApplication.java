package com.arslanyakup.jwtdemo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.arslanyakup.jwtdemo.model.User;
import com.arslanyakup.jwtdemo.security.model.Role;
import com.arslanyakup.jwtdemo.security.model.UserRole;
import com.arslanyakup.jwtdemo.service.UserService;
import com.arslanyakup.jwtdemo.util.SecurityUtility;

@SpringBootApplication
@ComponentScan(basePackages = "com.arslanyakup")
public class JwtDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JwtDemoApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {

		Role userRole = new Role();
		userRole.setName("USER");
		Role adminRole = new Role();
		adminRole.setName("ADMIN");

		User user = new User();
		user.setUsername("arslanyakup");
		user.setEmail("arslanyakup56@gmail.com");
		user.setPassword(SecurityUtility.passwordEncoder().encode("1234"));

		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, userRole));
		userRoles.add(new UserRole(user, adminRole));
		user.setUserRoles(userRoles);
		userService.createUser(user, userRoles);

	}

}
