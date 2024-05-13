package com.proyectoi.kinetia.api;

import com.proyectoi.kinetia.domain.Advertisement;
import com.proyectoi.kinetia.models.AdvertisementModel;
import com.proyectoi.kinetia.services.AdvertisementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advertisements")
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping
    public List<Advertisement> advertisements() {
        return advertisementService.getAll();
    }

    @GetMapping(path = "/{userId}")
    public List<Advertisement> advertisementsForUser(@PathVariable("userId") Long userId) {
        return advertisementService.getAll(userId);
    }

    @PostMapping
    public Long addAdvertisement(@RequestBody AdvertisementModel advertisement) {
        return advertisementService.createAdvertisement(advertisement);
    }

    @PutMapping
    public Boolean updateAdvertisement(@RequestBody AdvertisementModel advertisement) {
        return advertisementService.updateAdvertisement(advertisement);
    }

    @DeleteMapping(path = "/{id}")
    public Boolean deleteAdvertisement(@PathVariable("id") Long id) {
        return advertisementService.deleteAdvertisement(id);
    }

}
