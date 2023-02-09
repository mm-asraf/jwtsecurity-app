package com.asraf.infosapp.service;

import java.util.List;
import com.asraf.infosapp.model.User;
import com.asraf.infosapp.model.common.UserRequest;


public interface IUserService {
	public UserRequest createUser(User user);
	public UserRequest updateUser(User user);
	public List<UserRequest> getUser();
	public User getSingleUser(long userId);
	public User deleteUser(User user);
}
