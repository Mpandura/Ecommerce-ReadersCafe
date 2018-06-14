package com.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.domain.UserBilling;

//Interface to enable to create, read, update and delete details related to UserBilling
public interface UserBillingRepository extends CrudRepository<UserBilling, Long> {

}
