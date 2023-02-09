package com.asraf.infosapp.exception;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
	private Timestamp timestamp;
	private Integer status;
	private Integer errorCode;
	private String errorMessage;
	private Long traceId;
	private String errorDetails;
	private String path;
}
