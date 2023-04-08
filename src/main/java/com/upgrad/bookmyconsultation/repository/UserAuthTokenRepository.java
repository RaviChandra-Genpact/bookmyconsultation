package com.upgrad.bookmyconsultation.repository;

import com.upgrad.bookmyconsultation.entity.UserAuthToken;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface UserAuthTokenRepository extends CrudRepository<UserAuthToken, String> {

	
	@Query("SELECT authToken FROM UserAuthToken authToken WHERE authToken.user.emailId = :emailId")
	UserAuthToken findByUserEmailId(@NotNull String emailId);

	@Query("SELECT t FROM UserAuthToken t WHERE t.accessToken = :token")
	UserAuthToken findByAccessToken(String token);

}
