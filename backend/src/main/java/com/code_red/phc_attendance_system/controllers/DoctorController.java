package com.code_red.phc_attendance_system.controllers;

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

import com.code_red.phc_attendance_system.dto.DoctorDTO;
import com.code_red.phc_attendance_system.entities.Doctor;
import com.code_red.phc_attendance_system.entities.Facility;
import com.code_red.phc_attendance_system.entities.Shift;
import com.code_red.phc_attendance_system.services.DoctorService;
import com.code_red.phc_attendance_system.services.FacilityService;

import io.jsonwebtoken.lang.Collections;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private FacilityService facilityService;
	
	@PostMapping("/register")
	public ResponseEntity<Doctor> registerDoctor(@RequestBody DoctorDTO doctordto) {	
		return new ResponseEntity<>(doctorService.register(doctordto), HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public List<Doctor> getAllDoctors(){
		return doctorService.getAllDoctors();
	}
	
	@PutMapping("/{doctorId}/shift")
    public ResponseEntity<Doctor> updateDoctorShift(@PathVariable Long doctorId, @RequestBody Shift newShift) {
        
        Doctor updatedDoctor = doctorService.updateDoctorShift(doctorId, newShift);
        return ResponseEntity.ok(updatedDoctor);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Doctor>> getDoctorsByBlock(@PathVariable Long id) {  
	    Facility facility = facilityService.findById(id);
	    
	    if (facility == null) {
	        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
	    }
	    
	    List<Doctor> doctors = doctorService.getByFacility(facility);
	    return new ResponseEntity<>(doctors, HttpStatus.OK);
	}

}
