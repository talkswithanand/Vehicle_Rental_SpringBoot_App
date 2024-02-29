package com.ff.rentalApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.rentalApp.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
