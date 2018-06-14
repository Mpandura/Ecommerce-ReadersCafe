package com.bookstore.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.domain.Book;
import com.bookstore.domain.BookToCartItem;
import com.bookstore.domain.CartItem;
import com.bookstore.domain.ShoppingCart;
import com.bookstore.domain.User;
import com.bookstore.repository.BookToCartItemRepository;
import com.bookstore.repository.CartItemRepository;
import com.bookstore.service.CartItemService;
/* This is the implementation of Cart service,this covers the cart page operations and it implements methods like addbooktocart(), removecartitem(),save(),findByShoppingCart(), updateCartItem(),remove one()*/
@Service
public class CartItemServiceImpl implements CartItemService{
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private BookToCartItemRepository bookToCartItemRepository;

	/* This method implements the adding book to the cart and uses bookToCartItemRepository and returns the new cart item with adding new books*/
	public CartItem addBookToCartItem(Book book, User user, int qty) {
		List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
		
		for (CartItem cartItem : cartItemList) {
			if (book.getId() == cartItem.getBook().getId()) {
				cartItem.setQty(cartItem.getQty()+qty);
				cartItem.setSubtotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));
				cartItemRepository.save(cartItem);
				return cartItem;
			}
		}
		
		CartItem cartItem = new CartItem();
		cartItem.setShoppingCart(user.getShoppingCart());
		cartItem.setBook(book);
		cartItem.setQty(qty);
		cartItem.setSubtotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));
		
		cartItem =cartItemRepository.save(cartItem);
		
		BookToCartItem bookToCartItem = new BookToCartItem();
		bookToCartItem.setBook(book);
		bookToCartItem.setCartItem(cartItem);
		bookToCartItemRepository.save(bookToCartItem);
		
		return cartItem;
	}
	/*This method implements removing a certain cart item from cart_item_list*/
	@Transactional
	public void removeCartItem(CartItem cartItem) {
		bookToCartItemRepository.deleteByCartItem(cartItem);
		cartItemRepository.delete(cartItem);
	}
	/*This method finds the shopping cart object and returns it as list of items*/
	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}
	/*This method implements when you update certain cart list like increasing or decreasing the amount of item you would like to buy*/
	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal bigDecimal = new BigDecimal(cartItem.getBook().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));
		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		cartItem.setSubtotal(bigDecimal);
		
		cartItemRepository.save(cartItem);
		
		return cartItem;
		
	}
	/*This method is to find a cart item by id of the item*/
	public CartItem findById(Long id) {
		return cartItemRepository.findOne(id);
	}
	/*This method is to save the cart items in the cartItemRepository*/
	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}
	
//	public List<CartItem> findByOrder(Order order) {
//		return cartItemRepository.findByOrder(order);
//	}
}
