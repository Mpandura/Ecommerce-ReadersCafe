package com.bookstore.resource;

import java.util.Collections;
import java.util.Map;


import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.service.UserService;
/*This class implements the login service check whether the service is connected to the port and check session with redis and logout services*/
@RestController
public class LoginResource {
	@Autowired
	private UserService userService;
	/*This is implemented when http method /token is called and checks whether the remotehost is connected to the port number or not and returns a singleton session id when user logins which experies if the user logsout or after certain time of inactivity*/
	@RequestMapping("/token")
	public Map<String, String> token(HttpSession session, HttpServletRequest request) {
		System.out.println(request.getRemoteHost());
		
		String remoteHost = request.getRemoteHost();
		int portNumber = request.getRemotePort();
		
		System.out.println(remoteHost+":"+portNumber);
		System.out.println(request.getRemoteAddr());
		
		return Collections.singletonMap("token", session.getId());
	}
	/*This is implemented when we check for the session of user, it checks whether the token is still active or not*/
	@RequestMapping("/checkSession")
	public ResponseEntity checkSession() {
		return new ResponseEntity("Session Active!", HttpStatus.OK);
	}
	/*This is implemented when user wants to logout from the webpage it clears the required details and inactivates the session id so that he cant get to previous page by clicking back on web site*/
	@RequestMapping(value="/user/logout", method=RequestMethod.POST)
	public ResponseEntity logout(){
		SecurityContextHolder.clearContext();
		return new ResponseEntity("Logout Successfully!", HttpStatus.OK);
	}
	
}
