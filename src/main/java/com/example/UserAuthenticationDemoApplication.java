package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.entities.User;
import com.example.enums.Role;
import com.example.repositories.UserRepository;

@SpringBootApplication
public class UserAuthenticationDemoApplication implements CommandLineRunner {

	@Autowired
	private UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticationDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (repository.count() == 0) {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String password = passwordEncoder.encode("Test123");
			repository.saveAll(List.of(new User("Erradi Youssef", "admin@gmail.com", password, Role.ADMIN),
					new User("Erradi Mohamed", "user@gmail.com", password, Role.USER)));
		}

	}

}
