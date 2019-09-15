package com.example.flightreservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flightreservation.dto.ReservationRequest;
import com.example.flightreservation.entities.Flight;
import com.example.flightreservation.entities.Passenger;
import com.example.flightreservation.entities.Reservation;
import com.example.flightreservation.repos.FlightRepository;
import com.example.flightreservation.repos.PassengerRepository;
import com.example.flightreservation.repos.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		// Receive Flight fron db
		Long flightId = request.getFlightId();
		Flight flight = flightRepository.getFlightById(flightId);
		
		// create new passenger in db
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		// create reservation
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		Reservation saveReservation = reservationRepository.save(reservation);
		
		return saveReservation;
	}
	
}


















