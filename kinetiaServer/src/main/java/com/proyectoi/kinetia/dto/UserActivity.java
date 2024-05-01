package com.proyectoi.kinetia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoi.kinetia.models.ActivityModel;
import com.proyectoi.kinetia.models.UserModel;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class UserActivity {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
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
    @JsonProperty ("favs")
    private int favs;
    @JsonProperty("reservations")
    private List<Long> reservations = new ArrayList<>();

    public UserActivity(ActivityModel activity) {
        this.id = activity.getId();
        this.title = activity.getTitle();
        this.description = activity.getDescription();
        this.date = activity.getDate();
        this.createdAt = activity.getCreatedAt();
        this.price = activity.getPrice();
        this.location = activity.getLocation();
        this.category = activity.getCategory();
        this.featured = activity.getFeatured();
        this.vacancies = activity.getVacancies();
        this.availableVacancies = activity.getVacancies() - activity.getReservations().size();
        this.favs = activity.getUsersWhoFav().size();
        for(UserModel reservation :activity.getReservations()) {
            this.reservations.add(reservation.getId());
        }

    }

    @Override
    public String toString() {
        return "UserActivity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", createdAt=" + createdAt +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", category=" + category +
                ", featured=" + featured +
                ", vacancies=" + vacancies +
                ", availableVacancies=" + availableVacancies +
                ", reservations=" + reservations.size() +
                '}';
    }
}
