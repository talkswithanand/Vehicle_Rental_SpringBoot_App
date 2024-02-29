package com.ff.rentalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.rentalApp.dao.UserDao;
import com.ff.rentalApp.dao.VehicleDao;
import com.ff.rentalApp.dto.ResponseStructure;
import com.ff.rentalApp.entity.User;
import com.ff.rentalApp.entity.Vehicle;
import com.ff.rentalApp.exception.ApplicationException;

@Service
public class VehicleService {
	@Autowired
	private VehicleDao vehicleDao;
	@Autowired
	private UserDao userDao;

	// save vehicle
	public ResponseEntity<ResponseStructure<Vehicle>> saveVehicle(int id, Vehicle vehicle) {
		Vehicle receivedVehicle = vehicleDao.saveVehicle(id, vehicle);
		if (receivedVehicle != null) {
			ResponseStructure<Vehicle> response = new ResponseStructure<Vehicle>();
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("vehicle is added");
			response.setData(receivedVehicle);
			return new ResponseEntity<ResponseStructure<Vehicle>>(response, HttpStatus.CREATED);
		} else
			throw new ApplicationException("merchant id doesnt exist..!");

	}

	// update vehicle
	public ResponseEntity<ResponseStructure<Vehicle>> updateVehicle(int userId, int vehicleId, Vehicle vehicle) {
		User receivedUser = userDao.findUserbyId(userId);

		if (receivedUser != null && "merchant".equalsIgnoreCase(receivedUser.getUserRole())) {
			Vehicle updatedVehicle = vehicleDao.findVehicleById(vehicleId);
			if (updatedVehicle != null) {
				updatedVehicle.setType(vehicle.getType());
				updatedVehicle.setLocation(vehicle.getLocation());
				updatedVehicle.setAvailable(vehicle.isAvailable());
				updatedVehicle.setModel(vehicle.getModel());
				updatedVehicle.setVehicleNumber(vehicle.getVehicleNumber());

				Vehicle savedVehicle = vehicleDao.saveVehicle(userId, updatedVehicle);
				ResponseStructure<Vehicle> response = new ResponseStructure<Vehicle>();
				response.setData(updatedVehicle);
				response.setStatusCode(HttpStatus.CREATED.value());
				response.setMessage("Vehicle is updated");
				return new ResponseEntity<ResponseStructure<Vehicle>>(response, HttpStatus.CREATED);

			} else
				throw new ApplicationException(("Vehicle not found with ID: " + vehicleId));

		} else
			throw new ApplicationException(("Merchant doesnt exist"));
	}

	// get vehicle details
	public ResponseEntity<ResponseStructure<Vehicle>> findVehicle(int id) {
		Vehicle receivedVehicle = vehicleDao.findVehicleById(id);
		if (receivedVehicle != null) {
			ResponseStructure<Vehicle> response = new ResponseStructure<Vehicle>();
			response.setData(receivedVehicle);
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Vehicle is found");
			return new ResponseEntity<ResponseStructure<Vehicle>>(response, HttpStatus.FOUND);
		} else
			throw new ApplicationException(("Vehicle not found with ID: " + id));

	}

	// delete vehicle
	public ResponseEntity<ResponseStructure<String>> deleteVehicle(int userId, int vehicleId) {
		User receivedUser = userDao.findUserbyId(userId);

		if (receivedUser != null && "merchant".equalsIgnoreCase(receivedUser.getUserRole())) {
			Vehicle updatedVehicle = vehicleDao.findVehicleById(vehicleId);
			if (updatedVehicle != null) {
				vehicleDao.deleteVehicle(vehicleId);

				ResponseStructure<String> rs = new ResponseStructure<>();
				rs.setStatusCode(HttpStatus.OK.value());
				rs.setMessage("Success");
				rs.setData("Vehicle deleted successfully!");

				return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);

			}
			throw new ApplicationException("Vehicle id not valid!");

		}
		throw new ApplicationException("Not a valid customer.");

	}
}
