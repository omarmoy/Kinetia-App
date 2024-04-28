package com.proyectoi.kinetia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoi.kinetia.models.UserModel;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long>{
    UserModel findByEmail(String email);
    UserModel findByCif(String cif);
}
