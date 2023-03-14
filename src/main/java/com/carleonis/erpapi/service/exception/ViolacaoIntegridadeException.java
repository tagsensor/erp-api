package com.carleonis.erpapi.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ViolacaoIntegridadeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ViolacaoIntegridadeException(String message) {
		super(message);
	}

}
