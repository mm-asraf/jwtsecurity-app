package com.asraf.infosapp.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.asraf.infosapp.exception.RecordNotFoundException;
import com.asraf.infosapp.model.User;
import com.asraf.infosapp.model.common.UserRequest;
import com.asraf.infosapp.model.common.UserResponse;
import com.asraf.infosapp.repository.IUserRepository;
import com.asraf.infosapp.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepository iUserRepository;
	
	@Override
	public UserResponse createUser(UserRequest user) {
		User userData = User.builder()
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.mobile(user.getMobile())
				.gender(user.getGender())
				.maritialStatus(user.getMaritialStatus())
				.bitcoinPassword(user.getBitcoinPassword())
				.etherWalletPassword(user.getEtherWalletPassword())
				.build();
		iUserRepository.save(userData);
		return UserResponse.builder().message("user added successfully").statusCode(HttpStatus.OK).build();
	}

	@Override
	public UserResponse updateUser(UserRequest user, long id) {
		iUserRepository.findById(id).ifPresentOrElse(x-> {
			User updateUserData = User.builder()
					.firstName(user.getFirstName())
					.lastName(user.getLastName())
					.email(user.getEmail())
					.mobile(user.getMobile())
					.gender(user.getGender())
					.maritialStatus(user.getMaritialStatus())
					.bitcoinPassword(user.getBitcoinPassword())
					.etherWalletPassword(user.getBitcoinPassword())
					.build();
			iUserRepository.save(updateUserData);
		}, ()-> {throw new RecordNotFoundException("user data is not present");});
		
		return UserResponse.builder().message("user data updated successfully").statusCode(HttpStatus.OK).build();
	}

	@Override
	public List<User> getUser() {
		return (List<User>) iUserRepository.findAll();
	}

	
	@Override
	public User getSingleUser(long userId) {
		Optional<User> optionalUser = iUserRepository.findById(userId);
		if(optionalUser.isPresent()) {
			User dbUser = optionalUser.get();
			return dbUser;
		}else {
			throw new RecordNotFoundException("user id is not valid");
		}
	}

	@Override
	public User deleteUser(long userId) {
		return null;
	}

	

	

}
