package com.asraf.infosapp.optionenum;

import lombok.Getter;


@Getter
public enum MaritialStatus {
	MARRIED("married"),
	UNMARRIED("unmarried");
	private String typeVar;
	private MaritialStatus(String type) {
		this.typeVar = type;
	}
	
}
