package com.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.domain.Payment;

//Interface to enable to create, read, update and delete details related to Payments
public interface PaymentRepository extends CrudRepository<Payment, Long>{

}
