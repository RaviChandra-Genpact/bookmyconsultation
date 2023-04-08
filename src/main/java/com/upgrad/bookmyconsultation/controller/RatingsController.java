package com.upgrad.bookmyconsultation.controller;

import com.upgrad.bookmyconsultation.entity.Rating;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.service.RatingsService;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RatingsController {

	@Autowired
	private RatingsService ratingsService;


	@PostMapping("/ratings")
	public ResponseEntity<?> submitRatings(@RequestBody Rating rating) throws InvalidInputException {
		 if (Objects.isNull(rating)) {
		        throw new InvalidInputException("Invalid request body");
		    }
		ratingsService.submitRatings(rating);
		return ResponseEntity.ok().build();
	}
}
