package com.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.domain.Order;
import com.bookstore.domain.User;

//Interface to enable to create, read, update and delete details related to Orders
public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findByUser(User user);
}
