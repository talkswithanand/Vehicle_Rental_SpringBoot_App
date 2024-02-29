package com.ff.rentalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ff.rentalApp.dto.ResponseStructure;
import com.ff.rentalApp.entity.User;
import com.ff.rentalApp.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Operation(description ="Signup details for merchant/customer", summary = "Signup details for merchant/customer")
	@ApiResponse(description = "Create User details", responseCode = "200")
	@PostMapping("/signup")
	public ResponseEntity<ResponseStructure<User>> userSignup(@RequestBody User user){
		return userService.saveUser(user);
	}
	
	
	@Operation(description ="Login as merchant/customer", summary = "Login as merchant/customer")
	@ApiResponse(description = "Find User details", responseCode = "201")
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<User>>userLogin(@RequestParam String email, @RequestParam String password){
		return userService.findUser(email, password);
	}
	
	@Operation(description ="Update profile of merchant/customer", summary = "Update profile of merchant/customer")
	@ApiResponse(description = "Update user details", responseCode = "201")
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<User>>updateUser(@PathVariable int id, @RequestBody User user){
		return userService.updateUser(id,user);
	}

}
