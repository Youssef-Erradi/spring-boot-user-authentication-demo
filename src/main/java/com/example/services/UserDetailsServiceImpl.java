package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.entities.User;
import com.example.repositories.UserRepository;
import com.example.security.UserDetailsImpl;

public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.getByEmail(email.strip().toLowerCase());
		if(user == null) {
			throw new UsernameNotFoundException("Unable to find a user with the email '"+ email +"'");
		}
		return new UserDetailsImpl(user);
	}

}
