package com.example.flightreservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.flightreservation.entities.User;
import com.example.flightreservation.repos.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found for email: " +username);
		}
		
//		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),user.getRoles());
		return new U
	}

}
