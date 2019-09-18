package com.example.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightreservation.dto.ReservationUpdateRequest;
import com.example.flightreservation.entities.Reservation;
import com.example.flightreservation.repos.ReservationRepository;
import com.example.flightreservation.util.PDFGenerator;

@RestController
@CrossOrigin
public class ReservationRestController {

	@Autowired
	ReservationRepository reservationRepository;
	
	private static Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);
	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {

		LOGGER.info(">>> Inside findReservation() for id : " + id);
		
		return reservationRepository.getReservationById(id);
	}
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		
		LOGGER.info(">>> Inside updateReservation() for : " + request);
		
		Reservation reservation = reservationRepository.getReservationById(request.getId());
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		
		LOGGER.info(">>> Saving Reservation");
		
		return reservationRepository.save(reservation);
	}
	
}
