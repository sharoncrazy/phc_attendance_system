package com.code_red.phc_attendance_system.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.dto.DoctorDTO;
import com.code_red.phc_attendance_system.entities.Doctor;
import com.code_red.phc_attendance_system.entities.Facility;
import com.code_red.phc_attendance_system.entities.Shift;
import com.code_red.phc_attendance_system.repositories.DoctorRepository;
import com.code_red.phc_attendance_system.repositories.ShiftRepository;

import jakarta.transaction.Transactional;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private ShiftRepository shiftRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public Doctor register(DoctorDTO doctorDTO) {
		Doctor doctor = new Doctor();
		doctor.setDoctorId(doctorDTO.getDoctorId());
	    doctor.setFullName(doctorDTO.getFullName());
	    doctor.setEmail(doctorDTO.getEmail());
	    doctor.setPassword(passwordEncoder.encode(doctorDTO.getPassword())); // Encrypt password
	    doctor.setSpecialization(doctorDTO.getSpecialization());
	    doctor.setFacility(doctorDTO.getFacility());
	    doctor.setRoles(doctorDTO.getRoles());
		return doctorRepository.save(doctor);
	}
	
	public List<Doctor> getAllDoctors(){
		return doctorRepository.findAll();
	}
	
	public List<Doctor> getByFacility(Facility facility){
		return doctorRepository.findByFacility(facility);
	}

	public Optional<Doctor> findByEmail(String email) {
		// TODO Auto-generated method stub
		return doctorRepository.findByEmail(email);
	}
	
	 @Transactional
	    public Doctor updateDoctorShift(Long doctorId, Shift newShift) {
	        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
	        if (optionalDoctor.isEmpty()) {
	            throw new RuntimeException("Doctor not found with ID: " + doctorId);
	        }

	        Doctor doctor = optionalDoctor.get();

	        // Remove the existing shift if present
	        if (doctor.getShift() != null) {
	            shiftRepository.delete(doctor.getShift());
	        }

	        // Save new shift and update doctor
	        Shift savedShift = shiftRepository.save(newShift);
	        doctor.setShift(savedShift);

	        return doctorRepository.save(doctor);
	    }
}
