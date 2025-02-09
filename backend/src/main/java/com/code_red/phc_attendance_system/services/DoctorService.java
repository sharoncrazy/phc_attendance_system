package com.code_red.phc_attendance_system.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.dto.DoctorDTO;
import com.code_red.phc_attendance_system.entities.Doctor;
import com.code_red.phc_attendance_system.repositories.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public Doctor register(DoctorDTO doctordto) {
		Doctor doctor = new Doctor(doctordto.getId(), doctordto.getEmail(), passwordEncoder.encode(doctordto.getPassword()));
		return doctorRepository.save(doctor);
	}
	
	public List<Doctor> getAllDoctors(){
		return doctorRepository.findAll();
	}
}
