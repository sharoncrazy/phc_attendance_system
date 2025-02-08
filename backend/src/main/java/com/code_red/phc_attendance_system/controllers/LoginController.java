package com.code_red.phc_attendance_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code_red.phc_attendance_system.dto.AuthResponseDTO;
import com.code_red.phc_attendance_system.dto.LoginDTO;
import com.code_red.phc_attendance_system.services.CustomUserDetailsService;
import com.code_red.phc_attendance_system.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class LoginController {
	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private CustomUserDetailsService userDetailsService;

	    @Autowired
	    private JwtUtil jwtUtil;

	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody LoginDTO request) {
	        try {
	            authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
	            );
	        } catch (BadCredentialsException e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	        }

	        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	        final String token = jwtUtil.generateToken(userDetails);

	        return ResponseEntity.ok(new AuthResponseDTO(token));
	    }
}
