package com.proyectoi.kinetia.controllers;

import com.proyectoi.kinetia.models.RolModel;
import com.proyectoi.kinetia.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rol")
public class RolController {
	
	@Autowired
	private RolService rolService;
	

	
	@PostMapping
	public RolModel saveRol(@RequestBody RolModel rol) {
		return this.rolService.saveRol(rol);
	}

	
	@DeleteMapping (path = "/{id}")
	public String deleteUserById(@PathVariable("id") int id) {
		if(this.rolService.deleteRol(id))
			return "ok";
		else
			return "error";
	}
	

}
