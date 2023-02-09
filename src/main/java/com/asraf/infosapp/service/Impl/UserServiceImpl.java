package com.asraf.infosapp.service.Impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.asraf.infosapp.exception.RecordNotFoundException;
import com.asraf.infosapp.model.User;
import com.asraf.infosapp.model.common.UserRequest;
import com.asraf.infosapp.model.common.UserResponse;
import com.asraf.infosapp.repository.IUserRepository;
import com.asraf.infosapp.service.IUserService;
import com.asraf.infosapp.util.PasswordEncoder;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepository iUserRepository;

	@Override
	public UserResponse createUser(UserRequest user)  {

		//salt
		byte[] saltForBitcoin = PasswordEncoder.generateSalt();
		byte[] saltForEther = PasswordEncoder.generateSalt();

		//encoded password
		String encodedBitcoinPassword = null;
		String encodedEtherWalletPassword = null;

		char[] password = user.getBitcoinPassword().toCharArray();
		char [] etherPassword = user.getEtherWalletPassword().toCharArray();

		try {
			encodedBitcoinPassword = PasswordEncoder.encode(password, saltForBitcoin);
			encodedEtherWalletPassword = PasswordEncoder.encode(etherPassword, saltForEther);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		log.info("encoded bit coinPassword"+ " " +  encodedBitcoinPassword);
		log.info("encoded ether coinPassword"+ " " +  encodedEtherWalletPassword);

		User userData = User.builder()
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.mobile(user.getMobile())
				.gender(user.getGender())
				.maritialStatus(user.getMaritialStatus())
				.bitcoinPassword(encodedBitcoinPassword)
				.etherWalletPassword(encodedEtherWalletPassword)
				.build();
		iUserRepository.save(userData);
		return UserResponse.builder().message("user added successfully").statusCode(HttpStatus.OK).build();
	}

	@Override
	public UserResponse updateUser(UserRequest user, long id) {



		iUserRepository.findById(id).ifPresentOrElse(x-> {


			//salt
			byte[] saltForBitcoin = PasswordEncoder.generateSalt();
			byte[] saltForEther = PasswordEncoder.generateSalt();


			//encoded password
			String updatedEncodedBitcoinPassword = null;
			String updatedEncodedEtherWalletPassword = null;

			char[] password = user.getBitcoinPassword().toCharArray();
			char [] etherPassword = user.getEtherWalletPassword().toCharArray();

			try {
				updatedEncodedBitcoinPassword = PasswordEncoder.encode(password, saltForBitcoin);
				updatedEncodedEtherWalletPassword = PasswordEncoder.encode(etherPassword, saltForEther);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			}

			User updateUserData = User.builder()
					.firstName(user.getFirstName())
					.lastName(user.getLastName())
					.email(user.getEmail())
					.mobile(user.getMobile())
					.gender(user.getGender())
					.maritialStatus(user.getMaritialStatus())
					.bitcoinPassword(updatedEncodedBitcoinPassword)
					.etherWalletPassword(updatedEncodedEtherWalletPassword)
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
		return iUserRepository.findById(userId).orElseThrow(()-> {throw new RecordNotFoundException("invalid userid");});

	}

	@Override
	public void deleteUser(long userId) {
		User user = iUserRepository.findById(userId).orElseThrow(()-> {throw new RecordNotFoundException("invalid user id");});
		iUserRepository.delete(user);
	}

}
