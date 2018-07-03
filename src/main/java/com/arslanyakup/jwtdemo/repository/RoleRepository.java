package com.arslanyakup.jwtdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arslanyakup.jwtdemo.security.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
