package com.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.domain.UserPayment;
import com.bookstore.repository.UserPaymentRepository;
import com.bookstore.service.UserPaymentService;
/* This is the implementation of USer payment service,this covers the user payment operations and it implements methods like findById(),removeById()*/
@Service
public class UserPaymentServiceImpl implements UserPaymentService {
	@Autowired
	private UserPaymentRepository userPaymentRepository;
	/*this finds by id and gets the user payment details*/
	public UserPayment findById(Long id) {
		return userPaymentRepository.findOne(id);
	}
	/*this finds by id and removes the user payment details*/
	public void removeById(Long id) {
		userPaymentRepository.delete(id);
	}
	
}
