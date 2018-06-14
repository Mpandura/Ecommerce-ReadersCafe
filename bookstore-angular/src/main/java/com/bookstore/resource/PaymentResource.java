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
import com.bookstore.domain.UserBilling;
import com.bookstore.domain.UserPayment;
import com.bookstore.service.UserPaymentService;
import com.bookstore.service.UserService;
/*This class implements the payment related methods like user adding payment details , updating them, remove them, setting one of them as default, getting the list of payment details that are saved*/
/*This is called when /payment is http method is called*/
/*This implements methods like addNewCreditCardPost(), getUSerPaymentList(), removePaymentPost(), setDefaultPaymentPost()  */
@RestController
@RequestMapping("/payment")
public class PaymentResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserPaymentService userPaymentService;
/*This method is implemented when http method /payment/add is called and posts the given card details in respective table*/
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity addNewCreditCardPost (
			@RequestBody UserPayment userPayment,
			Principal principal) {
		User user = userService.findByUsername(principal.getName());
		
		UserBilling userBilling = userPayment.getUserBilling();
		
		userService.updateUserBilling(userBilling, userPayment, user);
		
		return new ResponseEntity("Payment Added(Updated) Successfully!", HttpStatus.OK);
	}
	/*This method is implemented when http method /payment/remove is called and removes the given card details in respective table*/

	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public ResponseEntity removePaymentPost(
			@RequestBody String id,
			Principal principal
			){
//		User user = userService.findByUsername(principal.getName());
		
		userPaymentService.removeById(Long.valueOf(id));
		
		return new ResponseEntity("Payment Removed Successfully!", HttpStatus.OK);
	}
	/*This method is implemented when http method /payment/setDefault is called and posts the given card as default for his transactios in respective table*/

	@RequestMapping(value="/setDefault", method=RequestMethod.POST)
	public ResponseEntity setDefaultPaymentPost(
			@RequestBody String id,
			Principal principal
			){
		User user = userService.findByUsername(principal.getName());
		
		userService.setUserDefaultPayment(Long.parseLong(id), user);
		
		return new ResponseEntity("Payment Removed Successfully!", HttpStatus.OK);
	}
	/*This method is implemented when http method /payment/getUserPaymentList is called and returns the list of all saved card details from respective table*/

	@RequestMapping("/getUserPaymentList")
	public List<UserPayment> getUserPaymentList(
			Principal principal
			){
		User user = userService.findByUsername(principal.getName());
		
		List<UserPayment> userPaymentList = user.getUserPaymentList();
		
		return userPaymentList;
	}
	
}
