package com.ff.rentalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ff.rentalApp.dto.ResponseStructure;
import com.ff.rentalApp.dto.Review_Dto;
import com.ff.rentalApp.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
public class ReviewController {
	
	@Autowired
	private BookingService bookingService;
	
	@Operation(description ="Saving review for a particular booking", summary = "Saving review for a particular booking")
	@ApiResponse(description = "Save Review details", responseCode = "201")
	@PostMapping("/review")
	public ResponseEntity<ResponseStructure<String>> createReview(@RequestParam int userId, @RequestParam int bookingId, @RequestBody  Review_Dto review){
		
//		System.err.print(review.getDescription());
		
		return bookingService.createReview(userId, bookingId, review);
		
	}

}
