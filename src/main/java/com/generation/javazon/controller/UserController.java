package com.generation.javazon.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.javazon.auth.model.UserInDb;
import com.generation.javazon.auth.service.UserRepository;
import com.generation.javazon.model.dto.user.UserDTONoOrdini;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepo;

	@GetMapping("/users")
	public List<UserDTONoOrdini> getAll() {
		List<UserDTONoOrdini> res= userRepo.findAll()
				.stream()
				.map(user -> new UserDTONoOrdini(user))
				.toList();
		return res;
	}
	
	
	@GetMapping("/users/{username}/username")
	public UserDTONoOrdini getOneUserByUSername(@PathVariable String username) {
		UserInDb u= userRepo.findByUsername(username);
		if(u==null)
			throw new NoSuchElementException("Non ho trovato alcun user con quell'username");
		return new UserDTONoOrdini(u);
	}

}
