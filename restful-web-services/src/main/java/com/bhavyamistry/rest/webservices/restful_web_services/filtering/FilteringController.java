package com.bhavyamistry.rest.webservices.restful_web_services.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	@GetMapping(path="/filtering")
	public SomeBean filtering(){
		return new SomeBean("value1","value2","value3");
	}
}
