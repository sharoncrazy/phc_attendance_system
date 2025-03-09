package com.code_red.phc_attendance_system.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.dto.UserDTO;
import com.code_red.phc_attendance_system.entities.AppUser;
import com.code_red.phc_attendance_system.entities.Facility;
import com.code_red.phc_attendance_system.entities.Role;
import com.code_red.phc_attendance_system.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	public AppUser register(UserDTO userDTO) {
		AppUser user = new AppUser(userDTO.getId(), userDTO.getEmail(), passwordEncoder.encode(userDTO.getPassword()), userDTO.getRoles(), userDTO.getFacility());
		user.setRoles(userDTO.getRoles());
		return userRepository.save(user);
	}
	
	public Optional<AppUser> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public Optional<AppUser> findDHO(Facility facility){
		return userRepository.findByFacilityAndRole(facility, "DHO");
	}
}
