package com.proyectoi.kinetia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;


public class Advertisement {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("userId")
    private Long userId;
@JsonProperty("title")
    private String title;
@JsonProperty("description")
    private String description;
@JsonProperty("location")
    private String location;


    private Instant creationDate;




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
