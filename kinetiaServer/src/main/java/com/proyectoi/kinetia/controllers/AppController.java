package com.proyectoi.kinetia.controllers;

import com.proyectoi.kinetia.dto.Activity;
import com.proyectoi.kinetia.dto.Advertisement;
import com.proyectoi.kinetia.dto.User;
import com.proyectoi.kinetia.models.UserModel;
import com.proyectoi.kinetia.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/app")
public class AppController {
	
	@Autowired
	private AppService appService;

	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> requestBody) {
		String email = requestBody.get("email");
		String password = requestBody.get("password");
		Optional<User> user = appService.logIn(email, password);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
		}
	}

	@GetMapping(path = "/verify/{email}")
	public Boolean emailExists(@PathVariable String email) {
		return appService.emailExists(email);
	}

	@PostMapping(path = "/verify")
	public Boolean cifExists(@RequestBody Map<String, String> requestBody) {
		String cif = requestBody.get("cif");
		return appService.cifExists(cif);
	}

	@PostMapping(path = "/signUp")
	public User signUp(@RequestBody UserModel user) {
		return this.appService.createUser(user);
	}

	@GetMapping(path = "/activities")
	public List<Activity> activities() {
		return appService.activities();
	}

	@GetMapping(path = "/advertisement")
	public List<Advertisement> advertisements() {
		return appService.advertisements();
	}

}
