package com.example.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.flightreservation.dto.ReservationRequest;
import com.example.flightreservation.entities.Flight;
import com.example.flightreservation.entities.Reservation;
import com.example.flightreservation.repos.FlightRepository;
import com.example.flightreservation.services.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReservationService reservationService;

	private static Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {

		LOGGER.info(">>> inside showCompleteReservation() invoked with the Flight Id: " + flightId);
		
//		Optional<Flight> flight = flightRepository.findById(flightId);
		Flight flight = flightRepository.getFlightById(flightId);

//		modelMap.addAttribute("flight", flight);
//		return "completeReservation";

		if (flight != null) {

			modelMap.addAttribute("flight", flight);
			LOGGER.info(">>> Flight is: " + flight);

			return "completeReservation";
		} else {
			return "displayFlights";
		}

	}

	@RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
	public String completeReservation(ReservationRequest request, ModelMap modelMap) {

		LOGGER.info(">>> inside completeReservation(): " + request);
		
		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg", "Reservation created successfully and the id is " + reservation.getId());
		
		return "reservationConfirmation";
	}
}







