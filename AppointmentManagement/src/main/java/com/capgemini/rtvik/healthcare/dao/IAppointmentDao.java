package com.capgemini.rtvik.healthcare.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.rtvik.healthcare.entities.Appointment;

public interface IAppointmentDao extends JpaRepository<Appointment, Long> {

	@Query("FROM Appointment WHERE centerId=:centerId AND status=0")
	public List<Appointment> findByCenterId(@Param("centerId")String centerId); 
	
	public List<Appointment> findByUserId(String userId); 
}
