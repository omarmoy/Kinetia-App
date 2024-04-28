package com.proyectoi.kinetia.services;

import com.proyectoi.kinetia.models.RolModel;
import com.proyectoi.kinetia.models.UserModel;
import com.proyectoi.kinetia.repositories.IRolRepository;
import com.proyectoi.kinetia.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    IRolRepository rolRepository;

    public RolModel saveRol(RolModel rol) {
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
}
