package com.code_red.phc_attendance_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.code_red.phc_attendance_system.entities")
public class PhcAttendanceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhcAttendanceSystemApplication.class, args);
	}

}
