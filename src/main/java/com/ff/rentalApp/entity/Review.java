package com.ff.rentalApp.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private double rating;
	private String reviewDescription;
	private String reviewerName;
	
	@CreationTimestamp
	private LocalDateTime reviewDate;
	
	@OneToOne(mappedBy = "review")
	private Booking booking;
	
}
