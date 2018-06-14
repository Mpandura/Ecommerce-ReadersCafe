package com.bookstore.service;

import com.bookstore.domain.BillingAddress;
import com.bookstore.domain.Order;
import com.bookstore.domain.Payment;
import com.bookstore.domain.ShippingAddress;
import com.bookstore.domain.ShoppingCart;
import com.bookstore.domain.User;
/*This is a interface that provides abstract methods for order service like createOrder() which is implemented by OrderServiceImpl*/

public interface OrderService {
	
	Order createOrder(
			ShoppingCart shoppingCart,
			ShippingAddress shippingAddress,
			BillingAddress billingAddress,
			Payment payment,
			String shippingMethod,
			User user
	);

}
