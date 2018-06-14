package com.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.domain.UserShipping;

//Interface to enable to create, read, update and delete details related to UserShipping
public interface UserShippingRepository extends CrudRepository<UserShipping, Long> {

}
