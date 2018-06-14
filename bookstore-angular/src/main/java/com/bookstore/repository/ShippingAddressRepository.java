package com.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.domain.ShippingAddress;

//Interface to enable to create, read, update and delete details related to ShippingAddress
public interface ShippingAddressRepository extends CrudRepository<ShippingAddress, Long> {
	
}
