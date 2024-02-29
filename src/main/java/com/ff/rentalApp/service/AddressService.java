package com.ff.rentalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.rentalApp.dao.UserDao;
import com.ff.rentalApp.dto.ResponseStructure;
import com.ff.rentalApp.entity.Address;
import com.ff.rentalApp.entity.User;

@Service
public class AddressService {
	
	@Autowired
	private UserDao userDao;
	
	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address, int id){
		User user = userDao.findUserbyId(id);
		user.setAddress(address);
		User saveUser = userDao.saveUser(user);
		
		ResponseStructure<Address>rs=new ResponseStructure<Address>();
		rs.setData(saveUser.getAddress());
		rs.setMessage("Address inserted successfully");
		rs.setStatusCode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<ResponseStructure<Address>>(rs,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Address>> updateAddress(Address address, int id) {
		
		User user = userDao.findUserbyId(id);
		Address address1 = user.getAddress();
		address1.setCity(address.getCity());
		address1.setCountry(address.getCountry());
		address1.setState(address.getState());
		address1.setStreet(address.getStreet());
		
		user.setAddress(address1);
		userDao.saveUser(user);
		
		ResponseStructure<Address>rs=new ResponseStructure<Address>();
		rs.setData(address1);
		rs.setMessage("Address inserted successfully");
		rs.setStatusCode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<ResponseStructure<Address>>(rs,HttpStatus.CREATED);
	}
}
