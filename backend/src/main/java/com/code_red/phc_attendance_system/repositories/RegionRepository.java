package com.code_red.phc_attendance_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.code_red.phc_attendance_system.entities.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

}
