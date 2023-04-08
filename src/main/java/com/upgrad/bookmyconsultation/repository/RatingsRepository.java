package com.upgrad.bookmyconsultation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.upgrad.bookmyconsultation.entity.Rating;



@Repository
public interface RatingsRepository extends CrudRepository<Rating, String> {

}
