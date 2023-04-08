package com.upgrad.bookmyconsultation.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upgrad.bookmyconsultation.entity.Address;
import com.upgrad.bookmyconsultation.entity.Doctor;
import com.upgrad.bookmyconsultation.enums.Speciality;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.exception.ResourceUnAvailableException;
import com.upgrad.bookmyconsultation.model.TimeSlot;
import com.upgrad.bookmyconsultation.repository.AddressRepository;
import com.upgrad.bookmyconsultation.repository.AppointmentRepository;
import com.upgrad.bookmyconsultation.repository.DoctorRepository;

import lombok.extern.log4j.Log4j2;
import springfox.documentation.annotations.Cacheable;

@Log4j2
@Service
public class DoctorService {
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private AddressRepository addressRepository;

	public Doctor register(Doctor doctor) throws InvalidInputException {
		// Validate doctor details
		if (doctorRepository.existsByMobileAndEmailId(doctor.getMobile(), doctor.getEmailId())) {
			throw new InvalidInputException(
					"Doctor already exist with the mobile :" + doctor.getMobile() + " email:" + doctor.getEmailId());
		}
		if (!Speciality.contains(doctor.getSpeciality().name())) {
			throw new InvalidInputException("Invalid Speciality :" + doctor.getSpeciality());
		}

		if (doctor.getAddress() == null) {
			System.out.println("doctor address obj is null");
			throw new InvalidInputException("Doctor address is required");
		}
		// Set UUID for doctor
		doctor.setId(UUID.randomUUID().toString());

		// Set default speciality if not provided
		if (doctor.getSpeciality() == null) {
			doctor.setSpeciality(Speciality.GENERAL_PHYSICIAN);
		}
		// Create and save address object
		Address address = doctor.getAddress();
		address.setId(UUID.randomUUID().toString());
		addressRepository.save(address);
		// Set address in doctor object
		doctor.setAddress(address);
		return doctorRepository.save(doctor);
	}
	
	public Doctor getDoctor(String id) {
	    return doctorRepository.findById(id)
	        .orElseThrow(() -> new ResourceUnAvailableException("Doctor not found"));
	}

	

	public List<Doctor> getAllDoctorsWithFilters(String speciality) {

		if (speciality != null && !speciality.isEmpty()) {
			return doctorRepository.findBySpecialityOrderByRatingDesc(Speciality.valueOf(speciality));
		}
		return getActiveDoctorsSortedByRating();
	}

	@Cacheable(value = "doctorListByRating")
	private List<Doctor> getActiveDoctorsSortedByRating() {
		log.info("Fetching doctor list from the database");
		return doctorRepository.findAllByOrderByRatingDesc()
				.stream()
				.limit(20)
				.collect(Collectors.toList());
	}

	public TimeSlot getTimeSlots(String doctorId, String date) {

		TimeSlot timeSlot = new TimeSlot(doctorId, date);
		System.out.println("timeSlot : "+timeSlot);
		timeSlot.setTimeSlot(timeSlot.getTimeSlot()
				.stream()
				.filter(slot -> {
					return appointmentRepository
							.findByDoctorIdAndTimeSlotAndAppointmentDate(timeSlot.getDoctorId(), slot, timeSlot.getAvailableDate()) == null;

				})
				.collect(Collectors.toList()));

		return timeSlot;

	}
}
