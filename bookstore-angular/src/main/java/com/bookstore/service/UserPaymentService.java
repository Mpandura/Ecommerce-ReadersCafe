package com.bookstore.service;

import com.bookstore.domain.UserPayment;
/*This is a interface that provides abstract methods for User Payment service like findById(),removeById() which are implemented by UserPaymentServiceImpl*/

public interface UserPaymentService {
	UserPayment findById(Long id);
	
	void removeById(Long id);
}
