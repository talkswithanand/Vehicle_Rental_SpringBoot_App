package com.ff.rentalApp.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn
	private Vehicle vehicle;
	
	@ManyToOne
	@JoinColumn
	private User user;
	
	@OneToOne(cascade =  CascadeType.ALL)
	@JoinColumn
	private Review review;
	
	@Basic
	private LocalDateTime startTime;
	
	@Basic
	private LocalDateTime endTime;
	
	

}
