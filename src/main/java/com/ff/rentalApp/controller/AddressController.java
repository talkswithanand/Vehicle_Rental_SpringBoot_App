package com.ff.rentalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ff.rentalApp.dto.ResponseStructure;
import com.ff.rentalApp.entity.Address;
import com.ff.rentalApp.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@PostMapping("/address/{userId}")
	ResponseEntity<ResponseStructure<Address>>saveAddress(@RequestBody Address address, @PathVariable int userId){
		return addressService.saveAddress(address, userId);
	}
	@PutMapping("/address/{userId}")
	ResponseEntity<ResponseStructure<Address>>updateAddress(@RequestBody Address address, @PathVariable int userId){
		return addressService.updateAddress(address, userId);
	}
}
