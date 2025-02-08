package com.code_red.phc_attendance_system.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "doctors")
public class Doctor {

	    @Id
	    private Long doctorId;
	    
	    private String fullName;

	    @Column(unique = true, nullable = false)
	    private String email;

	    private String password;

	    private String specialization;
	    
//	    private Facility facility;
	    public Doctor() { }
	    public Doctor(Long id, String email, String password) {
	    	this.email = email;
	    	this.password = password;
	    	this.doctorId = id;
	    }
}
