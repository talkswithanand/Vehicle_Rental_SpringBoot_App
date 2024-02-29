package com.ff.rentalApp.util;

import java.time.LocalDate;
import java.util.List;

import com.ff.rentalApp.entity.Booking;
import com.ff.rentalApp.entity.Vehicle;

public class VehicleHelper {

	
	public boolean isVehicleAvailableForDates(Vehicle vehicle, LocalDate startDate, LocalDate endDate) {

        List<Booking> bookings = vehicle.getBookings();
        for (Booking booking : bookings) {
            LocalDate bookingStartDate = booking.getStartTime().toLocalDate();
            LocalDate bookingEndDate = booking.getEndTime().toLocalDate();

            if (!(endDate.isBefore(bookingStartDate) || startDate.isAfter(bookingEndDate))) {
                return false; 
            }
        }
        return true; // Vehicle is available for all specified dates
    }
	
	
	
	
}
