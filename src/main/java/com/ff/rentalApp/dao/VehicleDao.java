package com.ff.rentalApp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ff.rentalApp.entity.User;
import com.ff.rentalApp.entity.Vehicle;
import com.ff.rentalApp.repository.VehicleRepository;

@Repository
public class VehicleDao {
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private UserDao userDao;

	// save vehicle
	public Vehicle saveVehicle(int id, Vehicle vehicle) {
		User receivedUser = userDao.findUserbyId(id);
		if (receivedUser != null && receivedUser.getUserRole().equalsIgnoreCase("merchant")) {
			return vehicleRepository.save(vehicle);

		} else
			return null;
	}

	// find the vehicle by id
	public Vehicle findVehicleById(int id) {
		Optional<Vehicle> option = vehicleRepository.findById(id);
		if (option.isPresent()) {
			Vehicle vehicle = option.get();
			return vehicle;
		} else
			return null;
	}

	// delete the vehicle
	public void deleteVehicle( int vehicleId) {
	 vehicleRepository.deleteById(vehicleId);
	}

}
