package com.ff.rentalApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.rentalApp.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

}
