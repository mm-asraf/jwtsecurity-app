package com.asraf.infosapp.optionenum;

import lombok.Getter;

@Getter
public enum Gender {
	MALE("male"),
	FEMALE("female"),
	OTHER("other");

	private String typeVar;
	private Gender(String type) {
		this.typeVar = type;
	}
}
