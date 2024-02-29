package com.ff.rentalApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.rentalApp.dao.BookingDao;
import com.ff.rentalApp.dao.UserDao;
import com.ff.rentalApp.dao.VehicleDao;
import com.ff.rentalApp.dto.ResponseStructure;
import com.ff.rentalApp.dto.Review_Dto;
import com.ff.rentalApp.entity.Booking;
import com.ff.rentalApp.entity.Review;
import com.ff.rentalApp.entity.User;
import com.ff.rentalApp.entity.Vehicle;
import com.ff.rentalApp.exception.ApplicationException;

@Service
public class BookingService {

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private VehicleDao vehicleDao;

	public ResponseEntity<ResponseStructure<String>> createReview(int userId, int bookingId, Review_Dto review) {

		User receivedUser = userDao.findUserbyId(userId);
		Booking booking = bookingDao.findBookingById(bookingId);

		if (receivedUser != null && receivedUser.getUserRole().equals("customer")) {

			if (booking != null) {
				
				Review r1 = new Review();
				r1.setRating(bookingId);
				r1.setReviewDescription(review.getDescription());
				r1.setReviewerName(review.getName());

				booking.setReview(r1);

				bookingDao.saveBooking(booking);

				ResponseStructure<String> rs = new ResponseStructure<>();
				rs.setStatusCode(HttpStatus.CREATED.value());
				rs.setMessage("Success");
				rs.setData("Review added successfully!");

				return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);

			} else {
				throw new ApplicationException("Wrong Booking Id!!!");
			}

		} else {
			throw new ApplicationException("Customer does not exist!!!");
		}

	}

	


	public ResponseEntity<ResponseStructure<String>> saveBooking(int userId, int vehicleId, Booking booking) {

		User receivedUser = userDao.findUserbyId(userId);
		Vehicle receivedVehicle = vehicleDao.findVehicleById(vehicleId);

		if (receivedUser != null && receivedUser.getUserRole().equals("customer")) {
			if (receivedVehicle != null && bookingDao.isVehicleAvailable(vehicleId, booking.getStartTime(), booking.getEndTime())) {
				booking.setVehicle(receivedVehicle);
				booking.setUser(receivedUser);
				bookingDao.saveBooking(booking);

				ResponseStructure<String> responseStructure = new ResponseStructure<String>();
				responseStructure.setData("Booking saved successfully!!!");
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Success");
				return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.CREATED);
			} else {
				throw new ApplicationException("Vehicle not available for booking!!!");
			}
		} else {
			throw new ApplicationException("Customer does not exist!!!");
		}

	}

	public ResponseEntity<ResponseStructure<List<Booking>>> findBookings(int userId) {

		User receivedUser = userDao.findUserbyId(userId);

		if (receivedUser != null && receivedUser.getUserRole().equals("customer")) {
			List<Booking> bookings = receivedUser.getListBooking();
			ResponseStructure<List<Booking>> responseStructure = new ResponseStructure<List<Booking>>();
			responseStructure.setData(bookings);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			return new ResponseEntity<ResponseStructure<List<Booking>>>(responseStructure, HttpStatus.OK);
		} else {
			throw new ApplicationException("Customer does not exist!!!");
		}

	}

	public ResponseEntity<ResponseStructure<Booking>> updateBooking(int userId, int bookingId, Booking booking) {
		User receivedUser = userDao.findUserbyId(userId);

		if (receivedUser != null && receivedUser.getUserRole().equals("customer")) {
			Booking existingBooking = bookingDao.findBookingById(bookingId);
			if (existingBooking != null) {
				
				if(bookingDao.isVehicleAvailable(existingBooking.getVehicle().getId(), booking.getStartTime(), booking.getEndTime()))
				{
				existingBooking.setStartTime(booking.getStartTime());
				existingBooking.setEndTime(booking.getEndTime());
				bookingDao.saveBooking(existingBooking);
				
				
				ResponseStructure<Booking> responseStructure = new ResponseStructure<Booking>();
				responseStructure.setData(existingBooking);
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("Updated Successfully!!");
				return new ResponseEntity<ResponseStructure<Booking>>(responseStructure, HttpStatus.OK);
				}
				else {
					throw new ApplicationException("Booking date is not available.");
				}
			}
			else {
				throw new ApplicationException("Booking does not exist for this Id!!!");
			}
		} else {
			throw new ApplicationException("Customer does not exist!!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteBooking(int userId, int bookingId){
		
		
		User user = userDao.findUserbyId(userId);
		Booking booking = bookingDao.findBookingById(bookingId);
		
		if (user != null && user.getUserRole().equals("customer")) {
			
			if(booking != null) {
				
				bookingDao.deleteBooking(booking);
				
				ResponseStructure<String> rs = new ResponseStructure<>();
				rs.setStatusCode(HttpStatus.OK.value());
				rs.setMessage("Success");
				rs.setData("Booking deleted successfully!");
				
				return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);				
				
			}
			throw new ApplicationException("Booking id not valid!");
			
			
		}
		throw new ApplicationException("Not a valid customer.");
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
