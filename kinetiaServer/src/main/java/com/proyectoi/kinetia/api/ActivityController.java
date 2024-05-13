package com.proyectoi.kinetia.api;

import com.proyectoi.kinetia.domain.Activity;
import com.proyectoi.kinetia.domain.ActivityPro;
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

    @GetMapping(path = "/{userId}")
    public List<Activity> activitiesForUser(@PathVariable("userId") Long userId) {
        return activityService.getAll(userId);
    }

    @PostMapping
    public Long addActivity(@RequestBody Activity activity) {
        System.out.println(activity);
        return this.activityService.createActivity(activity);
    }

    @PutMapping
    public Boolean updateActivity(@RequestBody ActivityPro activity) {
        return activityService.updateActivity(activity);
    }

    @DeleteMapping(path = "/{id}")
    public Boolean deleteActivity(@PathVariable("id") Long id) {
        return activityService.deleteActivity(id);
    }


}
