package com.proyectoi.kinetia.services;

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


    public Optional<UserModel> logIn(String email, String password) {
        try {
            UserModel user = userRepository.findByEmail(email);
            if (user.getPassword().equals(password)) {
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

    public UserModel createUser(UserModel user) {
        user.setRol(rolRepository.findByRolType(user.getRol().getRolType()));
        return userRepository.save(user);
    }



}
