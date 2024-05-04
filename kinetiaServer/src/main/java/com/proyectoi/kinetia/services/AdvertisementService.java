package com.proyectoi.kinetia.services;

import com.proyectoi.kinetia.domain.Advertisement;
import com.proyectoi.kinetia.models.AdvertisementModel;
import com.proyectoi.kinetia.repositories.IAdvertisementRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementService {

    private final IAdvertisementRepository advertisementRepository;

    public List<Advertisement> getAll() {
        List<AdvertisementModel> advertisementModels = advertisementRepository.findAll();
        List<Advertisement> advertisements = new ArrayList<>();
        for (AdvertisementModel advertisementModel : advertisementModels) {
            advertisements.add(new Advertisement(advertisementModel));
        }
        return advertisements;
    }

    public AdvertisementService(IAdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public Long createAdvertisement(AdvertisementModel advertisement) {
        try {
            advertisementRepository.save(advertisement);
            return advertisement.getId();
        } catch (Exception e) {
            return -1L;
        }
    }

    public Boolean updateAdvertisement(AdvertisementModel advertisement) {
        Optional<AdvertisementModel> optional = advertisementRepository.findById(advertisement.getId());
        if (optional.isPresent()) {
            advertisementRepository.save(advertisement);
            return true;
        }
        return false;
    }

    public Boolean deleteAdvertisement(Long id) {
        try {
            advertisementRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
