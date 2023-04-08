package com.upgrad.bookmyconsultation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upgrad.bookmyconsultation.entity.Doctor;
import com.upgrad.bookmyconsultation.enums.Speciality;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, String> {

	@Query("SELECT d FROM Doctor d WHERE d.speciality = :speciality ORDER BY d.rating DESC")
	List<Doctor> findBySpecialityOrderByRatingDesc(@Param("speciality") Speciality speciality);
	
	@Query("SELECT d FROM Doctor d ORDER BY d.rating DESC")
	List<Doctor> findAllByOrderByRatingDesc();
	
	@Query("SELECT COUNT(d) > 0 FROM Doctor d WHERE d.mobile = :mobile AND d.emailId = :emailId")
	boolean existsByMobileAndEmailId(String mobile, String emailId);

	@Query("SELECT count(d) > 0 FROM Doctor d where d.id=:doctorId")
	boolean checkUserIsExistsOrNot(String doctorId);
}
