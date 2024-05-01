package com.proyectoi.kinetia.controllers;

import com.proyectoi.kinetia.dto.Activity;
import com.proyectoi.kinetia.dto.SympleUser;
import com.proyectoi.kinetia.models.RolModel;
import com.proyectoi.kinetia.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	
	@PostMapping(path = "/addRoll")
	public RolModel saveRol(@RequestBody RolModel rol) {
		System.out.println("rol desde controller:" + rol);
		return this.adminService.saveRol(rol);
	}
	
	@DeleteMapping (path = "/deleteRol/{id}")
	public String deleteUserById(@PathVariable("id") int id) {
		if(this.adminService.deleteRol(id))
			return "ok";
		else
			return "error";
	}


	@GetMapping(path = "/getActivity/{id}")
	public Activity getActivityById(@PathVariable Long id) {
		return this.adminService.getActivity(id);
	}

	@GetMapping(path = "/users")
	public List<SympleUser> getAllUser(){
		return adminService.getUsers();
	}
	

}
