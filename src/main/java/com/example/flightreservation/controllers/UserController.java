package com.example.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.flightreservation.entities.User;
import com.example.flightreservation.repos.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@RequestMapping("/showReg")
	public String showRegistrationPage() {

		LOGGER.info(">>> inside showRegistrationPage()");
		
		return "login/registerUser";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {

		LOGGER.info(">>> inside register()" + user);
		userRepository.save(user);

		return "login/login";
	}

	@RequestMapping(value = "/loginFromRegisterUser", method = RequestMethod.GET)
	public String loginFromRegisterUser(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap) {

		LOGGER.info(">>> inside loginFromRegisterUser() and the email is: " + email);

		User user = userRepository.findByEmail(email);

		
		if (user != null && user.getPassword().equals(password)) {
			return "findFlight";

		} else {
			modelMap.addAttribute("msg",
					"Invalid User Name and Password. Please try again." + " FirstName:" + user.getFirstName()
							+ " LastName:" + user.getLastName() + " Email:" + user.getEmail() + " Password:"
							+ user.getPassword());
		}

		return "login/login";
	}

	@RequestMapping(value = "/login")
	public String login() {

		LOGGER.info(">>> inside login()");

		return "login/login";
	}

}
