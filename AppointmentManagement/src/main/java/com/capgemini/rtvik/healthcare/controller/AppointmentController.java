package com.capgemini.rtvik.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.rtvik.healthcare.dto.AppointmentDto;
import com.capgemini.rtvik.healthcare.entities.Appointment;
import com.capgemini.rtvik.healthcare.service.IAppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private IAppointmentService service;
	
	@PostMapping("/add")
	public ResponseEntity<Appointment> addAppointment(@RequestBody AppointmentDto dto) {
		Appointment app = convertDto(dto);
		Appointment appoint = service.saveAppointment(app);
		ResponseEntity<Appointment> response = new ResponseEntity<Appointment>(appoint, HttpStatus.OK);
		return response;
	}
	
	public Appointment convertDto(AppointmentDto dto) {
		Appointment app = new Appointment();
		app.setCenterId(dto.getCenterId());
		app.setUserId(dto.getUserId());
		app.setTestId(dto.getTestId());
		app.setDate(dto.getDate());
		app.setTime(dto.getTime());
		app.setStatus(false);
		return app;
	}
	
	@GetMapping("/viewCenterAppointment/{id}")
	public ResponseEntity<List<Appointment>> viewCenterAppointments(@PathVariable("id") String centerId) {
		List<Appointment> list = service.getAppointmentByCenter(centerId);
		ResponseEntity<List<Appointment>> response = new ResponseEntity<List<Appointment>>(list,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/viewUserAppointment/{id}")
	public ResponseEntity<List<Appointment>> viewUserAppointments(@PathVariable("id") String userId) {
		List<Appointment> list = service.getAppointmentByUser(userId);
		ResponseEntity<List<Appointment>> response = new ResponseEntity<List<Appointment>>(list,HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Appointment> approveAppointment(@PathVariable("id") long appointmentId) {
		Appointment appoint = service.getAppointment(appointmentId);
		appoint.setStatus(true);
		Appointment app = service.saveAppointment(appoint);
		ResponseEntity<Appointment> response = new ResponseEntity<Appointment>(app,HttpStatus.OK);
		return response;
	}
}
