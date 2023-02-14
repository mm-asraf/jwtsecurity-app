package com.asraf.infosapp.model.common;

import com.asraf.infosapp.optionenum.Gender;
import com.asraf.infosapp.optionenum.MaritialStatus;
import lombok.Getter;

@Getter
public class UserRequest {
	
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private Gender gender ;
	private MaritialStatus maritialStatus;
	private String password;
	
}
