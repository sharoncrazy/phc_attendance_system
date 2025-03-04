package com.code_red.phc_attendance_system.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code_red.phc_attendance_system.entities.Attendance;
import com.code_red.phc_attendance_system.repositories.AttendanceRepository;

@Service
public class AttendanceService {
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	private	List<Attendance> getAttendanceByToday(){
		LocalDate today = LocalDate.now();
		return attendanceRepository.findByDate(today);
	}
}
