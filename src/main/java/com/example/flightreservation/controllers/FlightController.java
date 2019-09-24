package com.example.flightreservation.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.flightreservation.entities.Flight;
import com.example.flightreservation.repos.FlightRepository;

@Controller
public class FlightController {

	private static Logger LOGGER = LoggerFactory.getLogger(FlightController.class);
	
	@Autowired
	FlightRepository flightRepository;
	
	@RequestMapping("/findFlights")
	public String findFlights(@RequestParam("from") String from,
							  @RequestParam("to") String to,
							  @RequestParam("departureDate")
						      @DateTimeFormat(pattern="MM-dd-yyyy") Date departureDate,
							  ModelMap modelMap) {
		
		LOGGER.info(">>> inside findFlights() From: "+ from + " TO: " + to + " Departure Date: " + departureDate);
		
		List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
		modelMap.addAttribute("flights", flights);
		
		LOGGER.info(">>> Flight Found are: " + flights);

		return "displayFlights";
	}
	
	@RequestMapping("admin/showAddFlight")
	public String showAddFlight() {
		
		return "addFlight";
	}
}
