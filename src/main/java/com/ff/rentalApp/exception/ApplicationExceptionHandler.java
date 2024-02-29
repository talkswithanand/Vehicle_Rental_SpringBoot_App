package com.ff.rentalApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ff.rentalApp.dto.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	
	
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ResponseStructure<String>> catchApplicationException(ApplicationException exception){
		
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
		rs.setMessage("BAD Request");
		rs.setMessage(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.BAD_REQUEST);
		
	}

}
