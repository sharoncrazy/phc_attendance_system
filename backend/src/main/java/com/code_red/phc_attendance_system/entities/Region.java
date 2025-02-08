package com.code_red.phc_attendance_system.entities;

import org.locationtech.jts.geom.Polygon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "POLYGON") // Store as a spatial type
    private Polygon boundary;
}
