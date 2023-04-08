package com.upgrad.bookmyconsultation.repository;

import com.upgrad.bookmyconsultation.entity.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	@Override
	List<User> findAll();

	@Query("SELECT u FROM User u WHERE u.emailId = :emailId")
	User findByEmailId(String emailId);
	
	@Query("SELECT COUNT(u) > 0 FROM User u WHERE u.userId = :userId")
	boolean checkUserIsExistsOrNot(String userId);

}
