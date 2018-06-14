package com.bookstore.resource;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.domain.Order;
import com.bookstore.domain.User;
import com.bookstore.service.UserService;
/*This class implements the orderresource page where the http method /order gets implemented under the user and shows the list of orders he has placed with the status of order*/
/*It implements the method getOrderList()*/
@RestController
@RequestMapping("/order")
public class OrderResource {
	
	@Autowired
	private UserService userService;
	/*when the users request orders placed by clicking users/getOrderList it returns the list of orders that are present in his respective table which are displayed on the web page with status of each order*/
	@RequestMapping("/getOrderList")
	public List<Order> getOrderList(Principal principal) {
		User user = userService.findByUsername(principal.getName());
		List<Order> orderList = user.getOrderList();
		
		return orderList;
	}

}
