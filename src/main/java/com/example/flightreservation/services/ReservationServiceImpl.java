package com.example.flightreservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flightreservation.controllers.ReservationController;
import com.example.flightreservation.dto.ReservationRequest;
import com.example.flightreservation.entities.Flight;
import com.example.flightreservation.entities.Passenger;
import com.example.flightreservation.entities.Reservation;
import com.example.flightreservation.repos.FlightRepository;
import com.example.flightreservation.repos.PassengerRepository;
import com.example.flightreservation.repos.ReservationRepository;
import com.example.flightreservation.util.EmailUtil;
import com.example.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	private static Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		LOGGER.info(">>> inside bookFlight()");
		
		// Receive Flight fron db
		Long flightId = request.getFlightId();
		LOGGER.info(">>> Fething Flight for flight id: " + flightId);
		Flight flight = flightRepository.getFlightById(flightId);
		
		// create new passenger in db
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		LOGGER.info(">>> Saving the passenger: " + passenger);
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		// create reservation
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		LOGGER.info(">>> Saving the Reservation: " + reservation);
		Reservation saveReservation = reservationRepository.save(reservation);
		
		String filePath = "C:/Users/eduar/Documents/reservations/reservation" + saveReservation.getId() + ".pdf";
		
		LOGGER.info(">>> Generating the Itinerary");
		pdfGenerator.generateItinerary(saveReservation, filePath);
		
		LOGGER.info(">>> Emailing the Itinerary");
		emailUtil.sendItinearary(passenger.getEmail(), filePath);

		return saveReservation;
	}
	
}


















