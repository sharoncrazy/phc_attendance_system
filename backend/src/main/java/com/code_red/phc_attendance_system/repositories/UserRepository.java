package com.code_red.phc_attendance_system.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.code_red.phc_attendance_system.entities.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long>{

	public Optional<AppUser> findByEmail(String email);

}
