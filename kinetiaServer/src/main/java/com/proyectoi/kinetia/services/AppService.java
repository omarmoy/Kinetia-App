package com.proyectoi.kinetia.services;

import com.proyectoi.kinetia.dto.User;
import com.proyectoi.kinetia.models.UserModel;
import com.proyectoi.kinetia.repositories.IRolRepository;
import com.proyectoi.kinetia.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppService {

    @Autowired
    IUserRepository userRepository;

	@Autowired
    IRolRepository rolRepository;


    public Optional<User> logIn(String email, String password) {
        try {
            UserModel userModel = userRepository.findByEmail(email);
            User user = new User(userModel);
            if (userModel.getPassword().equals(password)) {
                return Optional.of(user);
            }
        } catch (Exception e) {

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



}
