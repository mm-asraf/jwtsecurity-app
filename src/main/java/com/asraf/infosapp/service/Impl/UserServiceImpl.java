package com.asraf.infosapp.service.Impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.asraf.infosapp.config.JwtService;
import com.asraf.infosapp.model.User;
import com.asraf.infosapp.model.common.AuthenticationRequest;
import com.asraf.infosapp.model.common.AuthenticationResponse;
import com.asraf.infosapp.model.common.UserRequest;
import com.asraf.infosapp.optionenum.Role;
import com.asraf.infosapp.repository.IUserRepository;
import com.asraf.infosapp.service.IUserService;
//import com.asraf.infosapp.util.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepository iUserRepository;

	@Autowired
	JwtService jwtService;
	
	 private final AuthenticationManager authenticationManager;
	 private final PasswordEncoder passwordEncoder;

	@Override
	public AuthenticationResponse createUser(UserRequest user)  {

//		System.out.println("chal ja code");
		//salt
//		byte[] saltForPassword = PasswordEncoder.generateSalt();

		//encoded password
//		String encodedPassword = null;

//		char[] password = user.getPassword().toCharArray();

//		try {
//			encodedPassword = PasswordEncoder.encode(password, saltForPassword);

//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (InvalidKeySpecException e) {
//			e.printStackTrace();
//		}	
//		log.info("encoded bit coinPassword"+ " " +  encodedPassword);

		User userData = User.builder()
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.mobile(user.getMobile())
				.gender(user.getGender())
				.maritialStatus(user.getMaritialStatus())
				.password(passwordEncoder.encode(user.getPassword()))
				.role(Role.USER)
				.build();
		iUserRepository.save(userData);
		var jwtToken = jwtService.generateToken(userData);

		return AuthenticationResponse.builder().token(jwtToken).message("You Register Successfully").build();
	}

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
				);
		
		var user = iUserRepository.findByEmail(request.getEmail())
		        .orElseThrow();
		
		var jwtToken = jwtService.generateToken(user);
		
		return AuthenticationResponse.builder().token(jwtToken).message("authenticated successfully").build();
	}

}
