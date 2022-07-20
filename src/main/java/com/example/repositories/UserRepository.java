package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User getByEmail(String email);
}
