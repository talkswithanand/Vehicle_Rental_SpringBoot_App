package com.ff.rentalApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ff.rentalApp.entity.Address;
import com.ff.rentalApp.repository.AddressRepository;

@Repository
public class AddressDao {
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}
}
