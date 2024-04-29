package com.proyectoi.kinetia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoi.kinetia.models.ActivityModel;

import java.time.Instant;
import java.time.LocalDateTime;


public class Activity {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("date")
    private LocalDateTime date;
    @JsonProperty("createdAt")
    private Instant createdAt;
    @JsonProperty("price")
    private Float price;
    @JsonProperty("location")
    private String location;
    @JsonProperty("category")
    private ActivityModel.Category category;
    @JsonProperty("featured")
    private Boolean featured;
    @JsonProperty("vacancies")
    private int vacancies;
    @JsonProperty("availableVacancies")
    private int availableVacancies ;

    public Activity(ActivityModel activity) {
        this.id = activity.getId();
        this.title = activity.getTitle();
        this.description = activity.getDescription();
        this.userId = activity.getUser().getId();
        this.userName = activity.getUser().fullName();
        this.date = activity.getDate();
        this.createdAt = activity.getCreatedAt();
        this.price = activity.getPrice();
        this.location = activity.getLocation();
        this.category = activity.getCategory();
        this.featured = activity.getFeatured();
        this.vacancies = activity.getVacancies();
        this.availableVacancies = activity.getVacancies() - activity.getReservations().size();
    }

}
