package com.example.flightreservation.services;

import com.example.flightreservation.dto.ReservationRequest;
import com.example.flightreservation.entities.Reservation;

public interface ReservationService {

	public Reservation bookFlight(ReservationRequest request);
}
