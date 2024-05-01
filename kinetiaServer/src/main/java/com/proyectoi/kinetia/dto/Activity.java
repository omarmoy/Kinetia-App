package com.proyectoi.kinetia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoi.kinetia.models.ActivityModel;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ActivityModel.Category getCategory() {
        return category;
    }

    public void setCategory(ActivityModel.Category category) {
        this.category = category;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public int getVacancies() {
        return vacancies;
    }

    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }

    public int getAvailableVacancies() {
        return availableVacancies;
    }

    public void setAvailableVacancies(int availableVacancies) {
        this.availableVacancies = availableVacancies;
    }
}
