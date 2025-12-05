package com.bhavyamistry.rest.webservices.restful_web_services.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {
	public PostNotFoundException(String message) {
		super(message);
	}
}
