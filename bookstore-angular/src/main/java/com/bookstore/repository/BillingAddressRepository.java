package com.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.domain.BillingAddress;

//Interface to enable to create, read, update and delete details related to BillingAddress
public interface BillingAddressRepository extends CrudRepository<BillingAddress, Long>{

}
