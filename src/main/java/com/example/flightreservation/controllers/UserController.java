package com.example.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.flightreservation.entities.User;
import com.example.flightreservation.repos.UserRepository;
import com.example.flightreservation.services.SecurityService;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private SecurityService securityService;
	
	@RequestMapping("/showReg")
	public String showRegistrationPage() {

		LOGGER.info(">>> inside showRegistrationPage()");
		
		return "login/registerUser";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {

		LOGGER.info(">>> inside register()" + user);
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);

		return "login/login";
	}

	@RequestMapping(value = "/loginFromRegisterUser", method = RequestMethod.GET)
	public String loginFromRegisterUser(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap) {

		LOGGER.info(">>> inside loginFromRegisterUser() and the email is: " + email);

//		User user = userRepository.findByEmail(email);
		boolean loginResponse = securityService.login(email, password);

//		if (user != null && user.getPassword().equals(password)) {
		if (loginResponse) {
			return "findFlight";

		} else {
			modelMap.addAttribute("msg", "Invalid User Name and Password. Please try again.");
		}

		return "login/login";
	}

	@RequestMapping(value = "/login")
	public String login() {

		LOGGER.info(">>> inside login()");

		return "login/login";
	}

}
