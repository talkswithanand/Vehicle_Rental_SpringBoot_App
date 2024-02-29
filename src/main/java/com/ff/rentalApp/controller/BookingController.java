package com.ff.rentalApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
 
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ff.rentalApp.dto.ResponseStructure;
import com.ff.rentalApp.entity.Booking;
import com.ff.rentalApp.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/rent")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	

	@Operation(description ="Save Booking details for a vehicle", summary = "Save Booking details for a vehicle")
	@ApiResponse(description = "Create Booking details", responseCode = "201")
	@PostMapping("/book")
	public ResponseEntity<ResponseStructure<String>> saveBooking(@RequestHeader int userId, @RequestHeader int vehicleId, @RequestBody Booking booking){
		return bookingService.saveBooking(userId, vehicleId, booking);
	}
	
	@Operation(description ="Find Booking details for a specific Customer", summary = "Find Booking details for a specific Customer")
	@ApiResponse(description = "Find Booking details", responseCode = "200")
	@GetMapping("/details/{userId}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookings(@PathVariable int userId){
		return bookingService.findBookings(userId);
	}
	
	@Operation(description ="Update Booking vehicle rental timings for a specific Customer", summary = "Update Booking vehicle rental timings for a specific Customer")
	@ApiResponse(description = "Displaying Booking ", responseCode = "200")
	@PutMapping("/update/{userId}/{bid}")
	public ResponseEntity<ResponseStructure<Booking>> updateBooking(@PathVariable int userId, @PathVariable int bookingId,  @RequestBody Booking booking){
		return bookingService.updateBooking(userId, bookingId, booking);
	}
	
	@Operation(description ="Delete a particular booking for a specific Customer", summary = "Delete a particular booking for a specific Customer")
	@ApiResponse(description = "Deleted Successfully!! ", responseCode = "200")
	@DeleteMapping("/book")
	public ResponseEntity<ResponseStructure<String>> deleteBooking(@RequestParam int userId, @RequestParam int bookingId){
		
		return bookingService.deleteBooking(userId, bookingId);
		
	}

}
