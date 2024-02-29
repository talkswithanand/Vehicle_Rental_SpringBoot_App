package com.ff.rentalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ff.rentalApp.dto.ResponseStructure;
import com.ff.rentalApp.entity.Vehicle;
import com.ff.rentalApp.service.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@ApiResponses(value = @ApiResponse(description = "vehicle created", responseCode = "201"))
	@Operation(description = "To save the Vehicle information", summary = "The Vehicle datas are saved in the database")
	@PostMapping("/vehicles/{userId}")
	public ResponseEntity<ResponseStructure<Vehicle>> saveVehicle(@PathVariable int userId,
			@RequestBody Vehicle vehicle) {
		return vehicleService.saveVehicle(userId, vehicle);
	}

	@ApiResponse(description = "Update Vehicle details", responseCode = "201")
	@Operation(description = "Update details of an existing vehicle")
	@PutMapping("/vehicles/{userId}/{vehicleId}")
	public ResponseEntity<ResponseStructure<Vehicle>> updateVehicle(@PathVariable int userId,
			@PathVariable int vehicleId, @RequestBody Vehicle vehicle) {
		return vehicleService.updateVehicle(userId, vehicleId, vehicle);

	}

	@ApiResponse(description = "Get Vehicle details", responseCode = "201")
	@Operation(description = "Get details of an existing vehicle")
	@GetMapping("/vehicles/{vehicleId}")
	public ResponseEntity<ResponseStructure<Vehicle>> findVehicle(@PathVariable int vehicleId) {
		return vehicleService.findVehicle(vehicleId);

	}

	@ApiResponse(description = "Delete Vehicle details", responseCode = "201")
	@Operation(description = "Delete details of an existing vehicle")
	@DeleteMapping("/vehicles/{userId}/{vehicleId}")
	public ResponseEntity<ResponseStructure<String>> deleteVehicle(@PathVariable int userId,
			@PathVariable int vehicleId) {
		return vehicleService.deleteVehicle(userId, vehicleId);

	}

}
