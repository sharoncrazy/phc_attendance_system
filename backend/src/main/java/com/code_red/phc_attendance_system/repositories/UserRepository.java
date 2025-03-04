package com.code_red.phc_attendance_system.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.code_red.phc_attendance_system.entities.AppUser;
import com.code_red.phc_attendance_system.entities.Facility;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long>{

	Optional<AppUser> findByEmail(String email);
	    
	@Query("SELECT u FROM AppUser u JOIN u.roles r WHERE u.facility = :facility AND r.name = :roleName")
	Optional<AppUser> findByFacilityAndRole(@Param("facility") Facility facility, @Param("roleName") String roleName);
	


}
