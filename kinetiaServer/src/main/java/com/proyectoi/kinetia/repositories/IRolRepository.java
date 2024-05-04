package com.proyectoi.kinetia.repositories;

import com.proyectoi.kinetia.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<RoleModel, Integer>{

    RoleModel findByRoleType(RoleModel.RoleType role);

}
