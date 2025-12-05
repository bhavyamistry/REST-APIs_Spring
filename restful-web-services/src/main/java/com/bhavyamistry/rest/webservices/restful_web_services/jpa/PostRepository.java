package com.bhavyamistry.rest.webservices.restful_web_services.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhavyamistry.rest.webservices.restful_web_services.post.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
}
