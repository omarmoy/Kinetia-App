package com.proyectoi.kinetia.controllers;

import com.proyectoi.kinetia.models.UserModel;
import com.proyectoi.kinetia.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/kinetia")
public class AppController {
	
	@Autowired
	private AppService appService;

	@PostMapping
	public ResponseEntity<?> login(@RequestBody Map<String, String> requestBody) {
		String email = requestBody.get("email");
		String password = requestBody.get("password");

		Optional<UserModel> user = appService.logIn(email, password);

		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
		}
	}

	@GetMapping(path = "/signUp/{email}")
	public Boolean emailExists(@PathVariable String email) {
		return appService.emailExists(email);
	}

	@PostMapping(path = "/signUp")
	public Boolean cifExists(@RequestBody Map<String, String> requestBody) {
		String cif = requestBody.get("cif");
		return appService.cifExists(cif);
	}

	@PostMapping(path = "/signUp/user")
	public UserModel signUp(@RequestBody UserModel user) {
		return this.appService.createUser(user);
	}
	
	

}
