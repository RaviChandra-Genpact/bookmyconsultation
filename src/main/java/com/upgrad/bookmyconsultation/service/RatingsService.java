package com.upgrad.bookmyconsultation.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upgrad.bookmyconsultation.entity.Doctor;
import com.upgrad.bookmyconsultation.entity.Rating;
import com.upgrad.bookmyconsultation.exception.ResourceUnAvailableException;
import com.upgrad.bookmyconsultation.repository.DoctorRepository;
import com.upgrad.bookmyconsultation.repository.RatingsRepository;


@Service
public class RatingsService {

	@Autowired
	private RatingsRepository ratingsRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	public void submitRatings(Rating rating) {
		rating.setId(UUID.randomUUID().toString());
		Optional<Doctor> optionalDoctor = doctorRepository.findById(rating.getDoctorId());
		if (optionalDoctor.isPresent()) {
			ratingsRepository.save(rating);
			Doctor doctor = optionalDoctor.get();
			// Modify the average rating for that specific doctor by including the new
			// rating
			int numberOfRatings = doctor.getNumberOfRatings();
			double averageRating = doctor.getRating();
			double totalRating = averageRating * numberOfRatings;
			totalRating += rating.getRating();
			numberOfRatings += 1;
			averageRating = totalRating / numberOfRatings;

			doctor.setNumberOfRatings(numberOfRatings);
			doctor.setRating(averageRating);
			doctorRepository.save(doctor);
		} else {
			throw new ResourceUnAvailableException("Provided Doctor with the doctorId :"+rating.getDoctorId()+" is unavaible to rate");
			
		}

	}
}
