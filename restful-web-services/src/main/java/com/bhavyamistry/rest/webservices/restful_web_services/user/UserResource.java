package com.bhavyamistry.rest.webservices.restful_web_services.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UsersDaoService service;

	public UserResource(UsersDaoService service) {
		super();
		this.service = service;
	}

	@GetMapping(path = "/users")
	public List<User> getUsers() {
		return service.findAllUsers();
	}

	@GetMapping(path = "/users/{id}")
	public User getSingleUsers(@PathVariable int id) {
		User oneUser = service.findOneUser(id);
		if(oneUser==null) {
			throw new UserNotFoundException("This id:"+id+" user not found");
		}
		return oneUser;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User Createduser = service.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(Createduser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteUsers(@PathVariable int id) {
		service.deleteById(id);
	}

}
