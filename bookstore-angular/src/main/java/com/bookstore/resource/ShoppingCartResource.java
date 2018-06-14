package com.bookstore.resource;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.domain.Book;
import com.bookstore.domain.CartItem;
import com.bookstore.domain.ShoppingCart;
import com.bookstore.domain.User;
import com.bookstore.service.BookService;
import com.bookstore.service.CartItemService;
import com.bookstore.service.ShoppingCartService;
import com.bookstore.service.UserService;
/*This class is implemented to deal with all the cart related activities, such as assing new items, getting the cart item list, removing items from cart and updating them*/
/*This is implemented when /cart http method is called*/
/*this implements methods like addItem(), getCartItemList(), getShoppingCart(), removeItem(),updateCartItem()*/
@RestController
@RequestMapping("/cart")
public class ShoppingCartResource {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	/*This method is implemented when /cart/add http method is called and adds the list of items to the cart repository */
	@RequestMapping("/add")
	public ResponseEntity addItem (
			@RequestBody HashMap<String, String> mapper, Principal principal
			){
		String bookId = (String) mapper.get("bookId");
		String qty = (String) mapper.get("qty");
		
		User user = userService.findByUsername(principal.getName());
		Book book = bookService.findOne(Long.parseLong(bookId));
		
		if(Integer.parseInt(qty) > book.getInStockNumber()) {
			return new ResponseEntity("Not Enough Stock!", HttpStatus.BAD_REQUEST);
		}
		
		CartItem cartItem = cartItemService.addBookToCartItem(book, user, Integer.parseInt(qty));
		
		return new ResponseEntity("Book Added Successfully!", HttpStatus.OK);
	}
	/*This method is implemented when /cart/getCartItemList http method is called and returns the list of items from the cart repository */
	@RequestMapping("/getCartItemList")
	public List<CartItem> getCartItemList(Principal principal) {
		User user = userService.findByUsername(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		return cartItemList;
	}
	/*This method is implemented when /cart/getShoppingCart http method is called and returns the list of items in the shopping cart related to user */
	@RequestMapping("/getShoppingCart")
	public ShoppingCart getShoppingCart(Principal principal) {
		User user = userService.findByUsername(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		return shoppingCart;
	}
	/*This method is implemented when /cart/removeItem http method is called and removes the item from cart repository */
	@RequestMapping("/removeItem")
	public ResponseEntity removeItem(@RequestBody String id) {
		cartItemService.removeCartItem(cartItemService.findById(Long.parseLong(id)));
		
		return new ResponseEntity("Cart Item Removed Successfully!", HttpStatus.OK);
	}
	/*This method is implemented when /cart/updateCartItem http method is called and updates the item in  the cart repository */
	@RequestMapping("/updateCartItem")
	public ResponseEntity updateCartItem(
			@RequestBody HashMap<String, String> mapper
			){
		String cartItemId = mapper.get("cartItemId");
		String qty = mapper.get("qty");
		
		CartItem cartItem = cartItemService.findById(Long.parseLong(cartItemId));
		cartItem.setQty(Integer.parseInt(qty));
		
		cartItemService.updateCartItem(cartItem);
		
		return new ResponseEntity("Cart Item Updated Successfully!", HttpStatus.OK);
	}
	
}
