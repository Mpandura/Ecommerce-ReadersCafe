package com.bookstore.utility;

import java.util.Locale;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.bookstore.domain.Order;
import com.bookstore.domain.User;

/* This is the class that provides template for email confirmation (order confirmation, new user login password) and this uses some predefined methods in spring framework*/
@Component
public class MailConstructor {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private TemplateEngine templateEngine;
	/* Method that provides template to send when a new user creates his account for email verification where a username and password are sent */ 
	public SimpleMailMessage constructNewUserEmail(User user, String password) {
		String message="\nPlease use the following credentials to log in and edit your personal information including your own password."
				+ "\nUsername:"+user.getUsername()+"\nPassword:"+password;
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("Reader's Cafe - New User");
		email.setText(message);
		email.setFrom(env.getProperty("support.email"));
		return email;
	}
	/* Method that provides template to send  for order confirmation which has details of the order */
	public MimeMessagePreparator constructOrderConfirmationEmail (User user, Order order, Locale locale) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("user", user);
		context.setVariable("cartItemList", order.getCartItemList());
		String text = templateEngine.process("orderConfirmationEmailTemplate", context);
		
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(user.getEmail());
				email.setSubject("Order Confirmation - "+order.getId());
				email.setText(text,true);
				email.setFrom(new InternetAddress("ray.deng83@gmail.com"));
			}
		};
		
		return messagePreparator;
	}

}
