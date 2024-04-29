package com.proyectoi.kinetia.controllers;

import com.proyectoi.kinetia.models.ActivityModel;
import com.proyectoi.kinetia.models.UserModel;
import com.proyectoi.kinetia.services.ActivityService;
import com.proyectoi.kinetia.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/kinetia/activity")
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;

	@PostMapping(path = "/add")
	public ActivityModel addActivity(@RequestBody ActivityModel activity) {
		System.out.println(activity);
		return this.activityService.createActivity(activity);
	}

	
	

}
