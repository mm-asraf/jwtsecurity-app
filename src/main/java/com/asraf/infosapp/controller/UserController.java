package com.asraf.infosapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.asraf.infosapp.model.User;
import com.asraf.infosapp.model.common.UserRequest;
import com.asraf.infosapp.model.common.UserResponse;
import com.asraf.infosapp.service.IUserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	IUserService iuserService;

	@PostMapping("/user")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest user) {
		UserResponse userResponse = iuserService.createUser(user);
		HttpStatus status = userResponse.getStatusCode() != null ? userResponse.getStatusCode() : HttpStatus.BAD_REQUEST;
		return new ResponseEntity(userResponse, status);
	}

	@GetMapping("/user")
	public List<User> getUser() {
		return iuserService.getUser();
	}

	@GetMapping("/user/{id}")
	public User getSingleUser(@PathVariable("id")long userId) {
		User user = iuserService.getSingleUser(userId);
		return user;
	}

}
