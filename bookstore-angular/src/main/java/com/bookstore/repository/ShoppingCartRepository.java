package com.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.domain.ShoppingCart;

//Interface to enable to create, read, update and delete details related to ShoppingCart
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long>{

}
