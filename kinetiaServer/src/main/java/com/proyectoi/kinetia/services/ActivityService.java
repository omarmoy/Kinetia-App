package com.proyectoi.kinetia.services;

import com.proyectoi.kinetia.domain.Activity;
import com.proyectoi.kinetia.domain.ActivityPro;
import com.proyectoi.kinetia.models.ActivityModel;
import com.proyectoi.kinetia.models.UserModel;
import com.proyectoi.kinetia.repositories.IActivityRepository;
import com.proyectoi.kinetia.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    private final IActivityRepository activityRepository;
    private final IUserRepository userRepository;

    public ActivityService(IActivityRepository activityRepository, IUserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    public List<Activity> getAll() {
        List<ActivityModel> activityModels = activityRepository.findAll();
        List<Activity> activities = new ArrayList<>();
        for (ActivityModel activityModel : activityModels) {
            activities.add(new Activity(activityModel));
        }
        return activities;
    }

    public List<Activity> getAll(Long userId) {
        List<ActivityModel> activityModels = activityRepository.findAll();
        List<Activity> activities = new ArrayList<>();
        for (ActivityModel activityModel : activityModels) {
            if (!activityModel.getUser().getId().equals(userId)) {
                activities.add(new Activity(activityModel));
            }
        }
        return activities;
    }

    public Long createActivity(Activity activity) {
        try {
            UserModel user = userRepository.findById(activity.getUserId()).orElseThrow();
            ActivityModel activityModel = new ActivityModel(activity);
            activityModel.setUser(user);
            activityRepository.save(activityModel);
            return activity.getId();
        } catch (Exception e) {
            return -1L;
        }
    }

    public Boolean updateActivity(ActivityPro activity) {
        System.out.println(activity);
        Optional<ActivityModel> activityOptional = activityRepository.findById(activity.getId());
        if (activityOptional.isPresent()) {
            ActivityModel activityEdited = activityOptional.get();
            activityEdited.setTitle(activity.getTitle());
            activityEdited.setDescription(activity.getDescription());
            activityEdited.setPicture(activity.getPicture());
            activityEdited.setDate(activity.getDate());
            activityEdited.setPrice(activity.getPrice());
            activityEdited.setLocation(activity.getLocation());
            activityEdited.setCategory(activity.getCategory());
            activityEdited.setFeatured(activity.getFeatured() != null ? activity.getFeatured() : false);
            activityEdited.setVacancies(activity.getVacancies());
            activityRepository.save(activityEdited);
            return true;
        }
        return false;
    }

    public Boolean deleteActivity(Long id) {
        try {
            activityRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
