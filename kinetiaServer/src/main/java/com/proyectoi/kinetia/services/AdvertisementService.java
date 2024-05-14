package com.proyectoi.kinetia.services;

import com.proyectoi.kinetia.domain.Advertisement;
import com.proyectoi.kinetia.models.AdvertisementModel;
import com.proyectoi.kinetia.models.UserModel;
import com.proyectoi.kinetia.repositories.IAdvertisementRepository;
import com.proyectoi.kinetia.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementService {

    private final IAdvertisementRepository advertisementRepository;
    private final IUserRepository userRepository;

    public AdvertisementService(IAdvertisementRepository advertisementRepository, IUserRepository userRepository) {
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
    }


    public List<Advertisement> getAll() {
        List<AdvertisementModel> advertisementModels = advertisementRepository.findAll();
        List<Advertisement> advertisements = new ArrayList<>();
        for (AdvertisementModel advertisementModel : advertisementModels) {
            advertisements.add(new Advertisement(advertisementModel));
        }
        return advertisements;
    }

    public List<Advertisement> getAll(Long userId) {
        List<AdvertisementModel> advertisementModels = advertisementRepository.findAll();
        List<Advertisement> advertisements = new ArrayList<>();
        for (AdvertisementModel advertisementModel : advertisementModels) {
            if (!advertisementModel.getUser().getId().equals(userId)) {
                advertisements.add(new Advertisement(advertisementModel));
            }
        }
        return advertisements;
    }

    public Long createAdvertisement(Advertisement advertisement) {
        AdvertisementModel ad = new AdvertisementModel(advertisement);
        UserModel user = userRepository.findById(advertisement.getUserId()).orElseThrow();
        ad.setUser(user);
        try {
            advertisementRepository.save(ad);
            return advertisement.getId();
        } catch (Exception e) {
            return -1L;
        }
    }

    public Boolean updateAdvertisement(Advertisement advertisement) {
        try {
            AdvertisementModel ad = advertisementRepository.findById(advertisement.getId()).orElseThrow();
            ad.setTitle(advertisement.getTitle());
            ad.setDescription(advertisement.getDescription());
            ad.setLocation(advertisement.getLocation());
            advertisementRepository.save(ad);
            return true;
        } catch (Exception e) {
            return false;
        }


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
