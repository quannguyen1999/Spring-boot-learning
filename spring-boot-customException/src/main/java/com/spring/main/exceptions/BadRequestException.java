package com.spring.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//trả về status sẽ là Not_Found
//phải extends RuntimeException thì mới throw được
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
	
	public  BadRequestException(String exception) {
		super(exception);
	}
	
}
