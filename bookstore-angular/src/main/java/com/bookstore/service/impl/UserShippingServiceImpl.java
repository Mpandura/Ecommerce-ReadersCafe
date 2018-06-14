package com.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.domain.UserShipping;
import com.bookstore.repository.UserShippingRepository;
import com.bookstore.service.UserShippingService;
/*This class is to implement user shipping service and implements method like findById(),removeByID()*/
@Service
public class UserShippingServiceImpl implements UserShippingService{
	
	@Autowired
	private UserShippingRepository userShippingRepository;
	/*This is find user shipping details by id*/
	public UserShipping findById(Long id) {
		return userShippingRepository.findOne(id);
	}
	/*This is to remove shipping details by id*/
	public void removeById(Long id) {
		userShippingRepository.delete(id);
	}
}
