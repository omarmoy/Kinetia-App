package com.proyectoi.kinetia.services;

import com.proyectoi.kinetia.models.ActivityModel;
import com.proyectoi.kinetia.models.UserModel;
import com.proyectoi.kinetia.repositories.IActivityRepository;
import com.proyectoi.kinetia.repositories.IRolRepository;
import com.proyectoi.kinetia.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {
	
	@Autowired
	IActivityRepository activityRepository;

	public ActivityModel createActivity(ActivityModel activity) {
		return activityRepository.save(activity);
	}

	public Boolean deleteActivity(ActivityModel activity) {
		try {
			activityRepository.delete(activity);
			return true;
		} catch (Exception e) {
		    return false;
		}
	}

	public Optional<ActivityModel> getActivityById(Long id) {
		return activityRepository.findById(id);
	}

	public List<ActivityModel> getAllActivities() {
		return activityRepository.findAll();
	}


}
