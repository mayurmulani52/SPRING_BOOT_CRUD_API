package com.practice.demo.model;

import java.io.Serializable;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class ApiError implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("code")
	private String code = null;

	@JsonProperty("status")
	private String status = null;

	@JsonProperty("message")
	private String message = null;

	@JsonProperty("errorId")
	private String errorId = null;

}