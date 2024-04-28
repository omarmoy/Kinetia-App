package com.proyectoi.kinetia.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoi.kinetia.models.UserModel;
import com.proyectoi.kinetia.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/s")
	public ArrayList<UserModel> getUsers(){
		return this.userService.getUsers();
	}
	
	@PostMapping("/add") 
	public UserModel saveUser(@RequestBody UserModel user) {
		return this.userService.saveUser(user);
	}
	
	@GetMapping (path = "/{id}")
	public Optional<UserModel> getUserById(@PathVariable("id") Long id){
		return this.userService.getById(id);
	}
	
	@PutMapping (path = "/{id}")
	public UserModel updateUserById(@RequestBody UserModel request, @PathVariable("id") Long id) {
		return this.userService.updateById(request, id);
	}
	
	@DeleteMapping (path = "/{id}")
	public String deleteUserById(@PathVariable("id") Long id) {
		Boolean ok = this.userService.deleteUser(id);		
		return ok.toString();
		
	}
	
	

}
