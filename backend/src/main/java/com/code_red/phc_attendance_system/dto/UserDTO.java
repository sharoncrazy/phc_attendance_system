package com.code_red.phc_attendance_system.dto;



import com.code_red.phc_attendance_system.entities.Facility;
import com.code_red.phc_attendance_system.entities.Role;

import lombok.Data;
import java.util.Set;
@Data
public class UserDTO {
	private Long id;
	private String email;
	private String password;
	private Set<Role> roles;
	private Facility facility;
}
