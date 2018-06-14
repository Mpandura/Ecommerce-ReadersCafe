package com.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.domain.BookToCartItem;
import com.bookstore.domain.CartItem;

//Interface to enable to create, read, update and delete details related to BookToCartItem
public interface BookToCartItemRepository extends CrudRepository<BookToCartItem, Long>{
	void deleteByCartItem(CartItem cartItem);
}
