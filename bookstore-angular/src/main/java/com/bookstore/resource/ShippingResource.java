package com.bookstore.resource;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.domain.User;
import com.bookstore.domain.UserShipping;
import com.bookstore.service.UserService;
import com.bookstore.service.UserShippingService;
/*This class implements the shipping service related things such as adding shipping details , or getting shipping details, remove the saved shipping address , setting the default address for all orders*/
/*This is implemented when /shipping http method is called*/
/*implements methods like addNewUserShippingPost(), getUserShippingList(), removeUserShippingPost(), setDefaultUserSshippingPost() */
@RestController
@RequestMapping("/shipping")
public class ShippingResource {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserShippingService userShippingService;
	/*This method is implemented when /shipping/add http method is called and adds the details of shipping when a new user is created and saves them in respective table in database*/
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity addNewUserShippingPost(
			@RequestBody UserShipping userShipping, Principal principal
			) {
		User user = userService.findByUsername(principal.getName());
		userService.updateUserShipping(userShipping, user);
		
		return new ResponseEntity("Shipping Added(Updated) Successfully!", HttpStatus.OK);
	}
	/*This method is implemented when /shipping/getUserShippingList http method is called and returns the list of details of shipping when a user requests from respective table in database*/
	@RequestMapping("/getUserShippingList")
	public List<UserShipping> getUserShippingList(
			Principal principal
			){
		User user = userService.findByUsername(principal.getName());
		List<UserShipping> userShippingList = user.getUserShippingList();
		
		return userShippingList;
	}
	/*This method is implemented when /shipping/remove http method is called and removes the details of shipping from respective table in database*/
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public ResponseEntity removeUserShippingPost(
			@RequestBody String id,
			Principal principal
			) {
		userShippingService.removeById(Long.parseLong(id));
		return new ResponseEntity("Shipping Removed Successfully!", HttpStatus.OK);
	}
	/*This method is implemented when /shipping/setDefault http method is called and sets the details of shipping as default and used for further use*/
	@RequestMapping(value="/setDefault", method=RequestMethod.POST)
	public ResponseEntity setDefaultUserShippingPost(
			@RequestBody String id, Principal principal
			){
		User user = userService.findByUsername(principal.getName());
		userService.setUserDefaultShipping(Long.parseLong(id), user);
		
		return new ResponseEntity("Set Default Shipping Successfully!", HttpStatus.OK);
	}
}
