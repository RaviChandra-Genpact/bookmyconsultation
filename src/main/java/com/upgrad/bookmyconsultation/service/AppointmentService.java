package com.upgrad.bookmyconsultation.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upgrad.bookmyconsultation.dto.BookMyConsultaionResponse;
import com.upgrad.bookmyconsultation.entity.Appointment;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.exception.ResourceUnAvailableException;
import com.upgrad.bookmyconsultation.exception.SlotUnavailableException;
import com.upgrad.bookmyconsultation.repository.AppointmentRepository;
import com.upgrad.bookmyconsultation.repository.DoctorRepository;
import com.upgrad.bookmyconsultation.repository.UserRepository;
import com.upgrad.bookmyconsultation.util.ValidationUtils;

@Service
public class AppointmentService {

	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	private UserRepository userRepository;

	public Appointment bookAppointment(Appointment appointment) throws SlotUnavailableException, InvalidInputException {
		// Validate appointment details
		ValidationUtils.validate(appointment);

		if(!userRepository.checkUserIsExistsOrNot(appointment.getUserEmailId())) {
			throw new InvalidInputException("User is not available with the email :"+appointment.getUserEmailId());
		}
		
		if(!doctorRepository.checkUserIsExistsOrNot(appointment.getDoctorId())) {
			throw new InvalidInputException("Doctor is not available with the doctorId :"+appointment.getDoctorId());
		}
		
		if (appointmentRepository.checkUserExistsByDoctorIdAndAppointmentDateAndTimeSlot(appointment.getUserEmailId(),
				appointment.getAppointmentDate(), appointment.getTimeSlot())) {
			throw new SlotUnavailableException("User is having another appointment for the same timeslot");
		}
		
		// Check if appointment slot is available
		if (appointmentRepository.checkDoctorExistsByDoctorIdAndAppointmentDateAndTimeSlot(appointment.getDoctorId(),
				appointment.getAppointmentDate(), appointment.getTimeSlot())) {
			throw new SlotUnavailableException("Appointment slot is not available for the selected doctor");
		}

		// Save the appointment details to the database
		Appointment appointmentObj = appointmentRepository.save(appointment);
		return Appointment.builder().appointmentId(appointmentObj.getAppointmentId()).build();
	}

	public Appointment getAppointment(String appointmentId) {
		Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
		return optionalAppointment.orElseThrow(() -> new ResourceUnAvailableException("Appointment not found"));
	}

	public List<Appointment> getAppointmentsForUser(String emailId) {
		return appointmentRepository.findByEmailId(emailId);
	}

}
