package com.proyectoi.kinetia.repositories;

import com.proyectoi.kinetia.models.AdvertisementModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdvertisementRepository extends JpaRepository<AdvertisementModel, Long>{

}
