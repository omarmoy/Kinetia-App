package com.proyectoi.kinetia.api;

import com.proyectoi.kinetia.domain.Activity;
import com.proyectoi.kinetia.models.ActivityModel;
import com.proyectoi.kinetia.services.ActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public List<Activity> activities() {
        return activityService.getAll();
    }

    @PostMapping
    public Long addActivity(@RequestBody ActivityModel activity) {
        System.out.println(activity);
        return this.activityService.createActivity(activity);
    }

    @PutMapping
    public Boolean updateActivity(@RequestBody ActivityModel activity) {
        return activityService.updateActivity(activity);
    }

    @DeleteMapping(path = "/{id}")
    public Boolean deleteActivity(@PathVariable("id") Long id) {
        return activityService.deleteActivity(id);
    }

}
