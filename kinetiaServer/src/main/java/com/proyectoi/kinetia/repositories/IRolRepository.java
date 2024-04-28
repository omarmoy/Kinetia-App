package com.proyectoi.kinetia.repositories;

import com.proyectoi.kinetia.models.RolModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<RolModel, Long>{

}
