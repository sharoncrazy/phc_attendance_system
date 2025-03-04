package com.code_red.phc_attendance_system.dto;

import java.util.Set;

import com.code_red.phc_attendance_system.entities.Facility;
import com.code_red.phc_attendance_system.entities.Role;

import lombok.Data;

@Data
public class UserDTO {
	private Long id;
	private String email;
	private String password;
	private Set<Role> roles;
	private Facility facility;
}
