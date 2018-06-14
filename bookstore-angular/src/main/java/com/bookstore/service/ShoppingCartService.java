package com.bookstore.service;

import com.bookstore.domain.ShoppingCart;
/*This is a interface that provides abstract methods for shopping cart service like updateShoppingCart(),clearShoppingCart() which are implemented by SshoppingCartServiceImpl*/

public interface ShoppingCartService {
	
	ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
	
	void clearShoppingCart(ShoppingCart shoppingCart);

}
