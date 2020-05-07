package com.capgemini.rtvik.healthcare.service;

import java.util.List;

import com.capgemini.rtvik.healthcare.entities.Appointment;

public interface IAppointmentService {
	
	public Appointment saveAppointment(Appointment app);
	public Appointment getAppointment(long appointmentId);
	public List<Appointment> getAppointmentByCenter(String centerId);
	public List<Appointment> getAppointmentByUser(String userId);
}
