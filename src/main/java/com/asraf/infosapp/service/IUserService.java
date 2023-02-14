package com.asraf.infosapp.service;

import com.asraf.infosapp.model.common.AuthenticationRequest;
import com.asraf.infosapp.model.common.AuthenticationResponse;
import com.asraf.infosapp.model.common.UserRequest;


public interface IUserService {
	public AuthenticationResponse createUser(UserRequest user);
	public AuthenticationResponse authenticate(AuthenticationRequest user);
	
}
