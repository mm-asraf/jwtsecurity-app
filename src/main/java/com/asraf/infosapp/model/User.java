package com.asraf.infosapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GeneratorType;

import com.asraf.infosapp.optionenum.Gender;
import com.asraf.infosapp.optionenum.MaritialStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private Gender gender ;
	private MaritialStatus maritialStatus;
	private String bitcoinPassword;
	private String etherWalletPassword;
}
