package com.code_red.phc_attendance_system.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code_red.phc_attendance_system.dto.DoctorDTO;
import com.code_red.phc_attendance_system.entities.Doctor;
import com.code_red.phc_attendance_system.services.DoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@PostMapping("/register")
	public Doctor registerDoctor(@RequestBody DoctorDTO doctordto) {	
		return doctorService.register(doctordto);
	}
	
	@GetMapping("/")
	public List<Doctor> getAllDoctors(){
		return doctorService.getAllDoctors();
	}
}
