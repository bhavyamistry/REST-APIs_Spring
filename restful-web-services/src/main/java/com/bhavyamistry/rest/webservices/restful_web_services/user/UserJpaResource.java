package com.bhavyamistry.rest.webservices.restful_web_services.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bhavyamistry.rest.webservices.restful_web_services.jpa.PostRepository;
import com.bhavyamistry.rest.webservices.restful_web_services.jpa.UserRepository;
import com.bhavyamistry.rest.webservices.restful_web_services.post.Post;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {

	private UsersDaoService service;
	
	private UserRepository repository;
	
	private PostRepository postrepository;

	public UserJpaResource(UsersDaoService service, UserRepository repository, PostRepository postrepository) {
		super();
		this.service = service;
		this.repository = repository;
		this.postrepository = postrepository;
	}

	@GetMapping(path = "/jpa/users")
	public List<User> getUsers() {
		return repository.findAll();
	}

//	@GetMapping(path = "/users/{id}")
//	public User getSingleUsers(@PathVariable int id) {
//		User oneUser = service.findOneUser(id);
//		if(oneUser==null) {
//			throw new UserNotFoundException("This id:"+id+" user not found");
//		}
//		return oneUser;
//	}

	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> retrtieveUsers(@PathVariable int id) {

		Optional<User> oneUser = repository.findById(id);
		if (oneUser.isEmpty()) {
			throw new UserNotFoundException("This id:" + id + " user not found");
		}
		EntityModel<User> entityModel = EntityModel.of(oneUser.get());
		WebMvcLinkBuilder link = linkTo(methodOn(UserJpaResource.class).getUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User Createduser = repository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(Createduser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUsers(@PathVariable int id) {
		repository.deleteById(id);
	}
	
	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id) {
		Optional<User> oneUser = repository.findById(id);
		if (oneUser.isEmpty()) {
			throw new UserNotFoundException("This id:" + id + " user not found");
		}
		return oneUser.get().getPosts();
	}
	
	@GetMapping(path = "/jpa/users/{id}/posts/{post_id}")
	public EntityModel<Post> retrtieveAPostForUser(@PathVariable int id, @PathVariable int post_id) {

		Optional<User> oneUser = repository.findById(id);
		if (oneUser.isEmpty()) {
			throw new UserNotFoundException("This id:" + id + " user not found");
		}
		Optional<Post> onePost = postrepository.findById(post_id);
		if (onePost.isEmpty()) {
			throw new UserNotFoundException("This id:" + id + " user not found");
		}
		EntityModel<Post> entityModel = EntityModel.of(onePost.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(UserJpaResource.class).retrievePostsForUser(id));
		entityModel.add(link.withRel("all-posts"));
		return entityModel;
	}
	
	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPost(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> oneUser = repository.findById(id);
		if (oneUser.isEmpty()) {
			throw new UserNotFoundException("This id:" + id + " user not found");
		}
		post.setUser(oneUser.get());
		Post savedPost = postrepository.save(post); 
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();
	}


}
