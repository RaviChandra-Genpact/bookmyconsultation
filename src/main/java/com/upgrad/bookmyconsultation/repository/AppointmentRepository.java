package com.upgrad.bookmyconsultation.repository;


import com.upgrad.bookmyconsultation.entity.Appointment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, String> {

	@Query("SELECT a FROM Appointment a WHERE a.userEmailId = :emailId")
	public List<Appointment> findByEmailId(String emailId);
	
	@Query("SELECT a FROM Appointment a WHERE a.doctorId = :doctorId AND a.timeSlot = :timeSlot AND a.appointmentDate = :appointmentDate")
	public Appointment findByDoctorIdAndTimeSlotAndAppointmentDate(String doctorId, String timeSlot, String appointmentDate);

	@Query("SELECT COUNT(a) > 0 FROM Appointment a WHERE a.doctorId = :doctorId AND a.appointmentDate = :appointmentDate AND a.timeSlot = :timeSlot")
	public boolean checkDoctorExistsByDoctorIdAndAppointmentDateAndTimeSlot(String doctorId, String appointmentDate,
			String timeSlot);

	@Query("SELECT COUNT(a) > 0 FROM Appointment a WHERE a.userEmailId = :emailId AND a.appointmentDate = :appointmentDate AND a.timeSlot = :timeSlot")
	public boolean checkUserExistsByDoctorIdAndAppointmentDateAndTimeSlot(String emailId, String appointmentDate,
			String timeSlot);
}
