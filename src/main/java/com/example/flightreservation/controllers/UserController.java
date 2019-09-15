package com.example.flightreservation.controllers;

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

	@RequestMapping("/showReg")
	public String showRegistrationPage() {

		return "login/registerUser";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {

		userRepository.save(user);

		return "login/login";
	}

	@RequestMapping(value = "/loginFromRegisterUser", method = RequestMethod.GET)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap) {

		User user = userRepository.findByEmail(email);
		
			if (user != null && user.getPassword().equals(password)) {
				return "findFlight";
				
			} else {
			modelMap.addAttribute("msg", "Invalid User Name and Password. Please try again." +
										" FirstName:" + user.getFirstName() +
										" LastName:" + user.getLastName() +
										" Email:" + user.getEmail() +
										" Password:" + user.getPassword() 
						);
			}

		return "login/login";
	}

	@RequestMapping(value = "/login")
	public String login() {

		return "login/login";
	}

}














