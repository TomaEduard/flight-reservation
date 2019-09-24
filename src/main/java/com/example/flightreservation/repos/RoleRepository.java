package com.example.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
