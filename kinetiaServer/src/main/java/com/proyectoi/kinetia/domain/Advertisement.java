package com.proyectoi.kinetia.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoi.kinetia.models.AdvertisementModel;

import java.time.Instant;


public class Advertisement {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("userPhoto")
    private String userPhoto;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("location")
    private String location;
    @JsonProperty("creationDate")
    private Instant creationDate;

    public Advertisement() {}

    public Advertisement(AdvertisementModel advertisementModel) {
        this.id = advertisementModel.getId();
        this.userId = advertisementModel.getUser().getId();
        this.userName = advertisementModel.getUser().fullName();
        this.userPhoto = advertisementModel.getUser().getProfilePicture();
        this.title = advertisementModel.getTitle();
        this.description = advertisementModel.getDescription();
        this.location = advertisementModel.getLocation();
        this.creationDate = advertisementModel.getCreationDate();
    }


    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "AdvertisementModel{" +
                "id=" + id +
                ", user=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
