package com.ff.rentalApp.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ff.rentalApp.entity.Booking;
import com.ff.rentalApp.repository.BookingRepository;

@Repository
public class BookingDao {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	
	public Booking saveBooking(Booking booking) {
	
		return bookingRepository.save(booking);
		
	}
	
	
	public Booking findBookingById(int id) {
		
		return bookingRepository.findById(id);
	}
	
	
	public void deleteBooking(Booking booking) {
		
		bookingRepository.delete(booking);
		
	}
	
	public boolean isVehicleAvailable(int vehicleId, LocalDateTime startTime, LocalDateTime endTime) {
        // Query the database to check if there are any overlapping bookings for the specified vehicle and time range
        List<Booking> overlappingBookings = bookingRepository.findOverlappingBookings(vehicleId, startTime, endTime);

        // If there are no overlapping bookings, the vehicle is available
        return overlappingBookings.isEmpty();
    }

}
