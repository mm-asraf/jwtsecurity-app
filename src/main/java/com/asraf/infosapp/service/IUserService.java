package com.asraf.infosapp.service;

import java.util.List;

import com.asraf.infosapp.model.User;
import com.asraf.infosapp.model.common.AuthenticationRequest;
import com.asraf.infosapp.model.common.AuthenticationResponse;
import com.asraf.infosapp.model.common.UserRequest;
import com.asraf.infosapp.model.common.UserResponse;


public interface IUserService {
	public AuthenticationResponse createUser(UserRequest user);
	
}
