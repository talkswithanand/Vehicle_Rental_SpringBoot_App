package com.ff.rentalApp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ff.rentalApp.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	public Booking findById(int id);
	
	
	@Query("SELECT b FROM Booking b " +
            "WHERE b.vehicle.id = :vehicleId " +
            "AND ((b.startTime <= :endTime AND b.endTime >= :startTime) " +
            "OR (b.startTime >= :startTime AND b.startTime <= :endTime))")
    List<Booking> findOverlappingBookings(
            @Param("vehicleId") int vehicleId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);

}
