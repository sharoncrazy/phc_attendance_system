package com.code_red.phc_attendance_system.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class AppUser {
	    @Id
	    private Long userId;
	    private String fullName;
	    
	    @Column(unique = true, nullable = false)
	    private String email;
	    
	    private String password;
	    private String phone;
	    
	   
}
