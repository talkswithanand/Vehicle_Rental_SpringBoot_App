package com.ff.rentalApp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ff.rentalApp.entity.User;
import com.ff.rentalApp.repository.UserRepository;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User findUser(String email, String password) {
		return userRepository.findByEmailAndPassword(email , password);
	}

	public User findUserbyId(int id) {
		Optional<User>opt= userRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	
}
