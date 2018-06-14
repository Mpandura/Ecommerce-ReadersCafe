package com.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.domain.security.Role;

//Interface to enable to create, read, update and delete details related to Roles
public interface RoleRepository extends CrudRepository<Role, Long> {

}
