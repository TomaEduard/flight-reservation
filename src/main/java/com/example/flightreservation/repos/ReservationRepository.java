package com.example.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
