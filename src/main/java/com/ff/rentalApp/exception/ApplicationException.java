package com.ff.rentalApp.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ApplicationException extends RuntimeException {
	
	
	String message = "You are not authorised to perform this operation";
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
	

}
