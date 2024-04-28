package com.proyectoi.kinetia.repositories;

import com.proyectoi.kinetia.models.ActivityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActivityRepository extends JpaRepository<ActivityModel, Long>{

}
