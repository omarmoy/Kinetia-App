package com.proyectoi.kinetia.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoi.kinetia.models.ActivityModel;

public class Prueba {
    @JsonProperty("id")
    private String id;
    @JsonProperty("img_src")
    private String imgSrc;

    public Prueba (ActivityModel am){
        this.id = am.getId().toString();
        this.imgSrc = am.getDescription();
    }
}
