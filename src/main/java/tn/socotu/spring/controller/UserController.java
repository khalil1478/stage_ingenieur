package tn.socotu.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.socotu.spring.entities.Remboursement;
import tn.socotu.spring.entities.User;
import tn.socotu.spring.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService; 
	
	
	
	// http://localhost:8080/login/{email}/{password}
	@PostMapping("/login/{email}/{password}")
	public void login( @PathVariable("email") int email,@PathVariable("password") int password ) 
	{
		userService.login(email,password);
		
	
	}
}
