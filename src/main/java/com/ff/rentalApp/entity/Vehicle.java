package com.ff.rentalApp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String type;
	private String model;
	private String vehicleNumber;
	private String location;
	private boolean isAvailable;
	
	@OneToMany(mappedBy = "vehicle")
	@JsonIgnore
	private List<Booking> bookings;

}
