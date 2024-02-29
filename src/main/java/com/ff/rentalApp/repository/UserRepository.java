  package com.ff.rentalApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ff.rentalApp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {


	User findByEmailAndPassword(String email, String password);

	
}
