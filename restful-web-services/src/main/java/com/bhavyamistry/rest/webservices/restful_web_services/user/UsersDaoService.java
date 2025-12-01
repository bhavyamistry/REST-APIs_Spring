package com.bhavyamistry.rest.webservices.restful_web_services.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
public class UsersDaoService {
	private static List<User> users = new ArrayList<>();
	private static int usersCount = 0;
	static {
		users.add(new User(++usersCount,"A",LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount,"B",LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount,"C",LocalDate.now().minusYears(20)));
	}
	
	public List<User> findAllUsers(){
		return users;
	}
	
	public User saveUser(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}

	public User findOneUser(int id) {
		// TODO Auto-generated method stub
		Predicate<? super User> predicate = user -> user.getId() == id;
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Predicate<? super User> predicate = user -> user.getId() == id;
		users.removeIf(predicate);
	}
	
}
