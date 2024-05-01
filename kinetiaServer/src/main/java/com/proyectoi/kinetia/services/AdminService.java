package com.proyectoi.kinetia.services;

import com.proyectoi.kinetia.dto.Activity;
import com.proyectoi.kinetia.dto.SympleUser;
import com.proyectoi.kinetia.models.ActivityModel;
import com.proyectoi.kinetia.models.RolModel;
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
public class AdminService {

    @Autowired
    IRolRepository rolRepository;

    @Autowired
    IActivityRepository activityRepository;

    @Autowired
    IUserRepository userRepository;

    public Activity getActivity(Long id) {
        Optional<ActivityModel> activity = activityRepository.findById(id);
        return activity.map(Activity::new).orElse(null);
    }

    public RolModel saveRol(RolModel rol) {
        System.out.println(rol);
        return rolRepository.save(rol);
    }

    public Boolean deleteRol(int id) {
        try {
            this.rolRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<SympleUser> getUsers() {
        List<UserModel> users = userRepository.findAll();
        List<SympleUser> usersArray = new ArrayList<>();
        for (UserModel user : users) {
            usersArray.add(new SympleUser(user));
        }
        return usersArray;
    }
}
