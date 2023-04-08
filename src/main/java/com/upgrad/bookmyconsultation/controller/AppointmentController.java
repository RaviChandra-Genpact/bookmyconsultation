package com.upgrad.bookmyconsultation.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upgrad.bookmyconsultation.dto.BookMyConsultaionResponse;
import com.upgrad.bookmyconsultation.entity.Appointment;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping
	public ResponseEntity<BookMyConsultaionResponse> bookAppointment(@RequestBody Appointment appointment) throws InvalidInputException {
	    if (Objects.isNull(appointment)) {
	        throw new InvalidInputException("Invalid request body");
	    }
	    Appointment appointmentObj = appointmentService.bookAppointment(appointment);
	    return ResponseEntity.ok(new BookMyConsultaionResponse("Appointment booked successfully", "SUCCESS", appointmentObj));
	}
	
	@GetMapping("/{appointmentId}")
	public ResponseEntity<Appointment> getAppointment(@PathVariable String appointmentId) {
	    Appointment appointment = appointmentService.getAppointment(appointmentId);
	    if (appointment == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok(appointment);
	}
	
	

}