package com.proyectoi.kinetia.services;

import com.proyectoi.kinetia.dto.Activity;
import com.proyectoi.kinetia.dto.Advertisement;
import com.proyectoi.kinetia.dto.User;
import com.proyectoi.kinetia.models.ActivityModel;
import com.proyectoi.kinetia.models.AdvertisementModel;
import com.proyectoi.kinetia.models.UserModel;
import com.proyectoi.kinetia.repositories.IActivityRepository;
import com.proyectoi.kinetia.repositories.IAdvertisementRepository;
import com.proyectoi.kinetia.repositories.IRolRepository;
import com.proyectoi.kinetia.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppService {

    @Autowired
    IUserRepository userRepository;

	@Autowired
    IRolRepository rolRepository;

    @Autowired
    IActivityRepository activityRepository;

    @Autowired
    IAdvertisementRepository advertisementRepository;


    public Optional<User> logIn(String email, String password) {
        try {
            UserModel userModel = userRepository.findByEmail(email);
            User user = new User(userModel);
            if (userModel.getPassword().equals(password)) {
                return Optional.of(user);
            }
        } catch (Exception ignored) {

        }

        return Optional.empty();
    }

    public Boolean emailExists(String email) {
        UserModel user = userRepository.findByEmail(email);
        return user != null;
    }

    public Boolean cifExists(String cif) {
        UserModel user = userRepository.findByCif(cif);
        return user != null;
    }

    public User createUser(UserModel userModel) {
        userModel.setRol(rolRepository.findByRolType(userModel.getRol().getRolType()));
        return new User(userRepository.save(userModel));
    }


    public List<Activity> activities() {
        List<ActivityModel> activityModels = activityRepository.findAll();
        List<Activity> activities = new ArrayList<>();
        for (ActivityModel activityModel : activityModels) {
            activities.add(new Activity(activityModel));
        }
        return activities;
    }

    public List<Advertisement> advertisements() {
        List<AdvertisementModel> advertisementModels = advertisementRepository.findAll();
        List<Advertisement> advertisements = new ArrayList<>();
        for (AdvertisementModel advertisementModel : advertisementModels) {
            advertisements.add(new Advertisement(advertisementModel));
        }
        return advertisements;
    }
}
