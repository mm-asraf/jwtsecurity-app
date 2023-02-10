package com.asraf.infosapp.service;

import java.util.List;

import com.asraf.infosapp.model.User;
import com.asraf.infosapp.model.common.UserRequest;
import com.asraf.infosapp.model.common.UserResponse;


public interface IUserService {
	public UserResponse createUser(UserRequest user);
	public UserResponse updateUser(UserRequest user,long id);
	public List<User> getUser();
	public User getSingleUser(long userId);
	public void deleteUser(long userId);
}
