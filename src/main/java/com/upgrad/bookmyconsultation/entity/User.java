package com.upgrad.bookmyconsultation.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class User {
	@Id
	private String userId = UUID.randomUUID().toString();    
    @Column(unique = true)
    private String emailId;
    
    private String firstName;
    private String lastName;
    private String dob;
    private String mobile;
    private String password;
    private String createdDate;
    private String salt;
}