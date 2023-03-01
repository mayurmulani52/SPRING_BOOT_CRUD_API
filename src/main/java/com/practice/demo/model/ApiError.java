package com.practice.demo.model;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class ApiError {
	public ApiError(String valueOf, String reasonPhrase, String localizedMessage, String string) {
	}

	@JsonProperty("code")
	private String code = null;

	@JsonProperty("status")
	private String status = null;

	@JsonProperty("message")
	private String message = null;

	@JsonProperty("errorId")
	private String errorId = null;
}