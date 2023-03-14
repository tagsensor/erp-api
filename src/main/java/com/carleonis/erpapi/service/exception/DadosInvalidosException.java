package com.carleonis.erpapi.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DadosInvalidosException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public DadosInvalidosException(String message) {
		super(message);
	}

}
