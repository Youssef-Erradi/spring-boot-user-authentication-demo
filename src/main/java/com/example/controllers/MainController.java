package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	@ResponseBody
	public String index() {
		return "User logged in!";
	}
	
	@GetMapping("/admin")
	@ResponseBody
	public String dashboard() {
		return "Admin logged in!";
	}
}
