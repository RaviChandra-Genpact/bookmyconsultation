package com.upgrad.bookmyconsultation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
@AllArgsConstructor
@Builder
public class BookMyConsultaionResponse {
	private String message;
	private String status;
	private Object data;
}