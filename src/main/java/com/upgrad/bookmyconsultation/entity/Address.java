package com.upgrad.bookmyconsultation.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Address {
	@Id
	private String id = UUID.randomUUID().toString();
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String postcode;
}
