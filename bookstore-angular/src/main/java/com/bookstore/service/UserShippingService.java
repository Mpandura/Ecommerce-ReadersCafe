package com.bookstore.service;

import com.bookstore.domain.UserShipping;
/*This interface implements the user shipping details and have abstract method like findById(), removeById(), which are implemented by USerShippingServiceImpl*/
public interface UserShippingService {
	
	UserShipping findById(Long id);
	
	void removeById(Long id);

}
